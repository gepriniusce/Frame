package pr.tongson.demo_autotrack;

import android.app.Application;

import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;

/**
 * <b>Create Date:</b> 2020-01-08<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class AutotrackApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initSensorsDataAPI(this);
    }

    /**
     * 初始化埋点 SDK
     *
     * @param application Application
     */
    private void initSensorsDataAPI(Application application) {
        SensorsDataAPI.init(application);
    }
}
