package pr.tongson.module_main.debug;

import android.content.Context;

import androidx.multidex.MultiDex;
import pr.tongson.BaseApplication;
import pr.tongson.base.skin.core.SkinManager;
import pr.tongson.library.utils.L;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/3/29
 * @Version
 * @Since
 * @Description
 */
public class MyAloneApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        long startTime = System.currentTimeMillis();
        SkinManager.init(this);
        long endTime = System.currentTimeMillis();
        L.i("SkinManager初始化---------------------------------耗时：" + (endTime - startTime));
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //分包=>适配低版本的手机
        MultiDex.install(base);
    }
}
