package com.huaxia.rms.core.dao.impl;

import com.huaxia.rms.core.dao.BaseDao;

public class BaseDaoImpl implements BaseDao {

//    public List findByExample(Object example) {
//
////        TeammemberExample teammemberExample = new TeammemberExample();
//        Criteria criteria = teammemberExample.createCriteria();
//        criteria.andUserIdEqualTo(idInteger);
//
//        return this.getCurrentSession().createCriteria(example.getClass()).add(Example.create(example)).list();
//    }
//
//    public List findByExample(Object example, String condition, boolean enableLike) {
//
//        Criteria ec = this.getCurrentSession().createCriteria(example.getClass());
//        if (enableLike)
//            ec.add(Example.create(example).enableLike());
//        else
//            ec.add(Example.create(example));
//        if (condition != null && !condition.equals("")) {
//            String newCondition = condition.replaceAll("`", "'");
//            ec.add(Restrictions.sqlRestriction(newCondition));
//        }
//        return ec.list();
//    }
}
