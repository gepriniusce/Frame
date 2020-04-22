package pr.tongson.module_main.ui.setting;

import java.util.List;

import pr.tongson.base.mvp.BaseMVPPresenter;
import pr.tongson.base.mvp.BaseMVPView;
import pr.tongson.base.recycler.item.BaseRViewItem;
import pr.tongson.module_main.ui.setting.viewitem.bean.SettingListBean;

/**
 * @author quchao
 * @date 2018/4/2
 */

public interface SettingContract {

    interface View extends BaseMVPView {
        void changeNightMode();

        void clearCache();

        String getCacheMsg();

        void refreshAdapter();
    }

    interface Presenter extends BaseMVPPresenter<View> {

        List<SettingListBean> getItemList();

        BaseRViewItem[] getItemTypes();

        void changeNightMode();
    }
}
