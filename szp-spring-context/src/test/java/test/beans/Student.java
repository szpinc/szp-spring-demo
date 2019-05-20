package test.beans;

import me.szp.framework.spring.context.annotation.Component;
import me.szp.framework.spring.context.annotation.Value;

/**
 * @author Sun Zhi Peng
 * @version 1.0.0
 * @date 2019/5/15 10:19
 */
@Component
public class Student {

    @Value("GhostDog")
    private String name;
    @Value("18")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setAge(int age) {
        this.age = age;
    }
}
