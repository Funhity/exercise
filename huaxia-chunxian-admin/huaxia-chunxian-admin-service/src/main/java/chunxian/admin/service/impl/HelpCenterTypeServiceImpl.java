/**
 * @项目名称:
 * @文件名称: HelpCenterTypeService 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.HelpCenterType;
import chunxian.admin.service.HelpCenterTypeService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: HelpCenterTypeServiceImpl
 * @Description: HelpCenterTypeServiceImpl
 * @author
 */
@Service
public class HelpCenterTypeServiceImpl implements HelpCenterTypeService {
    @Resource(name="mBaseDao")
    private MBaseDao<HelpCenterType> mBaseDao;

    @Override
    public HelpCenterType getHelpCenterTypeById(Long id) {
        return (HelpCenterType) mBaseDao.findObjectByTemplate("helpcentertype.selectById", id);
    }

    @Override
    public Long deleteHelpCenterTypeById(Long id) {
        return mBaseDao.delete("helpcentertype.delete", id);
    }

    @Override
    public Long createHelpCenterType(HelpCenterType record) {
        HelpCenterType sortRecord = new HelpCenterType();
        sortRecord.setSort(record.getSort());
        Integer count = countBySort(sortRecord);
        if(count > 0) {
            //sort存在,低位到高位规则，存在sort及之后+1
            updateSortByLowToHigh(sortRecord);
        }

        return mBaseDao.create("helpcentertype.insert", record);
    }

    @Override
    public Long updateHelpCenterType(HelpCenterType record) {
        //查询原有的sort
        HelpCenterType oldRecord = getHelpCenterTypeById(record.getId());
        Integer oldSort = oldRecord.getSort();

        HelpCenterType sortRecord = new HelpCenterType();
        sortRecord.setSort(record.getSort());
        Integer count = countBySort(sortRecord);
        if(count > 0) {
            HelpCenterType hlRecord = new HelpCenterType();
            hlRecord.setSort(oldSort);
            hlRecord.setToSort(record.getSort());
            if(oldSort < record.getSort()) {
                //sort低位到高位规则，存在sort及之后+1
                updateSortByLowToHigh(hlRecord);
            } else {
                //高位到低位规则,低位到高位之间+1
                updateSortByHighToLow(hlRecord);
            }
        }

        return mBaseDao.update("helpcentertype.update", record);
    }

    @Override
    public List<HelpCenterType> queryHelpCenterTypeList(HelpCenterType record) {
        return mBaseDao.findListByTemplate("helpcentertype.selectByCondition", record);
    }

    @Override
    public PageInfo<HelpCenterType> findHelpCenterTypePage(int pageNum, int pageSize, HelpCenterType record) {
        return mBaseDao.findByQuery("helpcentertype.selectByCondition", pageNum, pageSize, record);
    }

    @Override
    public Integer countBySort(HelpCenterType record) {
        return (Integer) mBaseDao.findObjectByTemplate("helpcentertype.countBySort", record);
    }

    @Override
    public Long updateSortByLowToHigh(HelpCenterType record) {
        return mBaseDao.update("helpcentertype.updateSortByLowToHigh", record);
    }

    @Override
    public Long updateSortByHighToLow(HelpCenterType record) {
        return mBaseDao.update("helpcentertype.updateSortByHighToLow", record);
    }
}