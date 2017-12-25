/**
 * @项目名称:
 * @文件名称: OrderController 版本号：1.0
 * @创建日期: 2017/12/11
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.web.controller.order;

import chunxian.admin.constant.AdminConstant;
import chunxian.admin.constant.OrderSource;
import chunxian.admin.constant.OrderStatus;
import chunxian.admin.model.*;
import chunxian.admin.service.*;
import chunxian.admin.utils.FastJsonUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author
 * @Title: OrderController
 * @Description: OrderController
 */
@Controller
@RequestMapping({"order"})
public class OrderController {
    @Autowired
    OrderInfoService orderInfoService;
    @Autowired
    OrderService orderService;
    @Autowired
    UserPersonalService userPersonalService;
    @Autowired
    UserWorkService userWorkService;
    @Autowired
    UserAuthService userAuthService;

    @RequestMapping(value = {"orderInfo"}, method = RequestMethod.GET)
    @Login(AuthenType.page)
    public String base() {
        return "order/orderInfo";
    }

    @RequestMapping(value = {"orderList"}, method = RequestMethod.GET)
    @ResponseBody
    @Login(AuthenType.json)
    public PageInfo<OrderInfoDO> listDemoUserV(@RequestParam Map<String, Object> params) {
        int pageNo = 1;
        int pageSize = 10;
        if (params.get(AdminConstant.PAGE_NO) != null) {
            pageNo = Integer.parseInt((String) params.get(AdminConstant.PAGE_NO));
        }
        if (params.get(AdminConstant.PAGE_SIZE) != null) {
            pageSize = Integer.parseInt((String) params.get(AdminConstant.PAGE_SIZE));
        }
        String json = FastJsonUtil.collectToString(params);
        OrderInfoDO orderinfodo = FastJsonUtil.toBean(json, OrderInfoDO.class);

        return this.orderInfoService.findOrderDoInfoPage(pageNo, pageSize, orderinfodo);

    }

    @RequestMapping(value = {"detailorderInfo"}, method = RequestMethod.GET)
    @ResponseBody
    @Login(AuthenType.json)
    public ModelAndView listDetailUser(@RequestParam Map<String, Object> params) {
        Map<String, Object> map = new HashMap<>(10);
        List<UserPersonal> userPersonallist = new ArrayList<>();
        List<UserWork> userWorklist = new ArrayList<>();
        String personalvalue = null;
        String workvalue = null;
        String json = FastJsonUtil.collectToString(params);
        OrderInfoDO record = FastJsonUtil.toBean(json, OrderInfoDO.class);
        List<OrderInfoDO> orderinfolist = orderInfoService.queryOrderDoList(record);
        String value = JSONObject.toJSONString(orderinfolist.get(0));
        if (CollectionUtils.isNotEmpty(orderinfolist)) {
            // 花财
            if (OrderSource.HUA_CAI.getValue().equals(orderinfolist.get(0).getAppType())) {
                // 个人信息
                String json2 = FastJsonUtil.collectToString(params);
                UserPersonal personal = FastJsonUtil.toBean(json2, UserPersonal.class);
                userPersonallist = userPersonalService.queryUserPersonalList(personal);
                if (CollectionUtils.isNotEmpty(userPersonallist)) {
                    personalvalue = JSONObject.toJSONString(userPersonallist.get(0));
                }
                // 工作信息
                String json3 = FastJsonUtil.collectToString(params);
                UserWork work = FastJsonUtil.toBean(json3, UserWork.class);
                userWorklist = userWorkService.queryUserWorkList(work);
                if (CollectionUtils.isNotEmpty(userWorklist)) {
                    workvalue = JSONObject.toJSONString(userWorklist.get(0));
                }
                // 摩尔龙
            } else if (OrderSource.MO_ER_LONG.getValue().equals(orderinfolist.get(0).getAppType())) {
                OrderInfo orderInfo = FastJsonUtil.toBean(json, OrderInfo.class);
                List<OrderInfo> mllorderInfolist = orderInfoService.queryOrderInfoList(orderInfo);
                String personalvalueFirst = JSONObject.toJSONString(mllorderInfolist.get(0));
                Map<String, Object> mapsreslu = (Map) JSON.parse(personalvalueFirst);
                String jsonperson = FastJsonUtil.collectToString(mapsreslu);
                UserPersonal resultpersonal = FastJsonUtil.toBean(jsonperson, UserPersonal.class);
                userPersonallist.add(resultpersonal);
                personalvalue = JSONObject.toJSONString(userPersonallist.get(0));
                UserWork resultwork = FastJsonUtil.toBean(jsonperson, UserWork.class);
                userWorklist.add(resultwork);
                workvalue = JSONObject.toJSONString(userWorklist.get(0));

            }
            map.put("personalvalue", personalvalue);
            map.put("workvalue", workvalue);
            map.put("value", value);
            return new ModelAndView("order/detailorderInfo", map);
        } else {
            return null;
        }

    }

    @RequestMapping(value = {"orderAuth"}, method = RequestMethod.GET)
    @ResponseBody
    @Login(AuthenType.json)
    public PageInfo<UserAuth> listDemoAuth(@RequestParam Map<String, Object> params) {
        int pageNo = 1;
        int pageSize = 10;
        if (params.get(AdminConstant.PAGE_NO) != null) {
            pageNo = Integer.parseInt((String) params.get(AdminConstant.PAGE_NO));
        }
        if (params.get(AdminConstant.PAGE_SIZE) != null) {
            pageSize = Integer.parseInt((String) params.get(AdminConstant.PAGE_SIZE));
        }
        String json = FastJsonUtil.collectToString(params);
        UserAuth auth = FastJsonUtil.toBean(json, UserAuth.class);

        return this.userAuthService.signByCondition(pageNo, pageSize, auth);

    }

    @RequestMapping(value = {"countorderInfo"}, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @Login(AuthenType.json)
    public Object orderSignCount(@RequestParam Map<String, Object> params) {
        Map<String, List> map = new HashMap<>(2);
        String dateValue = (String) params.get("paramV");
        String[] aa = dateValue.split("-");
        OrderInfoDO record = new OrderInfoDO();
        record.setLoginName(aa[1].replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", ""));
        record.setRealName(aa[0].replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", ""));
        List<OrderInfoDO> orderinfolist = orderInfoService.queryCountOrderInfoList(record);
        map.put("value", orderinfolist);
        return map;

    }

    @RequestMapping(value = {"statusProssInfo"}, method = {
            org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @Login(AuthenType.json)
    public Object statusProssInfo(@RequestParam Map<String, Object> params) {
        Map<String, List> map = new HashMap<>(5);

        String orderNo = (String) params.get("orderNo");
        Order orderrecord = new Order();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        orderrecord.setOrderNo(orderNo);
        List<Order> orderlist = orderService.queryOrderList(orderrecord);
        String status;
        String checkPassTimeValue="";
        String moneyTimeValue="";
        List<OrderInfoStatus> orderstatuslist;
        if (CollectionUtils.isNotEmpty(orderlist)) {

            status = orderlist.get(0).getStatus().toString();
            String ordermount = orderlist.get(0).getLoanAmount().toString();
            String loanterm = orderlist.get(0).getLoanTerm().toString();
            Date checkPassTime = orderlist.get(0).getCheckPassTime();
            Date moneyTime = orderlist.get(0).getMoneyTime();
            if(checkPassTime!=null){
            	 checkPassTimeValue = format.format(checkPassTime);
            }
            if(moneyTime!=null){
            	  moneyTimeValue = format.format(moneyTime);
            }
         

            orderstatuslist = creatOrder(ordermount, loanterm, checkPassTimeValue, moneyTimeValue, status);

        } else {// 摩尔龙数据
            OrderInfo orderinforecord = new OrderInfo();
            orderinforecord.setOrderNo(orderNo);
            List<OrderInfo> orderotherlist = orderInfoService.queryOrderInfoList(orderinforecord);
            status = orderotherlist.get(0).getState().toString();
            String ordermount = orderotherlist.get(0).getLoanAmount().toString();
            String loanterm = orderotherlist.get(0).getTerm().toString();
            Date checkPassTime = orderotherlist.get(0).getCheckPassTime();
            Date moneyTime = orderotherlist.get(0).getMontyTime();
            if(checkPassTime!=null){
           	 checkPassTimeValue = format.format(checkPassTime);
           }
           if(moneyTime!=null){
           	  moneyTimeValue = format.format(moneyTime);
           }
            orderstatuslist = creatOrder(ordermount, loanterm, checkPassTimeValue, moneyTimeValue, status);

        }
        
        


        map.put("orderStatus", orderstatuslist);

        return map;

    }

    private List<OrderInfoStatus> creatOrder(String orderamount, String loantermvalue, String checkPassTime, String moneyTime, String status) {
        List<OrderInfoStatus> orderstatuslist = new ArrayList<>();
        OrderInfoStatus orderInfoStatus = new OrderInfoStatus();
        OrderInfoStatus orderInfoStatus1 = new OrderInfoStatus();
        OrderInfoStatus orderInfoStatus2 = new OrderInfoStatus();
        String loanterm = "申请期数:";
        String shcgtitle = "审核成功";
        String fkcgtitle = "放款成功";
        String sqje = "申请金额:";
        String spsj = "审批时间:";
        String fksj = "放款时间:";
        String br = "</br>";
        String tjsqtitle = "提交申请";
        orderInfoStatus.setTitle(tjsqtitle);
        if (OrderStatus.AUDITING.getValue().equals(status)) {

            orderInfoStatus
                    .setText(sqje + orderamount + br + loanterm + loantermvalue);

            orderInfoStatus1.setTitle(shcgtitle);
            orderInfoStatus1.setText("");
            orderInfoStatus2.setTitle(fkcgtitle);
            orderInfoStatus2.setText("");
            orderInfoStatus2.setState("0");
        } else if (OrderStatus.COMPLETE_LOAN.getValue().equals(status)) {
            orderInfoStatus1.setTitle(shcgtitle);
            orderInfoStatus2.setTitle(fkcgtitle);
            orderInfoStatus2.setState("2");
            orderInfoStatus
                    .setText(sqje + orderamount + br + loanterm + loantermvalue);
            orderInfoStatus1.setText(spsj + checkPassTime + br);
            orderInfoStatus2.setText(fksj + moneyTime);

        } else {
            String title = "";
            // 订单状态：0.未提交、1.审核中、2.审核通过、3.审核未通过、4.需补件、5.已取消、6.等待放款、7.已放款、8.补件完成、9.签约完成、10.银行处理中

            switch (Integer.parseInt(status)) {
                case 2:
                    title = OrderStatus.AUDIT.getName();
                    break;
                case 3:
                    title = OrderStatus.FAIL_AUDIT.getName();
                    break;
                case 5:
                    title = OrderStatus.CANCELED.getName();
                    break;
                case 1:
                    title = OrderStatus.AUDITING.getName();
                    break;
                default:
                    title = OrderStatus.AUDITING.getName();
                    break;

            }
            orderInfoStatus
                    .setText(sqje + orderamount + br + loanterm + loantermvalue);
            orderInfoStatus1.setTitle(title);
            orderInfoStatus1.setText(title);
            orderInfoStatus2.setTitle(fkcgtitle);
            orderInfoStatus2.setText("");
            orderInfoStatus2.setState("1");
        }
        orderstatuslist.add(orderInfoStatus);
        orderstatuslist.add(orderInfoStatus1);
        orderstatuslist.add(orderInfoStatus2);
        return orderstatuslist;
    }


}
