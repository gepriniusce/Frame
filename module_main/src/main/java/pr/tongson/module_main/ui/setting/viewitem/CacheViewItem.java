package pr.tongson.module_main.ui.setting.viewitem;

import android.widget.ImageView;
import android.widget.TextView;

import pr.tongson.base.recycler.holder.RViewHolder;
import pr.tongson.base.recycler.item.BaseRViewItem;
import pr.tongson.library.cache.ACache;
import pr.tongson.module_main.R;
import pr.tongson.module_main.ui.setting.viewitem.bean.SettingListBean;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/3/29
 * @Version
 * @Since
 * @Description
 */
public class CacheViewItem extends BaseRViewItem<SettingListBean> {
    private ImageView mItemIcon;
    private TextView mItemTitle;
    private TextView mItemCacheValue;
    private TextView mItemExtraMsg;

    @Override
    public int getItemLayout() {
        return R.layout.main_item_setting_cache;
    }

    @Override
    public void initView(RViewHolder holder) {
        mItemIcon = (ImageView) holder.findViewById(R.id.item_icon);
        mItemTitle = (TextView) holder.findViewById(R.id.item_title);
        mItemCacheValue = (TextView) holder.findViewById(R.id.item_cache_value);
        mItemExtraMsg = (TextView) holder.findViewById(R.id.item_extra_msg);
    }

    @Override
    public boolean openClick() {
        return true;
    }

    @Override
    public boolean isItemView(SettingListBean entity, int position) {
        if (entity.getCacheBean() != null) {
            return true;
        }
        return false;
    }

    @Override
    public void convert(RViewHolder holder, SettingListBean entity) {
        mItemIcon.setImageResource(entity.getCacheBean().getIconId());
        mItemTitle.setText(entity.getCacheBean().getTitleName());
        mItemCacheValue.setText(entity.getCacheBean().getCacheMsg());
        mItemExtraMsg.setText(ACache.PATH_CACHE);
    }

}
