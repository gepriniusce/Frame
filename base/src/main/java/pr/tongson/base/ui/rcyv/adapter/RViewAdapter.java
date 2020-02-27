package pr.tongson.base.ui.rcyv.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pr.tongson.base.ui.rcyv.holder.RViewHolder;
import pr.tongson.base.ui.rcyv.listener.OnItemListener;
import pr.tongson.base.ui.rcyv.listener.RViewItem;
import pr.tongson.base.ui.rcyv.manager.RViewItemManager;


/**
 * <b>Create Date:</b> 2019-04-23<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class RViewAdapter<T> extends RecyclerView.Adapter<RViewHolder> {

    RViewItemManager<T> mItemStyles;
    OnItemListener<T> mTItemListener;
    List<T> mTList;

    public RViewAdapter(List<T> TList) {
        if (TList == null) {
            mTList = new ArrayList<>();
        } else {
            mTList = TList;
        }
        mItemStyles = new RViewItemManager<>();
    }

    public RViewAdapter(List<T> TList, RViewItem<T> item) {
        if (TList == null) {
            mTList = new ArrayList<>();
        } else {
            mTList = TList;
        }
        mItemStyles = new RViewItemManager<>();
        addItemStyle(item);
    }

    private void addItemStyle(RViewItem<T> item) {
        mItemStyles.addStyle(item);
    }


    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RViewItem item = mItemStyles.getRViewItem(viewType);
        int layoutId = item.getItemLayout();
        RViewHolder holder = RViewHolder.createViewHolder(parent, layoutId);
        if (item.openClick()) {
            setListener(holder);
        }
        return holder;
    }

    private void setListener(final RViewHolder holder) {
        holder.getContentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTItemListener != null) {
                    int position = holder.getAdapterPosition();
                    mTItemListener.onItemClick(v, mTList.get(position), position);
                }
            }
        });
        holder.getContentView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mTItemListener != null) {
                    int position = holder.getAdapterPosition();
                    return mTItemListener.onItemLongClick(v, mTList.get(position), position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolder holder, int position) {
        convert(holder, mTList.get(position));
    }

    private void convert(RViewHolder holder, T entity) {
        mItemStyles.convert(holder, entity);
    }


    @Override
    public int getItemCount() {
        return mTList == null ? 0 : mTList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (hasMultiStyle()) {
            return mItemStyles.getItemViewType(mTList.get(position), position);
        }
        return super.getItemViewType(position);
    }

    private boolean hasMultiStyle() {
        return mItemStyles.getCount() > 0;
    }

    public void setTItemListener(OnItemListener<T> TItemListener) {
        mTItemListener = TItemListener;
    }
}
