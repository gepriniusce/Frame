package pr.tongson.base.recycler.holder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import pr.tongson.base.recycler.item.BaseRViewItem;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <b>Create Date:</b> 2019-04-23<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class RViewHolder extends RecyclerView.ViewHolder {

    /**
     * 稀疏的数组
     */
    SparseArray<View> mViews;
    /**
     *
     */
    private View mContentView;

    private BaseRViewItem mViewItem;

    /**
     * 1中ViewHolder对应一种ItemViewType对应一种ViewItem
     */
    private int mItemViewType;

    /**
     * @param parent
     * @param viewItem
     * @return
     */
    public static RViewHolder createViewHolder(ViewGroup parent, BaseRViewItem viewItem) {
        View contentView = LayoutInflater.from(parent.getContext()).inflate(viewItem.getItemLayout(), parent, false);
        return new RViewHolder(contentView,viewItem);
    }

    public RViewHolder(@NonNull View contentView, BaseRViewItem viewItem) {
        super(contentView);
        mContentView = contentView;
        this.mViewItem=viewItem;
        mViews = new SparseArray<>();
        viewItem.initView(this);
    }

    /**
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T findViewById(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mContentView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * @return
     */
    public View getContentView() {
        return mContentView;
    }

    public BaseRViewItem getViewItem() {
        return mViewItem;
    }
}
