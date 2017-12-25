/**
 * @项目名称:
 * @文件名称: RmsUserService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.api.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.api.model.UserRo;
import com.huaxia.rms.api.model.UserDetailRo;

import java.util.List;
import java.util.Map;

/**
 * RmsUserService接口
 */
public interface ApiUserService {

    UserRo findOneByCode(String code);
    UserRo findOneById(int id);

    UserDetailRo findOneByUserId(int user_id);

    List<UserRo> queryUserListByDeptCode(Map map);

    int queryUserListByDeptCodeCount(Map map);

//    List<UserRo> queryUser(Map<String, Object> map);
//
//    int findUserCount(Map<String, Object> map);


}