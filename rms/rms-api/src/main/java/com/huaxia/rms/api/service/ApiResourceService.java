/**
 * @项目名称:
 * @文件名称: RmsResourceService 版本号：1.0
 * @创建日期: 2017/10/25
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.api.service;

import com.huaxia.rms.api.model.ResourceRo;

import java.util.List;

/**
 * RmsResourceService接口
 */
public interface ApiResourceService {

    /**
     * 根据用户id获取菜单列表
     * @param uid
     * @return
     */
    String getSourceTreeByUid(int uid);

    /**
     * 根用户id 和 系统编号获取菜单列表
     * @param uid
     * @param syscode
     * @return
     */
    String getSourceTreeByUidAndSysid(int uid, String syscode);


    /**
     * 获取用户在某系统的权限数据
     *
     * @param uid
     * @param syscode
     * @return
     */
    List<ResourceRo> getResourceListByUidAndSysid(int uid, String syscode);


}