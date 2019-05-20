import me.szp.framework.spring.context.AnnotationApplicationContext;
import me.szp.framework.spring.context.ApplicationContext;
import me.szp.framework.spring.context.XmlApplicationContext;
import org.junit.Test;
import test.beans.Student;
import test.beans.User;

/**
 * @author Sun Zhi Peng
 * @version 1.0.0
 * @date 2019/5/13 11:31
 */
public class TestApplicationContext {

    @Test
    public void testXmlApplicationContext() throws Throwable {
        ApplicationContext applicationContext = new XmlApplicationContext("classpath:/application.xml");
//        User user = (User) applicationContext.getBean("user");

//        System.out.println(user);
    }

    @Test
    public void testAnnotationApplicationContext() throws Throwable {

        ApplicationContext applicationContext = new AnnotationApplicationContext("test");

        Student student = (Student) applicationContext.getBean("student");

        System.out.println(student);

    }
}
