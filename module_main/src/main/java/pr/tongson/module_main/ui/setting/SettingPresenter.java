package pr.tongson.module_main.ui.setting;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatDelegate;
import pr.tongson.base.mvp.BaseMVPPresenterImpl;
import pr.tongson.base.recycler.item.BaseRViewItem;
import pr.tongson.base.recycler.listener.IOnItemChildrenListener;
import pr.tongson.module_main.R;
import pr.tongson.module_main.ui.setting.viewitem.CacheViewItem;
import pr.tongson.module_main.ui.setting.viewitem.DividerViewItem;
import pr.tongson.module_main.ui.setting.viewitem.SkinViewItem;
import pr.tongson.module_main.ui.setting.viewitem.bean.CacheBean;
import pr.tongson.module_main.ui.setting.viewitem.bean.DividerBean;
import pr.tongson.module_main.ui.setting.viewitem.bean.SettingListBean;
import pr.tongson.module_main.ui.setting.viewitem.bean.SkinBean;

/**
 * <b>Create Date:</b> 2020/3/28<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class SettingPresenter extends BaseMVPPresenterImpl<SettingContract.View> implements SettingContract.Presenter, IOnItemChildrenListener {

    @Override
    public List<SettingListBean> getItemList() {
        List<SettingListBean> SettingListBeans = new ArrayList<>();

        SettingListBean settingListBean0 = new SettingListBean();
        SkinBean skin = new SkinBean();
        skin.setIconId(R.drawable.main_ic_skin_mode_24dp);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            skin.setTitleName("日间模式");
        } else {
            skin.setTitleName("夜间模式");
        }
        skin.setModeNight("切换" + AppCompatDelegate.getDefaultNightMode());
        settingListBean0.setSkinBean(skin);

        SettingListBean divider = new SettingListBean();
        divider.setDividerBean(new DividerBean());

        SettingListBean settingListBean2 = new SettingListBean();
        CacheBean cache = new CacheBean();
        cache.setIconId(R.drawable.main_ic_clean_cache_24dp);
        cache.setTitleName("清除缓存");
        cache.setCacheMsg("" + mView.getCacheMsg());
        settingListBean2.setCacheBean(cache);

        SettingListBeans.add(settingListBean0);
        SettingListBeans.add(divider);
        SettingListBeans.add(settingListBean2);

        return SettingListBeans;
    }

    @Override
    public BaseRViewItem[] getItemTypes() {
        BaseRViewItem[] itemTypes = new BaseRViewItem[3];
        itemTypes[0] = new SkinViewItem(this);
        itemTypes[1] = new DividerViewItem();
        itemTypes[2] = new CacheViewItem();
        return itemTypes;
    }

    static boolean b = false;

    @Override
    public void changeNightMode() {
        if (b) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            b = false;
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            b = true;
        }
        mView.refreshAdapter();
    }

    @Override
    public void onItemClick() {
        mView.changeNightMode();
    }
}
