package me.szp.framework.spring.context;

import com.google.common.base.CaseFormat;
import me.szp.framework.core.io.Resource;
import me.szp.framework.spring.beans.PropertyValue;
import me.szp.framework.spring.beans.factory.support.AbstractBeanDefinitionReader;
import me.szp.framework.spring.beans.factory.support.BeanDefinitionRegistry;
import me.szp.framework.spring.beans.factory.support.GenericBeanDefinition;
import me.szp.framework.spring.context.annotation.Autowired;
import me.szp.framework.spring.context.annotation.Component;
import me.szp.framework.spring.context.annotation.Value;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 注解bean定义读取器, 从Resource里面加载bean定义、创建bean定义、注册bean定义到bean工厂
 *
 * @author GhostDog
 */
public class AnnotationBeanDefinitionReader extends AbstractBeanDefinitionReader {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AnnotationBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws Throwable {
        this.loadBeanDefinitions(new Resource[]{resource});
    }

    @Override
    public void loadBeanDefinitions(Resource... resource) throws Throwable {
        if (resource != null && resource.length > 0) {
            for (Resource r : resource) {
                retrieveAndResistBeanDefinition(r);
            }
        }
    }

    /**
     * 从Resource里面加载bean定义、创建bean定义、注册bean定义到bean工厂
     *
     * @param resource Resource对象
     * @throws Throwable Throwable
     */
    private void retrieveAndResistBeanDefinition(Resource resource) throws Throwable {
        if (resource != null && resource.getFile() != null) {
            //根据File对象获取className
            String className = getClassNameFromFile(resource.getFile());
            try {
                //反射获取类上面的注解
                Class<?> clazz = Class.forName(className);
                Component component = clazz.getAnnotation(Component.class);
                //从注解里面获取bean定义信息、注册bean定义
                //标注了@Component注解
                if (component != null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("类[{}]标注了Component注解", className);
                    }
                    //从注解里面获取bean定义信息
                    GenericBeanDefinition bd = new GenericBeanDefinition();
                    bd.setBeanClass(clazz);
                    bd.setScope(component.scope());
                    bd.setFactoryMethodName(component.factoryMethodName());
                    bd.setFactoryBeanName(component.factoryBeanName());
                    bd.setInitMethodName(component.initMethodName());
                    bd.setDestroyMethodName(component.destroyMethodName());
                    // 获得所有构造方法，在构造方法上找@Autowired注解，如有，将这个构造方法set到bd;
                    this.handleConstructor(clazz, bd);

                    // 处理工厂方法参数依赖
                    if (StringUtils.isNotBlank(bd.getFactoryMethodName())) {
                        this.handleFactoryMethodArgs(clazz, bd);
                    }
                    // 处理属性依赖
                    this.handlePropertyDi(clazz, bd);
                    String beanName = "".equals(component.value()) ? component.name() : null;

                    if (StringUtils.isEmpty(beanName)) {
                        beanName = StringUtils.uncapitalize(clazz.getSimpleName());
                    }

                    logger.debug("BeanName:[{}]", beanName);
                    // 注册bean定义
                    this.registry.registerBeanDefinition(beanName, bd);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 依赖注入
     *
     * @param clazz
     * @param bd
     */
    private void handlePropertyDi(Class<?> clazz, GenericBeanDefinition bd) {

        Field[] fields = clazz.getDeclaredFields();

        List<PropertyValue> propertyValueList = new ArrayList<>();

        for (Field field : fields) {
            Value value = field.getAnnotation(Value.class);

            if (value != null) {
                String filedValue = value.value();
                propertyValueList.add(new PropertyValue(field.getName(), filedValue));
            }
        }

    }

    private void handleFactoryMethodArgs(Class<?> clazz, GenericBeanDefinition bd) {

    }

    private void handleConstructor(Class<?> clazz, GenericBeanDefinition bd) {
        // 获得所有构造方法，在构造方法上找@Autowired注解，如有，将这个构造方法set到bd;
        Constructor<?>[] cs = clazz.getConstructors();
        if (cs.length > 0) {
            for (Constructor<?> c : cs) {
                if (c.getAnnotation(Autowired.class) != null) {
                    bd.setConstructor(c);
                    Parameter[] ps = c.getParameters();
                    //遍历获取参数上的注解，及创建参数依赖
                    for (Parameter parameter : ps) {

                    }
                    break;
                }
            }
        }
    }

    /**
     * 根据File对象获取class文件类名
     *
     * @param file class文件File对象
     * @return class文件类名
     */
    private String getClassNameFromFile(File file) {
        String absPath = file.getAbsolutePath();
        String absRootPath = new File(this.getClass().getResource("/").getFile()).getAbsolutePath();
        String name = absPath.substring(absRootPath.length() + 1, absPath.indexOf("."));
        //获取class文件类名
        return StringUtils.replace(name, File.separator, ".");
    }

}
