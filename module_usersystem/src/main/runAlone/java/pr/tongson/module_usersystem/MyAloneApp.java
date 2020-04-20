package pr.tongson.module_usersystem;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/4/10
 * @Version
 * @Since
 * @Description
 */
public class MyAloneApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Bmob.initialize(this, "ee413cd274fea347a21ce62ff5fa31db");
    }
}
