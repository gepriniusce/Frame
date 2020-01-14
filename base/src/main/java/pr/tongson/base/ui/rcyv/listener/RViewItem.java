package pr.tongson.base.ui.rcyv.listener;


import pr.tongson.base.ui.rcyv.holder.RViewHolder;

/**
 * <b>Create Date:</b> 2019-04-23<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public interface RViewItem<T> {
    int getItemLayout();

    boolean openClick();

    boolean isItemView(T entity, int position);

    void convert(RViewHolder holder, T entity);
}
