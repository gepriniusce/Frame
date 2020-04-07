package pr.tongson;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

/**
 * <b>Create Date:</b> 2019-05-15<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class BaseApplication extends Application {


    private static Application mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }


    public static Application getApp() {
        return mApp;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //分包=>适配低版本的手机
        MultiDex.install(base);
    }
}
