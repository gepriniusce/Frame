package pr.tongson.base.skin.core;

import android.app.Application;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import pr.tongson.base.skin.model.SkinCache;
import pr.tongson.library.utils.L;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/3/29
 * @Version
 * @Since
 * @Description 皮肤管理器
 * 加载应用资源（app内置：res/xxx） or 存储资源（下载皮肤包）
 */
public class SkinManager {
    private static SkinManager ourInstance;
    private Application application;
    /**
     * 用于加载app内置资源
     */
    private Resources appResources;
    /**
     * 用于加载皮肤包资源
     */
    private Resources skinResources;
    /**
     * 皮肤包资源所在包名（注：皮肤包不在app内，也不限包名）
     */
    private String skinPackageName;
    /**
     * 应用默认皮肤（app内置）
     * 皮肤包>日夜模式（DayNightMode）>原先资源
     */
    private volatile boolean isDefaultSkin = true;

    /**
     * 方法名
     */
    private static final String ADD_ASSET_PATH = "addAssetPath";
    private Map<String, SkinCache> cacheSkin;

    /**
     * 单例方法，目的是初始化app内置资源（越早越好，用户的操作可能是：换肤后的第2次冷启动）
     */
    public static void init(Application application) {
        if (ourInstance == null) {
            synchronized (SkinManager.class) {
                if (ourInstance == null) {
                    ourInstance = new SkinManager(application);
                }
            }
        }
    }

    private SkinManager(Application application) {
        this.application = application;
        appResources = application.getResources();
        cacheSkin = new HashMap<>();
    }

    public static SkinManager getInstance() {
        return ourInstance;
    }

    /**
     * 加载皮肤包资源
     *
     * @param skinPath 皮肤包路径，为空则加载app内置资源
     */
    public void loaderSkinResources(String skinPath) {
        // 优化：如果没有皮肤包或者没做换肤动作，方法不执行直接返回！
        if (TextUtils.isEmpty(skinPath)) {
            isDefaultSkin = true;
            return;
        }

        // 优化：app冷启动、热启动可以取缓存对象
        if (cacheSkin.containsKey(skinPath)) {
            isDefaultSkin = false;
            SkinCache skinCache = cacheSkin.get(skinPath);
            if (null != skinCache) {
                skinResources = skinCache.getSkinResources();
                skinPackageName = skinCache.getSkinPackageName();
                return;
            }
        }

        try {
            // 创建资源管理器（此处不能用：application.getAssets()）
            AssetManager assetManager = AssetManager.class.newInstance();
            // 由于AssetManager中的addAssetPath和setApkAssets方法都被@hide，目前只能通过反射去执行方法
            Method addAssetPath = assetManager.getClass().getDeclaredMethod(ADD_ASSET_PATH, String.class);
            // 设置私有方法可访问
            addAssetPath.setAccessible(true);
            // 执行addAssetPath方法
            addAssetPath.invoke(assetManager, skinPath);
            //==============================================================================
            // 如果还是担心@hide限制，可以反射addAssetPathInternal()方法，参考源码366行 + 387行
            //==============================================================================

            // 创建加载外部的皮肤包(xxx.skin)文件Resources（注：依然是本应用加载）
            skinResources = new Resources(assetManager, appResources.getDisplayMetrics(), appResources.getConfiguration());

            // 根据apk文件路径（皮肤包也是apk文件），获取该应用的包名。兼容5.0 - 9.0（亲测）
            skinPackageName = application.getPackageManager().getPackageArchiveInfo(skinPath, PackageManager.GET_ACTIVITIES).packageName;

            // 无法获取皮肤包应用的包名，则加载app内置资源
            isDefaultSkin = TextUtils.isEmpty(skinPackageName);
            if (!isDefaultSkin) {
                cacheSkin.put(skinPath, new SkinCache(skinResources, skinPackageName));
            }


        } catch (Exception e) {
            e.printStackTrace();
            // 发生异常，预判：通过skinPath获取skinPacakageName失败！
            isDefaultSkin = true;
        }
    }

    //==============================================================================================

    /**
     * 参考：resources.arsc资源映射表
     * 通过ID值获取资源 Name 和 Type
     *
     * @param resourceId 资源ID值
     * @return 如果没有皮肤包则加载app内置资源ID，反之加载皮肤包指定资源ID
     */
    private int getSkinResourceIds(int resourceId) {
        // 优化：如果没有皮肤包或者没做换肤动作，直接返回app内置资源！
        if (isDefaultSkin) {
            return resourceId;
        }
        // 使用app内置资源加载，是因为内置资源与皮肤包资源一一对应（“netease_bg”, “drawable”）
        String resName = appResources.getResourceEntryName(resourceId);
        String resType = appResources.getResourceTypeName(resourceId);
        // 动态获取皮肤包内的指定资源ID
        int skinResourceId = skinResources.getIdentifier(resName, resType, skinPackageName);
        // 源码1924行：(0 is not a valid resource ID.)
        //        isDefaultSkin = skinResourceId == 0;
        //        return skinResourceId == 0 ? resourceId : skinResourceId;
        return skinResourceId;
    }

    //==============================================================================================


    public int getColor(int resourceId) {
        int ids = getSkinResourceIds(resourceId);
        if (ids == 0) {
            return appResources.getColor(resourceId);
        } else {
            return skinResources.getColor(ids);
        }
        //        return isDefaultSkin ? appResources.getColor(ids) : skinResources.getColor(ids);
    }

    public ColorStateList getColorStateList(int resourceId) {
        int ids = getSkinResourceIds(resourceId);
        if (ids == 0) {
            return appResources.getColorStateList(resourceId);
        } else {
            return skinResources.getColorStateList(ids);
        }
        //        return isDefaultSkin ? appResources.getColorStateList(ids) : skinResources.getColorStateList(ids);
    }

    /**
     * mipmap和drawable统一用法（待测）
     */
    public Drawable getDrawableOrMipMap(int resourceId) {
        int ids = getSkinResourceIds(resourceId);
        if (ids == 0) {
            //如果从外置皮肤包获取不到资源则获取本地资源
            return appResources.getDrawable(resourceId);
        } else {
            return skinResources.getDrawable(ids);
        }
        //        return isDefaultSkin ? appResources.getDrawable(ids) : skinResources.getDrawable(ids);
    }

    public String getString(int resourceId) {
        int ids = getSkinResourceIds(resourceId);
        if (ids == 0) {
            return appResources.getString(resourceId);
        } else {
            return skinResources.getString(ids);
        }
        //return isDefaultSkin ? appResources.getString(ids) : skinResources.getString(ids);
    }

    /**
     * 返回值特殊情况：可能是color / drawable / mipmap
     */
    public Object getBackgroundOrSrc(int resourceId) {
        // 需要获取当前属性的类型名Resources.getResourceTypeName(resourceId)再判断
        //String resName = appResources.getResourceEntryName(resourceId);
        String resType = appResources.getResourceTypeName(resourceId);
        switch (resType) {
            case "color":
                return getColor(resourceId);
            case "mipmap":
            case "drawable":
                return getDrawableOrMipMap(resourceId);
            default:
                return null;
        }
    }

    /**
     * 获得字体
     *
     * @param resourceId
     * @return
     */
    public Typeface getTypeface(int resourceId) {
        // 通过资源ID获取资源path，参考：resources.arsc资源映射表
        String skinTypefacePath = getString(resourceId);
        // 路径为空，使用系统默认字体
        if (TextUtils.isEmpty(skinTypefacePath)) {
            return Typeface.DEFAULT;
        }
        return isDefaultSkin ? Typeface.createFromAsset(appResources.getAssets(), skinTypefacePath) : Typeface.createFromAsset(skinResources.getAssets(), skinTypefacePath);
    }

    public synchronized boolean isDefaultSkin() {
        return isDefaultSkin;
    }
}
