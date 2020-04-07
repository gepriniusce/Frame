package pr.tongson.module_main.ui.setting.viewitem;

import pr.tongson.base.recycler.holder.RViewHolder;
import pr.tongson.base.recycler.item.BaseRViewItem;
import pr.tongson.module_main.R;
import pr.tongson.module_main.ui.setting.viewitem.bean.SettingListBean;

/**
 * <b>Create Date:</b> 2020/3/28<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class DividerViewItem extends BaseRViewItem<SettingListBean> {

    @Override
    public int getItemLayout() {
        return R.layout.main_divider;
    }

    @Override
    public void initView(RViewHolder holder) {
    }

    @Override
    public boolean openClick() {
        return false;
    }

    @Override
    public boolean isItemView(SettingListBean entity, int position) {
        if (entity.getDividerBean() != null) {
            return true;
        }
        return false;
    }

    @Override
    public void convert(RViewHolder holder, SettingListBean entity) {
    }
}
