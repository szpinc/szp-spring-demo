import java.sql.SQLOutput;

/**
 * @author Sun Zhi Peng
 * @version 1.0.0
 * @date 2019/5/13 9:59
 */
public class User {

    private int id;

    private String username;

    private String password;

    public void init() {
        System.out.println("User初始化");
    }

    public void destroy() {
        System.out.println("User销毁");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void sayHello() {
        System.out.println("Hello World");
    }


}
