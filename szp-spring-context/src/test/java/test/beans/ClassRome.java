package test.beans;

import me.szp.framework.spring.beans.factory.annotation.Autowired;
import me.szp.framework.spring.stereotype.Component;

/**
 * @author Sun Zhi Peng
 * @version 1.0.0
 * @date 2019/5/20 18:07
 */

@Component
public class ClassRome {

    @Autowired
    private Student student;


    public Student getStudent() {
        return student;
    }
}
