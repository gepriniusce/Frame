package pr.tongson.base.ui;

import android.app.Application;

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
}
