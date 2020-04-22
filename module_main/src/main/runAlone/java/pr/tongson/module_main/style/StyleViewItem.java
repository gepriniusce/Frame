package pr.tongson.module_main.style;

import android.text.TextUtils;
import android.widget.TextView;

import pr.tongson.base.recycler.holder.RViewHolder;
import pr.tongson.base.recycler.item.BaseRViewItem;
import pr.tongson.module_main.R;
import pr.tongson.module_main.ui.home.HomeListBean;

/**
 * <b>Create Date:</b> 2020/3/28<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class StyleViewItem extends BaseRViewItem<HomeListBean> {

    private TextView mTvTitle;

    @Override
    public int getItemLayout() {
        return R.layout.main_item_home_client;
    }

    @Override
    public void initView(RViewHolder holder) {
        mTvTitle = (TextView) holder.findViewById(R.id.tv_title);
    }

    @Override
    public boolean openClick() {
        return true;
    }

    @Override
    public boolean isItemView(HomeListBean entity, int position) {
        return true;
    }

    @Override
    public void convert(RViewHolder holder, HomeListBean entity) {
        if (!TextUtils.isEmpty(entity.getModuleName())) {
            mTvTitle.setText(entity.getModuleName());
        }
    }

}
