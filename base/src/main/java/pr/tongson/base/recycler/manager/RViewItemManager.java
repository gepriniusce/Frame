package pr.tongson.base.recycler.manager;

import androidx.collection.SparseArrayCompat;

import pr.tongson.base.recycler.item.BaseRViewItem;

/**
 * <b>Create Date:</b> 2019-04-23<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 * <p>
 * ItemViewType的管理者
 *
 * @author tongson
 */
public class RViewItemManager<T> {
    /**
     *
     */
    private SparseArrayCompat<BaseRViewItem<T>> mViewItemStyles = new SparseArrayCompat<>();

    /**
     * @param item
     */
    public void addStyle(BaseRViewItem<T> item) {
        if (item != null) {
            item.setItemViewType(mViewItemStyles.size());
            mViewItemStyles.put(mViewItemStyles.size(), item);
        }
    }

    /**
     * @return
     */
    public int getCount() {
        return mViewItemStyles.size();
    }

    /**
     * @param viewType
     * @return
     */
    public BaseRViewItem getRViewItem(int viewType) {
        return mViewItemStyles.get(viewType);
    }


    /**
     * 通过entity跟position得到ItemViewType
     *
     * @param entity
     * @param position
     * @return
     */
    public int getItemViewType(T entity, int position) {
        for (int i = mViewItemStyles.size() - 1; i >= 0; i--) {
            BaseRViewItem<T> item = mViewItemStyles.valueAt(i);

            //            if (item.getItemViewType() ==entity/position) {
            //                return mViewItemStyles.keyAt(i);
            //            }

            //position应该与Style一一对应
            if (item.isItemView(entity, position)) {
                return mViewItemStyles.keyAt(i);
            }
        }
        throw new IllegalArgumentException("该位置没有匹配的条目getItemViewType");
    }
}
