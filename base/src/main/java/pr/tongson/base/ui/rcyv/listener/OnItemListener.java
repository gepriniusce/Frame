package pr.tongson.base.ui.rcyv.listener;

import android.view.View;

/**
 * <b>Create Date:</b> 2019-04-27<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public interface OnItemListener<T> {
    void onItemClick(View view, T entity, int position);

    boolean onItemLongClick(View view, T entity, int position);
}
