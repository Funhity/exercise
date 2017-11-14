/**
 * @项目名称: generator-web
 * @文件名称: HuaxiaXMLMapperGenerator 版本号：1.0
 * @创建日期: 2017年10月10日 9:47
 * <p>
 * Copyrights © 2016 华夏信财股权投资管理有限公司 All rights reserved.
 * <p>
 * 注意：本内容仅限于华夏信财股权投资管理有限公司信息技术部内部传阅，禁止外泄以及用于其他的商业目的。
 */
package com.huaxia.generator.mybatis;

import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 * 说明: TODO
 * @author xiaogui
 * @version
 */
public class HuaxiaXMLMapperGenerator extends XMLMapperGenerator {


    @Override
    protected XmlElement getSqlMapElement() {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
        progressCallback.startTask(getString(
                "Progress.12", table.toString())); //$NON-NLS-1$
        XmlElement answer = new XmlElement("mapper"); //$NON-NLS-1$
        String namespace = introspectedTable.getMyBatis3SqlMapNamespace();
        //名称空间调整为类型小写
        String ns = table.getDomainObjectName();
        if(StringUtils.isNotBlank(ns)) {
            namespace = StringUtils.lowerCase(StringUtils.trim(ns));
        }
        answer.addAttribute(new Attribute("namespace", //$NON-NLS-1$
                namespace));

        context.getCommentGenerator().addRootComment(answer);

        addResultMapWithoutBLOBsElement(answer);
        addBaseColumnListElement(answer);
        addSelectByPrimaryKeyElement(answer);
        addDeleteByPrimaryKeyElement(answer);
        addInsertSelectiveElement(answer);
        addUpdateByPrimaryKeySelectiveElement(answer);
        addSelectByConditionElement(answer);

        return answer;
    }


    protected void addSelectByConditionElement(XmlElement parentElement) {
        AbstractXmlElementGenerator elementGenerator = new HuaxiaSelectByConditionGenerator();
        initializeAndExecuteGenerator(elementGenerator, parentElement);
    }

    @Override
    protected void initializeAndExecuteGenerator(
            AbstractXmlElementGenerator elementGenerator,
            XmlElement parentElement) {
        elementGenerator.setContext(context);
        elementGenerator.setIntrospectedTable(introspectedTable);
        elementGenerator.setProgressCallback(progressCallback);
        elementGenerator.setWarnings(warnings);
        parentElement.addElement(new TextElement("")); // 增加一行空白的隔行
        elementGenerator.addElements(parentElement);
    }
}
