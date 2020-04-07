package pr.tongson.base.recycler.item;

import pr.tongson.base.recycler.listener.IBaseRViewItem;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/3/29
 * @Version
 * @Since
 * @Description
 */
public abstract class BaseRViewItem<T> implements IBaseRViewItem<T> {

    private int itemViewType;

    public int getItemViewType() {
        return itemViewType;
    }

    public void setItemViewType(int itemViewType) {
        this.itemViewType = itemViewType;
    }
}
