package pr.tongson.module_usersystem.bean;

import cn.bmob.v3.BmobObject;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/4/10
 * @Version
 * @Since
 * @Description
 */
public class Person extends BmobObject {
    private String username;
    private String password;

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
}
