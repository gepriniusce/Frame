package pr.tongson.module_main.ui.home;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatDelegate;
import pr.tongson.base.mvp.BaseMVPPresenterImpl;
import pr.tongson.base.recycler.item.BaseRViewItem;
import pr.tongson.base.recycler.listener.IOnItemChildrenListener;
import pr.tongson.module_main.R;
import pr.tongson.module_main.ui.setting.SettingContract;
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
public class HomePresenter extends BaseMVPPresenterImpl<HomeContract.View> implements HomeContract.Presenter {

    @Override
    public List<HomeListBean> getItemList() {
        List<HomeListBean> homeListBeans = new ArrayList<>();

        HomeListBean colorStyle = new HomeListBean();
        colorStyle.setModuleName("Style");
        colorStyle.setHostAndPath("moduleMain/Style");
        homeListBeans.add(colorStyle);

        HomeListBean skin = new HomeListBean();
        skin.setModuleName("Skin");
        skin.setHostAndPath("moduleMain/Skin");
        homeListBeans.add(skin);

        HomeListBean im = new HomeListBean();
        im.setModuleName("im");
        im.setHostAndPath("moduleIm/im");
        homeListBeans.add(im);

        HomeListBean player = new HomeListBean();
        player.setModuleName("player");
        player.setHostAndPath("modulePlayer/Player");
        homeListBeans.add(player);

        for (int i = 0; i < 10; i++) {
            HomeListBean settingListBean0 = new HomeListBean();
            settingListBean0.setModuleName("");
            settingListBean0.setHostAndPath("");
            homeListBeans.add(settingListBean0);
        }
        return homeListBeans;
    }

    @Override
    public BaseRViewItem[] getItemTypes() {
        BaseRViewItem[] itemTypes = new BaseRViewItem[1];
        itemTypes[0] = new HomeViewItem();
        return itemTypes;
    }

}
