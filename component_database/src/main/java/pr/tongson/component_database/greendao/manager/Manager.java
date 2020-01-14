package pr.tongson.component_database.greendao.manager;

import android.app.Activity;
import android.content.Context;

import pr.tongson.component_database.greendao.dao.DaoMaster;
import pr.tongson.component_database.greendao.dao.DaoSession;
import pr.tongson.component_database.greendao.helper.Helper;


/**
 * <b>Create Date:</b> 2019/2/10<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class Manager {

    private static String KEY_DB_NAME = "TongsonApp.db";
    public static final String PWD = "TONGSON_APP_DATEBASE";
    // volatile关键字保证了：① instance实例对于所有线程都是可见的 ② 禁止了instance
    // 操作指令重排序。
    private static volatile Manager INSTANCE;
    private DaoSession session;
    private DaoMaster daoMaster;

    private Manager(Context context) {
        //防止持有Activity的对象，造成内存泄漏
        if (context instanceof Activity) {
            context = ((Activity) context).getApplicationContext();
        }
        Helper helper = new Helper(context, KEY_DB_NAME);
        //采用了加密的数据库
        daoMaster = new DaoMaster(helper.getEncryptedWritableDb(PWD));
        session = daoMaster.newSession();
    }

    /**
     * 还有一种常见的单例模式： 就是静态内部类的初始化  没事自己看去哦
     *
     * @param context
     * @return
     */
    public static Manager getInstance(Context context) {
        //return SingleHolder.INSTANCE;
        // 第一次校验，防止不必要的同步。
        if (INSTANCE == null) {
            // synchronized关键字加锁，保证每次只有一个线程执行对象初始化操作
            synchronized (Manager.class) {
                if (INSTANCE == null) {
                    // 第二次校验，进行判空，如果为空则执行初始化
                    INSTANCE = new Manager(context);
                }
            }
        }
        return INSTANCE;
    }

    public DaoSession getSession() {
        return session;
    }

    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

}
