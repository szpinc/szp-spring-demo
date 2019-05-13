package me.szp.framework.spring.context;

import me.szp.framework.core.io.ResourceLoader;
import me.szp.framework.spring.beans.factory.BeanFactory;

/**
 * 
 * 加载xml配置文件和扫描包的接口
 * 在XmlApplicationContext和AnnotationApplicationContext里面实现
 *
 * @author GhostDog
 */
public interface ApplicationContext extends ResourceLoader, BeanFactory {

}
