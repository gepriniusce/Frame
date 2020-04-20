package pr.tongson.module_main.ui.home;

import java.util.List;

import pr.tongson.base.mvp.BaseMVPPresenter;
import pr.tongson.base.mvp.BaseMVPView;
import pr.tongson.base.recycler.item.BaseRViewItem;
import pr.tongson.module_main.ui.setting.viewitem.bean.SettingListBean;

/**
 * @author quchao
 * @date 2018/4/2
 */

public interface HomeContract {

    interface View extends BaseMVPView {

    }

    interface Presenter extends BaseMVPPresenter<View> {
        List<HomeListBean> getItemList();

        BaseRViewItem[] getItemTypes();
    }
}
