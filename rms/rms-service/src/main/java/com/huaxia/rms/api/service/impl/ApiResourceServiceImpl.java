/**
 * @项目名称:
 * @文件名称: RmsResourceService 版本号：1.0
 * @创建日期: 2017/10/25
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.api.service.impl;

import com.huaxia.rms.api.model.ResourceRo;
import com.huaxia.rms.api.service.ApiResourceService;
import com.huaxia.rms.core.model.RmsResource;
import com.huaxia.rms.core.service.RmsResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("apiResourceService")
public class ApiResourceServiceImpl implements ApiResourceService {

    private Logger logger = LoggerFactory.getLogger(ApiResourceServiceImpl.class);

    @Resource
    private RmsResourceService resourceService;

    @Override
    public String getSourceTreeByUid(int uid) {
        String treeStr = resourceService.getSourceTreeByUid(uid);
        return treeStr;
    }

    @Override
    public String getSourceTreeByUidAndSysid(int uid, String syscode) {
        String treeStr = resourceService.getSourceTreeByUidAndSyscode(uid, syscode);
        return treeStr;
    }

    @Override
    public List<ResourceRo> getResourceListByUidAndSysid(int uid, String syscode) {

        List<ResourceRo> resourceRoList = null;
        try {
            List<RmsResource> list =  resourceService.getSourceListByUidAndSyscode(uid, syscode);
            if(list != null && list.size() > 0) {
                resourceRoList = new ArrayList<ResourceRo>();
                BeanUtils.copyProperties(list, resourceRoList);

            }
        }catch(Exception e){
            return null;
        }
        return resourceRoList;

    }


}