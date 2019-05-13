package me.szp.framework.spring.context;

import me.szp.framework.core.io.Resource;
import me.szp.framework.spring.beans.factory.support.BeanDefinitionRegistry;
import me.szp.framework.spring.context.support.AbstractApplicationContext;

import java.io.IOException;

/**
 * 扫描指定包下的类(包含子孙包下的类), 得到Resource
 * 通过反射获取bean定义信息、创建bean定义对象、注册bean定义对象到bean工厂
 *
 * @author GhostDog
 */
public class AnnotationApplicationContext extends AbstractApplicationContext {

    public AnnotationApplicationContext(String... basePackages) throws Throwable {
        //扫描指定包下的类(包含子孙包下的类),
        //通过反射获取bean定义信息、创建bean定义对象、注册bean定义对象到bean工厂
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner((BeanDefinitionRegistry) this.beanFactory);
        scanner.scan(basePackages);
    }

    @Override
    public Resource getResource(String location) throws IOException {
        return null;
    }

}
