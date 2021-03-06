package pr.tongson.base.recycler.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pr.tongson.base.recycler.holder.RViewHolder;
import pr.tongson.base.recycler.item.BaseRViewItem;
import pr.tongson.base.recycler.listener.IOnItemListener;
import pr.tongson.base.recycler.manager.RViewItemManager;
import pr.tongson.library.utils.L;


/**
 * <b>Create Date:</b> 2019-04-23<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class RViewAdapter<T> extends RecyclerView.Adapter<RViewHolder> {

    RViewItemManager<T> mItemStyles;
    IOnItemListener<T> mOnItemListener;
    List<T> mTList;

    public RViewAdapter(List<T> TList) {
        if (TList == null) {
            mTList = new ArrayList<>();
        } else {
            mTList = TList;
        }
        mItemStyles = new RViewItemManager<>();
    }

    public RViewAdapter(List<T> TList, BaseRViewItem<T> ...items) {
        if (TList == null) {
            mTList = new ArrayList<>();
        } else {
            mTList = TList;
        }
        mItemStyles = new RViewItemManager<>();
        for (BaseRViewItem item : items) {
            addItemStyle(item);
        }
    }

    public void addItemStyle(BaseRViewItem<T> item) {
        mItemStyles.addStyle(item);
    }

    @NonNull
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseRViewItem item = mItemStyles.getRViewItem(viewType);
        RViewHolder holder = RViewHolder.createViewHolder(parent, item);
        if (item.openClick()) {
            setListener(holder);
        }
        return holder;
    }

    private void setListener(final RViewHolder holder) {
        holder.getContentView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemListener != null) {
                    int position = holder.getAdapterPosition();
                    mOnItemListener.onItemClick(v, mTList.get(position), position);
                }
            }
        });
        holder.getContentView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemListener != null) {
                    int position = holder.getAdapterPosition();
                    return mOnItemListener.onItemLongClick(v, mTList.get(position), position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull RViewHolder holder, int position) {
        holder.getViewItem().convert(holder,mTList.get(position));
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

    public void setItemListener(IOnItemListener<T> itemListener) {
        mOnItemListener = itemListener;
    }


}
