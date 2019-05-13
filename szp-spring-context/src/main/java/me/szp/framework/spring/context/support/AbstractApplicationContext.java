package me.szp.framework.spring.context.support;

import me.szp.framework.spring.beans.factory.BeanFactory;
import me.szp.framework.spring.beans.factory.config.BeanPostProcessor;
import me.szp.framework.spring.beans.factory.PreBuildBeanFactory;
import me.szp.framework.spring.context.ApplicationContext;

/**
 * 无论是XmlApplicationContext还是AnnotationApplicationContext都要
 * 使用BeanFactory和BeanDefinitionRegistry,所以对他们再进行一次抽象
 *
 * @author GhostDog
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    protected BeanFactory beanFactory;

    public AbstractApplicationContext() {
        super();
        this.beanFactory = new PreBuildBeanFactory();
    }


    @Override
    public Object getBean(String name) throws Throwable {
        return beanFactory.getBean(name);
    }
    
    @Override
    public void registerBeanPostProcessor(BeanPostProcessor bpp) {
        this.beanFactory.registerBeanPostProcessor(bpp);
    }

}
