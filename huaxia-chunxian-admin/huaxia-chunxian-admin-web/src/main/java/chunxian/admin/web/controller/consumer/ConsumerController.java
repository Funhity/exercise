/**
 * @项目名称:
 * @文件名称: ConsumerController 版本号：1.0
 * @创建日期: 2017/12/11
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.web.controller.consumer;

import chunxian.admin.constant.AdminConstant;
import chunxian.admin.constant.Gender;
import chunxian.admin.model.AccountInfo;
import chunxian.admin.model.Consumer;
import chunxian.admin.service.AccountInfoService;
import chunxian.admin.utils.FastJsonUtil;
import com.github.pagehelper.PageInfo;
import com.huaxia.sso.filter.AuthenType;
import com.huaxia.sso.filter.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: ConsumerController
 * @Description: ConsumerController
 * @author
 */
@Controller
@RequestMapping({"consumer"})
public class ConsumerController
{
  @Autowired
  AccountInfoService accountInfoService;
  @RequestMapping(value={"consumerInfo"}, method=RequestMethod.GET)
  @Login(AuthenType.page)
  public String base()
  {
    return "consumer/consumerInfo";
  }
  
  @RequestMapping(value={"consumerList1"}, method=RequestMethod.GET)
  @ResponseBody
  @Login(AuthenType.json)
  public PageInfo<AccountInfo> listDemoUser(@RequestParam Map<String, Object> params)
  {
    int pageNo = 1;
    int pageSize = 10;
    if (params.get(AdminConstant.PAGE_NO) != null) {
      pageNo = Integer.parseInt((String)params.get(AdminConstant.PAGE_NO));
    }
    if (params.get(AdminConstant.PAGE_SIZE) != null) {
      pageSize = Integer.parseInt((String)params.get(AdminConstant.PAGE_SIZE));
    }
    String json = FastJsonUtil.collectToString(params);
    AccountInfo consumer = FastJsonUtil.toBean(json, AccountInfo.class);

    return this.accountInfoService.findAccountInfoPage(pageNo, pageSize, consumer);
  }
  
  
  @RequestMapping(value={"consumerList"}, method=RequestMethod.GET)
  @ResponseBody
  @Login(AuthenType.json)
  public PageInfo<Consumer> listDemoUserV(@RequestParam Map<String, Object> params)
  {
	    int pageNo = 1;
	    int pageSize = 10;
	    if (params.get(AdminConstant.PAGE_NO) != null) {
	      pageNo = Integer.parseInt((String)params.get(AdminConstant.PAGE_NO));
	    }
	    if (params.get(AdminConstant.PAGE_SIZE) != null) {
	      pageSize = Integer.parseInt((String)params.get(AdminConstant.PAGE_SIZE));
	    }
    String json = FastJsonUtil.collectToString(params);
    Consumer consumer = FastJsonUtil.toBean(json, Consumer.class);
    
    return this.accountInfoService.findConsumerPage(pageNo, pageSize, consumer);
 
  }
  
  
  
  @RequestMapping(value={"detailconsumerInfo"}, method=RequestMethod.GET)
  @ResponseBody
  @Login(AuthenType.json)
  public ModelAndView listDetailUser(@RequestParam Map<String, Object> params)
  {
	  String  userId = (String)params.get("userId");
	  Consumer record=new Consumer();
	  record.setHxxcUserId(userId);
	  List<Consumer> consumerinfolist=accountInfoService.queryConsumerList(record);
	  String realname=consumerinfolist.get(0).getRealName();
	  String phone=consumerinfolist.get(0).getPhone();
	  String registerChannel=consumerinfolist.get(0).getRegisterChannel().length()<=0? null :consumerinfolist.get(0).getRegisterChannel();
	  String deviceModelRegister=consumerinfolist.get(0).getDeviceModelRegister().length()<=0? null :consumerinfolist.get(0).getDeviceModelRegister();
	  Date ctime=consumerinfolist.get(0).getCreateTime();
	  String ddetype=consumerinfolist.get(0).getDevicType();
	  String idCard=consumerinfolist.get(0).getIdCard();
	  String sex=consumerinfolist.get(0).getSex();
	  Map<String, Object> map=new HashMap<>(10);
	  String nullvalue="空";
      DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	  map.put("cateList", userId);
	  map.put("realname", realname==null? nullvalue :realname);
	  map.put("phone", phone==null? nullvalue :phone);
	  map.put("RegisterChannel", registerChannel==null? nullvalue :registerChannel);
	  map.put("DeviceModelRegister", deviceModelRegister==null? nullvalue :deviceModelRegister);
	  map.put("ctime", format.format(ctime));
	  map.put("ddetype", ddetype==""? nullvalue :ddetype);
	  map.put("IdCard", idCard==null? nullvalue :idCard);
	  if(Gender.MAN.getValue().equals(sex)){
		  sex=Gender.MAN.getName();
	  }else if(Gender.WOMAN.getValue().equals(sex)){
		  sex=Gender.WOMAN.getName();
	  }else{
		  sex=Gender.UN_KNOWN.getName();
	  }
	  map.put("sex", sex);

	  String returnUrl = "consumer/detailconsumerInfo";

	  return new ModelAndView(returnUrl,map);
  }
  
  
  
  
  @RequestMapping(value={"countconsumerInfo"}, method= RequestMethod.GET)
  @ResponseBody
  @Login(AuthenType.json)
  public Object consumerSignCount(@RequestParam Map<String, Object> params)
  {
	  Map<String, List> map = new HashMap<>(2);
	  String  dateValue = (String)params.get("paramV");
	  String[] aa =dateValue.split("-");
	  AccountInfo record=new AccountInfo();
	  record.setRealName(aa[0].replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", ""));
	 record.setLoginName(aa[1].replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", ""));
	  
	  List<AccountInfo> consumerinfolist=accountInfoService.queryCountInfoList(record);
	  map.put("value", consumerinfolist);
	 return map;
	
  }
  
  
}
