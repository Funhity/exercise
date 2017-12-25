/**
 * @项目名称:
 * @文件名称: RmsUserService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service;

import com.github.pagehelper.PageInfo;
import com.huaxia.rms.core.model.RmsLabel;
import com.huaxia.rms.core.model.RmsUser;
import com.huaxia.rms.core.model.RmsUserDetail;
import com.huaxia.rms.core.model.RmsUserUpdateRecord;

import java.util.List;
import java.util.Map;

/**
 * RmsUserService接口
 */
public interface RmsUserService extends BaseService {
    Long deleteRmsUserById(Long id);
    Long createRmsUser(RmsUser record);
    Long updateRmsUser(RmsUser record);
    List<RmsUser> queryRmsUserList(Map<String, Object> map);
    PageInfo<RmsUser> findRmsUserPage(int pageNum, int pageSize, Map<String, Object> map);

    RmsUser findOneByCode(String code);
    RmsUser findOneById(int id);
    RmsUser findOneByLoginName(String loginName);

    RmsUserDetail findOneByUserId(int user_id);
    Long updateRmsUserDetail(RmsUserDetail record);
    Long createRmsUserDetail(RmsUserDetail record);

    List<RmsUser> queryUserListByDeptCode(Map map);
    int queryUserListByDeptCodeCount(Map map);

    List<RmsUser> queryUser(Map<String, Object> map);

    int findUserCount(Map<String, Object> map);

    Map checkParamUnique(Map paramMap);
//    Map checkCodeUnique(String code);
//    Map checkNameUnique(String name);

    /**
     * 清除redis中的相关权限设置，主要在更新权限时使用
     * @param userId
     */
    void deleteAuthInRedis(String userId);

    String findMaxCode(String formatLength);

    int queryUserCountByName(Map map);


    List<RmsUser> queryAllUserList(Map map);

    int queryAllUserListCount(Map map);

    String getUserNamesByUserIds(String userIds);

    Long createRmsUserRecord(RmsUserUpdateRecord record);

    List<RmsUserUpdateRecord> queryUserUpdateListByUid(Map map);

    int queryUserUpdateListByUidCount(Map map);

    RmsUserUpdateRecord findRecordById(int id);

    List<RmsLabel> findLabelByUcode(int userId);

    RmsLabel findLabelByName(String name);

    Long createRmsLabel(RmsLabel record);
}