package pr.tongson.base.skin.model;

import android.content.res.Resources;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/3/29
 * @Version
 * @Since
 * @Description
 */
public class SkinCache {
    /**
     * 用于加载皮肤包资源
     */
    private Resources skinResources;
    /**
     * 皮肤包资源所在包名（注：皮肤包不在app内，也不限包名）
     */
    private String skinPackageName;

    public SkinCache(Resources skinResources, String skinPackageName) {
        this.skinResources = skinResources;
        this.skinPackageName = skinPackageName;
    }

    public Resources getSkinResources() {
        return skinResources;
    }

    public String getSkinPackageName() {
        return skinPackageName;
    }
}
