package me.szp.framework.spring.beans.factory.support;

/**
 * 具体的bean定义读取器抽象
 * XmlBeanDefinitionReader和AnnotationBeanDefinitionReader两个bean定义读取器加载创建bean定义以后
 * 都需要注册bean定义到bean工厂，所以把注册的部分进行了一次抽象
 *
 * @author GhostDog
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    protected BeanDefinitionRegistry registry;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super();
        this.registry = registry;
    }
}
