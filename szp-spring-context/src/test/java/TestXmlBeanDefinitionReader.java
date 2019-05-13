import me.szp.framework.core.io.ClassPathResource;
import me.szp.framework.core.io.Resource;
import me.szp.framework.spring.beans.factory.DefaultBeanFactory;
import me.szp.framework.spring.beans.factory.config.BeanDefinition;
import me.szp.framework.spring.context.XmlBeanDefinitionReader;
import org.junit.Test;
import test.beans.User;

import java.io.File;

/**
 * @author Sun Zhi Peng
 * @version 1.0.0
 * @date 2019/5/13 13:50
 */
public class TestXmlBeanDefinitionReader {


    @Test
    public void testParseXml() throws Throwable {

        DefaultBeanFactory beanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
        definitionReader.loadBeanDefinitions(new ClassPathResource("/application.xml"));

        User user = (User) beanFactory.getBean("user");

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("user");

        System.out.println(beanDefinition);

        System.out.println(user);


    }

}
