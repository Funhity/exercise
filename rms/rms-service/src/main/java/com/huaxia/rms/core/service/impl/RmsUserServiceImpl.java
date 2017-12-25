/**
 * @项目名称:
 * @文件名称: RmsUserService 版本号：1.0
 * @创建日期: 2017/10/24
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.rms.core.service.impl;

import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import com.huaxia.rms.annotation.MethodLog;
import com.huaxia.rms.constant.RedisConstant;
import com.huaxia.rms.core.dao.RedisDao;
import com.huaxia.rms.core.model.RmsLabel;
import com.huaxia.rms.core.model.RmsUserDetail;
import com.huaxia.rms.core.model.RmsUserUpdateRecord;
import com.huaxia.rms.core.service.RmsUserService;
import com.huaxia.rms.core.model.RmsUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class RmsUserServiceImpl extends BaseServiceImpl implements RmsUserService {
    private Logger logger = LoggerFactory.getLogger(RmsUserServiceImpl.class);

    @Resource(name="mBaseDao")
    private MBaseDao<RmsUser> mBaseDao;
    @Resource
    public RedisDao redisDao;

    @Override
    public Long deleteRmsUserById(Long id) {
        return mBaseDao.delete("rmsuser.delete", id);
    }

    @Override
    @MethodLog(remark = "新增用户")
    public Long createRmsUser(RmsUser record) {
        return mBaseDao.create("rmsuser.insert", record);
    }

    @Override
    @MethodLog(remark = "更新用户")
    public Long updateRmsUser(RmsUser record) {
        return mBaseDao.update("rmsuser.update", record);
    }

    @Override
    public List<RmsUser> queryRmsUserList(Map<String, Object> map) {
        //TODO
        return mBaseDao.findListByTemplate("rmsuser.selectAll", map);
    }

    @Override
    public PageInfo<RmsUser> findRmsUserPage(int pageNum, int pageSize, Map<String, Object> map) {
        //TODO
        return mBaseDao.findByQuery("rmsuser.selectAll", pageNum, pageSize, map);
    }


    @Override
    public RmsUser findOneByCode(String code) {
        return (RmsUser)mBaseDao.findObjectByTemplate("rmsuser.selectByCode", code);
    }

    @Override
    public RmsUser findOneById(int id) {
        return (RmsUser)mBaseDao.findObjectByTemplate("rmsuser.selectById", id);
    }

    @Override
    public RmsUser findOneByLoginName(String loginName) {
        return (RmsUser)mBaseDao.findObjectByTemplate("rmsuser.selectByLoginName", loginName);
    }

    @Override
    public RmsUserDetail findOneByUserId(int user_id) {
        return (RmsUserDetail)mBaseDao.findObjectByTemplate("rmsuserdetail.selectById", user_id);
    }

    @Override
    public Long createRmsUserDetail(RmsUserDetail record) {
        return mBaseDao.create("rmsuserdetail.insert", record);
    }

    @Override
    public Long updateRmsUserDetail(RmsUserDetail record) {
        return mBaseDao.update("rmsuserdetail.update", record);
    }

    @Override
    //@MethodLog(remark = "查询部门用户")
    public List<RmsUser> queryUserListByDeptCode(Map map) {
        return mBaseDao.findListByTemplate("rmsuser.selectByDepartmentCode", map);
    }

    @Override
    public int queryUserListByDeptCodeCount(Map map) {
        int userCount = Integer.parseInt(String.valueOf(mBaseDao.findObjectByTemplate("rmsuser.selectByDepartmentCodeCount", map)));
        return userCount;
    }

    @Override
    public List<RmsUser> queryUser(Map<String, Object> map) {
        return mBaseDao.findListByTemplate("rmsuser.selectUserByPage", map);
    }

    @Override
    public int findUserCount(Map<String, Object> map) {
        int userCount = Integer.parseInt(String.valueOf(mBaseDao.findObjectByTemplate("rmsuser.selectCount", map)));
        return userCount;
    }

    /**
     * 根据参数获取用户
     * @param paramMap
     * @return
     */
    @Override
    public Map checkParamUnique(Map paramMap) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        try {
            RmsUser user = (RmsUser)mBaseDao.findObjectByTemplate("rmsuser.selectUserByParam", paramMap);
            if(user == null) {
                map.put("valid", true);
            } else {
                map.put("valid", false);
            }
            return map;
        } catch (Exception ex) {
            logger.info(ex.getMessage().toString());
            map.put("valid", false);
            return map;
        }
    }

//    @Override
//    public Map checkCodeUnique(String code) {
//        Map<String, Boolean> map = new HashMap<String, Boolean>();
//        try {
//            RmsUser user = (RmsUser)mBaseDao.findObjectByTemplate("rmsuser.selectUserByCode", code);
//            if(user == null) {
//                map.put("valid", true);
//            } else {
//                map.put("valid", false);
//            }
//            return map;
//        } catch (Exception ex) {
//            logger.info(ex.getMessage().toString());
//            map.put("valid", false);
//            return map;
//        }
//    }
//
//    @Override
//    public Map checkNameUnique(String name) {
//        Map<String, Boolean> map = new HashMap<String, Boolean>();
//        try {
//            RmsUser user = (RmsUser)mBaseDao.findObjectByTemplate("rmsuser.selectUserByName", name);
//            if(user == null) {
//                map.put("valid", true);
//            } else {
//                map.put("valid", false);
//            }
//            return map;
//        } catch (Exception ex) {
//            logger.info(ex.getMessage().toString());
//            map.put("valid", false);
//            return map;
//        }
//    }

    @Override
    public void deleteAuthInRedis(String userId) {
        //缓存SimpleAuthorizationInfo权限
        redisDao.delete(userId);
        redisDao.delete(RedisConstant.ROLE_PRE + userId);
        redisDao.delete(RedisConstant.PERMISSION_PRE + userId);
    }

    @Override
    public String findMaxCode(String formatLength) {
        int maxId = Integer.parseInt(String.valueOf(mBaseDao.findObjectByTemplate("rmsuser.selectMaxId", null)));

        String maxCode = String.format("%0" + formatLength + "d", maxId + 1);

        return maxCode;
    }

    @Override
    public int queryUserCountByName(Map map) {
        int userCount = Integer.parseInt(String.valueOf(mBaseDao.findObjectByTemplate("rmsuser.selectCountByName", map)));
        return userCount;
    }

    @Override
    public List<RmsUser> queryAllUserList(Map map) {
        return mBaseDao.findListByTemplate("rmsuser.selectAllUser", map);
    }

    @Override
    public int queryAllUserListCount(Map map) {
        int userCount = Integer.parseInt(String.valueOf(mBaseDao.findObjectByTemplate("rmsuser.selectAllUserCount", map)));
        return userCount;
    }

    @Override
    public String getUserNamesByUserIds(String userIds) {
        String userNames = String.valueOf(mBaseDao.findObjectByTemplate("rmsuser.selectUserNamesById", userIds.split(",")));
        return userNames;
    }

    @Override
    public Long createRmsUserRecord(RmsUserUpdateRecord record) {
        return mBaseDao.create("rmsuserupdaterecord.insert", record);
    }

    @Override
    //@MethodLog(remark = "查询部门用户")
    public List<RmsUserUpdateRecord> queryUserUpdateListByUid(Map map) {
        return mBaseDao.findListByTemplate("rmsuserupdaterecord.selectListByUid", map);
    }

    @Override
    public int queryUserUpdateListByUidCount(Map map) {
        int userCount = Integer.parseInt(String.valueOf(mBaseDao.findObjectByTemplate("rmsuserupdaterecord.selectListByUidCount", map)));
        return userCount;
    }

    @Override
    public RmsUserUpdateRecord findRecordById(int id) {
        return (RmsUserUpdateRecord)mBaseDao.findObjectByTemplate("rmsuserupdaterecord.selectById", id);
    }

    @Override
    public List<RmsLabel> findLabelByUcode(int userId) {
        return mBaseDao.findListByTemplate("rmslabel.selectLabelByUid", userId);
    }

    @Override
    public RmsLabel findLabelByName(String name) {
        return (RmsLabel)mBaseDao.findObjectByTemplate("rmslabel.selectLabelByName", name);
    }

    @Override
    public Long createRmsLabel(RmsLabel record) {
        return mBaseDao.create("rmslabel.insert", record);
    }
}