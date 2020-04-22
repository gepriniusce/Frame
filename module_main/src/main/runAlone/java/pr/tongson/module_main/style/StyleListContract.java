package pr.tongson.module_main.style;

import java.util.List;

import pr.tongson.base.mvp.BaseMVPPresenter;
import pr.tongson.base.mvp.BaseMVPView;
import pr.tongson.base.recycler.item.BaseRViewItem;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/4/22
 * @Version
 * @Since
 * @Description
 */
public interface StyleListContract {
    interface View extends BaseMVPView {

    }

    interface Presenter extends BaseMVPPresenter<StyleListContract.View> {
        List<StyleListBean> getItemList();

        BaseRViewItem[] getItemTypes();
    }
}
