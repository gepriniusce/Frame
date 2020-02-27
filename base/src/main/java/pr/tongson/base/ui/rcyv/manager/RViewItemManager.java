package pr.tongson.base.ui.rcyv.manager;

import androidx.collection.SparseArrayCompat;

import pr.tongson.base.ui.rcyv.holder.RViewHolder;
import pr.tongson.base.ui.rcyv.listener.RViewItem;

/**
 * <b>Create Date:</b> 2019-04-23<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class RViewItemManager<T> {
    private SparseArrayCompat<RViewItem<T>> mStyles = new SparseArrayCompat<>();

    public void addStyle(RViewItem<T> item) {
        if (item != null) {
            mStyles.put(mStyles.size(), item);
        }
    }

    public int getCount() {
        return mStyles.size();
    }

    public RViewItem getRViewItem(int viewType) {
        return mStyles.get(viewType);
    }

    public int getItemViewType(T entity, int position) {
        for (int i = mStyles.size() - 1; i >= 0; i--) {
            RViewItem<T> item = mStyles.valueAt(i);
            if (item.isItemView(entity, position)) {
                return mStyles.keyAt(i);
            }
        }
        throw new IllegalArgumentException("该位置没有匹配的条目");
    }

    public void convert(RViewHolder holder, T entity) {
        int position = holder.getAdapterPosition();
        for (int i = 0; i < mStyles.size(); i++) {
            RViewItem<T> item = mStyles.valueAt(i);
            if (item.isItemView(entity, position)) {
                item.convert((RViewHolder) holder, entity);
                return;
            }
        }
        throw new IllegalArgumentException("该位置没有匹配的条目");
    }

}
