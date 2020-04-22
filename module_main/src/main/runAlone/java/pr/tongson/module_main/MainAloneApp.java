package pr.tongson.module_main;

import com.xiaojinzi.component.Component;
import com.xiaojinzi.component.Config;
import com.xiaojinzi.component.impl.application.ModuleManager;
import com.xiaojinzi.component.support.LogUtil;
import com.xiaojinzi.component.support.RxErrorIgnoreUtil;

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
public class MainAloneApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initSkin();
        initRouter();
    }

    private void initSkin() {
        long startTime = System.currentTimeMillis();
        SkinManager.init(this);
        long endTime = System.currentTimeMillis();
        L.i("SkinManager初始化---------------------------------耗时：" + (endTime - startTime));
    }

    private void initRouter() {
        long startTime = System.currentTimeMillis();

        Component.init(BuildConfig.DEBUG, Config.with(this).
                build());

        RxErrorIgnoreUtil.ignoreError();
        ModuleManager.getInstance().registerArr("moduleMain","moduleIm","modulePlayer");

        if (BuildConfig.DEBUG) {
            ModuleManager.
                    getInstance().
                    check();
        }

        long endTime = System.currentTimeMillis();
        L.i(BuildConfig.DEBUG + "组件化初始化---------------------------------耗时：" + (endTime - startTime));

    }

}
