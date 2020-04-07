package pr.tongson.base.recycler.listener;

import android.view.View;

/**
 * <b>Create Date:</b> 2019-04-27<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 * 顾名思义，这个不解释了
 *
 * @author tongson
 */
public interface IOnItemListener<T> {
    /**
     * @param view
     * @param entity
     * @param position
     */
    void onItemClick(View view, T entity, int position);

    /**
     * @param view
     * @param entity
     * @param position
     * @return
     */
    boolean onItemLongClick(View view, T entity, int position);
}
