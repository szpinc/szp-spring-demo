import me.szp.framework.spring.beans.factory.DefaultBeanFactory;
import me.szp.framework.spring.beans.factory.config.BeanDefinition;
import me.szp.framework.spring.beans.factory.support.GenericBeanDefinition;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * @author leeSmall
 * @Description: 测试IOC容器（bean工厂）创建bean实例ABean
 * @date 2018年11月29日
 */
public class DefaultBeanFactoryTest {

    static DefaultBeanFactory bf = new DefaultBeanFactory();

    //测试构造方法方式创建bean实例
    @Test
    public void testRegist() throws Throwable {

        //创建bean定义
        GenericBeanDefinition bd = new GenericBeanDefinition();

        //设置bean的类名
        bd.setBeanClass(User.class);
        //设置是否单例
        bd.setScope(BeanDefinition.SCOPE_SINGLETON);
        // bd.setScope(BeanDefinition.SCOPE_PROTOTYPE);

        //设置bean的初始化方法
        bd.setInitMethodName("init");
        //设置bean的销毁方法
        bd.setDestroyMethodName("destroy");

        //把bean定义注册到bean工厂DefaultBeanFactory bf
        bf.registerBeanDefinition("user", bd);

        User user = (User)bf.getBean("user");

        System.out.println(user);


    }

    //静态工厂方法的方式创建bean实例
    @Test
    public void testRegistStaticFactoryMethod() throws Exception {
        //创建bean定义
        GenericBeanDefinition bd = new GenericBeanDefinition();
        //设置工厂bean的名字
        bd.setBeanClass(UserFactory.class);
        //设置工厂方法名
        bd.setFactoryMethodName("getUser");
        //把bean定义注册到bean工厂DefaultBeanFactory bf
        bf.registerBeanDefinition("staticUser", bd);
    }

    //工厂bean的方式创建bean实例
    @Test
//    public void testRegistFactoryMethod() throws Exception {
//        //创建工厂bean定义
//        GenericBeanDefinition bd = new GenericBeanDefinition();
//        //设置工厂bean的名字
//        bd.setBeanClass(ABeanFactory.class);
//        String fbname = "factory";
//        //把工厂bean注册到bean工厂DefaultBeanFactory bf
//        bf.registerBeanDefinition(fbname, bd);
//
//        //创建bean定义
//        bd = new GenericBeanDefinition();
//        //设置工厂bean的名字
//        bd.setFactoryBeanName(fbname);
//        //设置工厂bean的方法名
//        bd.setFactoryMethodName("getABean2");
//        //设置是否是单列
//        bd.setScope(BeanDefinition.SCOPE_PROTOTYPE);
//
//        //把bean定义注册到bean工厂DefaultBeanFactory bf
//        bf.registerBeanDefinition("factoryAbean", bd);
//    }

    //获取bean实例并调用里面的方法
//    @AfterClass
    public void testGetBean() throws Throwable {
        System.out.println("构造方法方式------------");
        User user = (User)bf.getBean("user");

        System.out.println(user.getUsername());

//        System.out.println("静态工厂方法方式------------");
//        for (int i = 0; i < 3; i++) {
//            ABean ab = (ABean) bf.getBean("staticAbean");
//            ab.doSomthing();
//        }
//
//        System.out.println("工厂方法方式------------");
//        for (int i = 0; i < 3; i++) {
//            ABean ab = (ABean) bf.getBean("factoryAbean");
//            ab.doSomthing();
//        }

        //销毁
        bf.close();
    }
}
