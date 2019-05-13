package me.szp.framework.spring.context;

import me.szp.framework.core.io.Resource;
import me.szp.framework.spring.beans.PropertyValue;
import me.szp.framework.spring.beans.XMLRules;
import me.szp.framework.spring.beans.exception.BeanDefinitionRegistryException;
import me.szp.framework.spring.beans.factory.support.AbstractBeanDefinitionReader;
import me.szp.framework.spring.beans.factory.support.BeanDefinitionRegistry;
import me.szp.framework.spring.beans.factory.support.GenericBeanDefinition;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * xml的bean定义读取器
 *
 * @author GhostDog
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) {
        this.loadBeanDefinitions(new Resource[]{resource});
    }

    @Override
    public void loadBeanDefinitions(Resource... resource) {
        if (resource != null && resource.length > 0) {
            Arrays.asList(resource).forEach(this::parseXml);
        }
    }

    private void parseXml(Resource resource) {
        List<Element> elementList = this.getElements(resource);

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();

        //遍历Element集合
        for (Element element : elementList) {
            //如果是bean标签
            if (XMLRules.BEAN_RULE.getProperty().equals(element.getName())) {

                //属性集合
                List<PropertyValue> propertyValueList = new ArrayList<>();
                String beanId = element.attributeValue(XMLRules.BEAN_RULE.getName());
                String beanClass = element.attributeValue(XMLRules.BEAN_RULE.getValue());

                try {
                    //确保获取的标签正确
                    if (!StringUtils.isEmpty(beanId) && !StringUtils.isEmpty(beanClass)) {
                        // TODO 暂时这么写，后面加上其他参数
                        beanDefinition.setBeanClass(Class.forName(beanClass));

                        //获取bean标签的子元素
                        List<Element> propertyList = element.elements();

                        for (Element property : propertyList) {

//                            logger.debug("xml定义:{},解析得到:{}", XMLRules.SET_INJECT.getProperty(), property.getName());

//                            logger.debug("是否相等:[{}]",XMLRules.SET_INJECT.getProperty().equals(property.getName()));
                            //确保是property标签
                            if (XMLRules.SET_INJECT.getProperty().equals(property.getName())) {
                                String name = property.attributeValue(XMLRules.SET_INJECT.getName());
                                String value = property.attributeValue(XMLRules.SET_INJECT.getValue());
                                if (logger.isDebugEnabled()) {
                                    logger.debug("属性：[name={},value={}]", name, value);
                                }
                                if (StringUtils.isEmpty(name) || StringUtils.isEmpty(value)) {
                                    throw new RuntimeException("property标签属性不能为空");
                                }
                                propertyValueList.add(new PropertyValue(name, value));
                            }
                        }
                        beanDefinition.setPropertyValues(propertyValueList);
                    }
                    this.registry.registerBeanDefinition(beanId, beanDefinition);
                } catch (ClassNotFoundException | BeanDefinitionRegistryException e) {
                    e.printStackTrace();
                }

            }
        }


    }

    /**
     * 解析xml的工厂,根据路径名获取根元素下面的所有子元素
     *
     * @return
     */
    private List<Element> getElements(Resource resource) {


        // 创建saxReader对象
        SAXReader reader = new SAXReader();
        // 通过read方法读取一个文件 转换成Document对象
        Document document = null;
        try {
            if (resource == null || resource.getFile() == null) {
                throw new NoSuchFileException("没有找到xml文件");
            }
            document = reader.read(resource.getFile());
        } catch (DocumentException | NoSuchFileException e) {
            e.printStackTrace();
        }
        //获取根节点元素
        assert document != null;
        Element node = document.getRootElement();
        //获取所有的bean
        List<Element> elementsList = node.elements();
        return elementsList;
    }

}
