/**
 * @项目名称: generator-web
 * @文件名称: HuaxiaIntrospectedTable 版本号：1.0
 * @创建日期: 2017年10月10日 11:04
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.generator.mybatis;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;

/**
 * 说明: TODO
 *
 * @author xiaogui
 */
public abstract class HuaxiaIntrospectedTable extends IntrospectedTable {
    public HuaxiaIntrospectedTable(TargetRuntime targetRuntime) {
        super(targetRuntime);
    }

    @Override
    protected void calculateXmlAttributes() {
        setIbatis2SqlMapPackage(calculateSqlMapPackage());
        setIbatis2SqlMapFileName(calculateIbatis2SqlMapFileName());
        setMyBatis3XmlMapperFileName(calculateMyBatis3XmlMapperFileName());
        setMyBatis3XmlMapperPackage(calculateSqlMapPackage());

        setIbatis2SqlMapNamespace(calculateIbatis2SqlMapNamespace());
        setMyBatis3FallbackSqlMapNamespace(calculateMyBatis3FallbackSqlMapNamespace());

        setSqlMapFullyQualifiedRuntimeTableName(calculateSqlMapFullyQualifiedRuntimeTableName());
        setSqlMapAliasedFullyQualifiedRuntimeTableName(calculateSqlMapAliasedFullyQualifiedRuntimeTableName());

        setCountByExampleStatementId("countByExample"); //$NON-NLS-1$
        setDeleteByExampleStatementId("deleteByExample"); //$NON-NLS-1$
        //setDeleteByPrimaryKeyStatementId("deleteByPrimaryKey"); //$NON-NLS-1$
        setDeleteByPrimaryKeyStatementId("delete"); //deleteByPrimaryKey改成delete
        setInsertStatementId("insert"); //$NON-NLS-1$
        //setInsertSelectiveStatementId("insertSelective"); //$NON-NLS-1$
        setInsertSelectiveStatementId("insert"); //insertSelective改成insert
        //setSelectAllStatementId("selectAll"); //$NON-NLS-1$
        setSelectAllStatementId("selectByCondition"); // 增加selectByCondition
        setSelectByExampleStatementId("selectByExample"); //$NON-NLS-1$
        setSelectByExampleWithBLOBsStatementId("selectByExampleWithBLOBs"); //$NON-NLS-1$
        //setSelectByPrimaryKeyStatementId("selectByPrimaryKey"); //$NON-NLS-1$
        setSelectByPrimaryKeyStatementId("selectById"); //selectByPrimaryKey改成selectById
        setUpdateByExampleStatementId("updateByExample"); //$NON-NLS-1$
        setUpdateByExampleSelectiveStatementId("updateByExampleSelective"); //$NON-NLS-1$
        setUpdateByExampleWithBLOBsStatementId("updateByExampleWithBLOBs"); //$NON-NLS-1$
        setUpdateByPrimaryKeyStatementId("updateByPrimaryKey"); //$NON-NLS-1$
        //setUpdateByPrimaryKeySelectiveStatementId("updateByPrimaryKeySelective"); //$NON-NLS-1$
        setUpdateByPrimaryKeySelectiveStatementId("update"); //updateByPrimaryKeySelective改成update
        setUpdateByPrimaryKeyWithBLOBsStatementId("updateByPrimaryKeyWithBLOBs"); //$NON-NLS-1$
        setBaseResultMapId("BaseResultMap"); //$NON-NLS-1$
        setResultMapWithBLOBsId("ResultMapWithBLOBs"); //$NON-NLS-1$
        setExampleWhereClauseId("Example_Where_Clause"); //$NON-NLS-1$
        setBaseColumnListId("Base_Column_List"); //$NON-NLS-1$
        setBlobColumnListId("Blob_Column_List"); //$NON-NLS-1$
        setMyBatis3UpdateByExampleWhereClauseId("Update_By_Example_Where_Clause"); //$NON-NLS-1$
    }

    @Override
    public void addColumn(IntrospectedColumn introspectedColumn) {
        if (introspectedColumn.isBLOBColumn()) {
            //blobColumns.add(introspectedColumn);
        } else {
            baseColumns.add(introspectedColumn);
        }

        introspectedColumn.setIntrospectedTable(this);
    }
}
