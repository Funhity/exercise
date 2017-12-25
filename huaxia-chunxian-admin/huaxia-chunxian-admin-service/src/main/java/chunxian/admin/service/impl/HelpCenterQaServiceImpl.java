/**
 * @项目名称:
 * @文件名称: HelpCenterQaService 版本号：1.0
 * @创建日期: 2017/12/7
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package chunxian.admin.service.impl;

import chunxian.admin.model.HelpCenterQa;
import chunxian.admin.service.HelpCenterQaService;
import com.github.pagehelper.PageInfo;
import com.huaxia.common.core.dao.MBaseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: HelpCenterQaServiceImpl
 * @Description: HelpCenterQaServiceImpl
 * @author
 */
@Service
public class HelpCenterQaServiceImpl implements HelpCenterQaService {
    @Resource(name="mBaseDao")
    private MBaseDao<HelpCenterQa> mBaseDao;

    @Override
    public HelpCenterQa getHelpCenterQaById(Long id) {
        return (HelpCenterQa) mBaseDao.findObjectByTemplate("helpcenterqa.selectById", id);
    }

    @Override
    public Long deleteHelpCenterQaById(Long id) {
        return mBaseDao.delete("helpcenterqa.delete", id);
    }

    @Override
    public Long createHelpCenterQa(HelpCenterQa record) {
        HelpCenterQa sortRecord = new HelpCenterQa();
        sortRecord.setSort(record.getSort());
        Integer count = countBySort(sortRecord);
        if(count > 0) {
            //sort存在,低位到高位规则，存在sort及之后+1
            updateSortByLowToHigh(sortRecord);
        }

        return mBaseDao.create("helpcenterqa.insert", record);
    }

    @Override
    public Long updateHelpCenterQa(HelpCenterQa record) {
        //查询原有的sort
        HelpCenterQa oldRecord = getHelpCenterQaById(record.getId());
        Integer oldSort = oldRecord.getSort();

        HelpCenterQa sortRecord = new HelpCenterQa();
        sortRecord.setSort(record.getSort());
        Integer count = countBySort(sortRecord);
        if(count > 0) {
            HelpCenterQa hlRecord = new HelpCenterQa();
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

        return mBaseDao.update("helpcenterqa.update", record);
    }

    @Override
    public List<HelpCenterQa> queryHelpCenterQaList(HelpCenterQa record) {
        return mBaseDao.findListByTemplate("helpcenterqa.selectByCondition", record);
    }

    @Override
    public PageInfo<HelpCenterQa> findHelpCenterQaPage(int pageNum, int pageSize, HelpCenterQa record) {
        return mBaseDao.findByQuery("helpcenterqa.selectByCondition", pageNum, pageSize, record);
    }

    @Override
    public Integer countBySort(HelpCenterQa record) {
        return (Integer) mBaseDao.findObjectByTemplate("helpcenterqa.countBySort", record);
    }

    @Override
    public Long updateSortByLowToHigh(HelpCenterQa record) {
        return mBaseDao.update("helpcenterqa.updateSortByLowToHigh", record);
    }

    @Override
    public Long updateSortByHighToLow(HelpCenterQa record) {
        return mBaseDao.update("helpcenterqa.updateSortByHighToLow", record);
    }
}