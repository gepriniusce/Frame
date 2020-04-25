package pr.tongson.module_main.ui.setting.viewitem;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pr.tongson.base.recycler.holder.RViewHolder;
import pr.tongson.base.recycler.item.BaseRViewItem;
import pr.tongson.base.recycler.listener.IOnItemChildrenListener;
import pr.tongson.base.utils.LiveDataUtils;
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
public class SkinViewItem extends BaseRViewItem<SettingListBean> {
    private ImageView mItemIcon;
    private TextView mItemTitle;
    private TextView mItemModeNight;

    private IOnItemChildrenListener mIOnItemChildrenListener;

    public SkinViewItem(IOnItemChildrenListener IOnItemChildrenListener) {
        mIOnItemChildrenListener = IOnItemChildrenListener;
    }

    @Override
    public int getItemLayout() {
        return R.layout.main_item_setting_skin;
    }

    @Override
    public void initView(RViewHolder holder) {
        mItemIcon = (ImageView) holder.findViewById(R.id.item_icon);
        mItemTitle = (TextView) holder.findViewById(R.id.item_title);
        mItemModeNight = (TextView) holder.findViewById(R.id.item_mode_night);

        mItemModeNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveDataUtils.getInstance().post("Skin","dsalfjdska");

                if (mIOnItemChildrenListener != null && openClick()) {
                    mIOnItemChildrenListener.onItemClick();
                }
            }
        });
    }

    @Override
    public boolean openClick() {
        return true;
    }

    @Override
    public boolean isItemView(SettingListBean entity, int position) {
        if (entity.getSkinBean() != null) {
            return true;
        }
        return false;
    }

    @Override
    public void convert(RViewHolder holder, SettingListBean entity) {
        mItemIcon.setImageResource(entity.getSkinBean().getIconId());
        mItemTitle.setText(entity.getSkinBean().getTitleName());
        mItemModeNight.setText(entity.getSkinBean().getModeNight());
    }
}
