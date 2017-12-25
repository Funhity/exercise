/**
 * @项目名称:
 * @文件名称: RmsLabelService 版本号：1.0
 * @创建日期: 2017/12/20
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service.impl;

import com.github.pagehelper.PageInfo;

import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.core.service.RmsLabelService;
import com.huaxia.rms.core.model.RmsLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("labelService")
public class RmsLabelServiceImpl implements RmsLabelService {
    private Logger logger = LoggerFactory.getLogger(RmsLabelServiceImpl.class);

    @Resource(name="mBaseDao")
    private MBaseDao<RmsLabel> mBaseDao;

    @Override
    public Long deleteRmsLabelById(Long id) {
        return mBaseDao.delete("rmslabel.delete", id);
    }

    @Override
    public Long createRmsLabel(RmsLabel record) {
        return mBaseDao.create("rmslabel.insert", record);
    }

    @Override
    public Long updateRmsLabel(RmsLabel record) {
        return mBaseDao.update("rmslabel.update", record);
    }

    @Override
    public List<RmsLabel> queryRmsLabelList(Map<String, Object> map) {
        //TODO
        return mBaseDao.findListByTemplate("rmslabel.selectAll", map);
    }

    @Override
    public PageInfo<RmsLabel> findRmsLabelPage(int pageNum, int pageSize, Map<String, Object> map) {
        //TODO
        return mBaseDao.findByQuery("rmslabel.selectAll", pageNum, pageSize, map);
    }

    @Override
    public List<RmsLabel> queryLabelList(Map<String, Object> map) {
        return mBaseDao.findListByTemplate("rmslabel.selectLabellist", map);
    }

    @Override
    public int queryLabelListCount(Map map) {
        int count = Integer.parseInt(String.valueOf(mBaseDao.findObjectByTemplate("rmslabel.selectLabellistCount", map)));
        return count;
    }

    @Override
    public String getNamesByIds(String ids) {

        List<RmsLabel> nameList = mBaseDao.findListByTemplate("rmslabel.selectLabelByIds", ids.split(","));
        StringBuilder names = new StringBuilder();
        if(nameList != null && nameList.size() > 0) {
            for (RmsLabel label: nameList) {
                names.append(label.getName()).append(",");
            }
        }
        if(names.length() > 0) {
            return names.deleteCharAt(names.length() - 1).toString();
        }
        //String userNames = String.valueOf(mBaseDao.findObjectByTemplate("rmslabel.selectNamesByIds", ids.split(",")));
        return "";
    }


}