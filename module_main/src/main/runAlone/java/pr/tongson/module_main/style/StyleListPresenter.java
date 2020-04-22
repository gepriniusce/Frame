package pr.tongson.module_main.style;

import java.util.List;

import pr.tongson.base.mvp.BaseMVPPresenterImpl;
import pr.tongson.base.recycler.item.BaseRViewItem;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/4/22
 * @Version
 * @Since
 * @Description
 */
public class StyleListPresenter extends BaseMVPPresenterImpl<StyleListContract.View> implements StyleListContract.Presenter  {

    @Override
    public List<StyleListBean> getItemList() {
        return null;
    }

    @Override
    public BaseRViewItem[] getItemTypes() {
        return new BaseRViewItem[0];
    }
}
