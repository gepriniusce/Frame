package pr.tongson.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * <b>Create Date:</b> 2019-04-27<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public abstract class BaseFragment extends Fragment /*implements IPreload*/ {
    protected View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            inflateView(inflater, container);
            initView();
            loadData();
        }
        ViewGroup group = (ViewGroup) rootView.getParent();
        if (group != null) {
            group.removeView(rootView);
        }
        return rootView;
    }

    protected void inflateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(getLayoutId(), container, false);
        rootView = view;
    }

    @Nullable
    public final <T extends View> T findViewById(@IdRes int id) {
        if (rootView == null) {
            return null;
        }
        return rootView.findViewById(id);
    }


    /**
     * @return 获取layout的id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view，只加载一次。
     */
    protected abstract void initView();

    /**
     * 初始化数据，只加载一次。
     */
    protected abstract void loadData();
}
