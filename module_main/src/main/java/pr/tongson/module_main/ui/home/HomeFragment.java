package pr.tongson.module_main.ui.home;

import android.text.TextUtils;
import android.view.View;

import com.xiaojinzi.component.impl.Router;
import com.xiaojinzi.component.support.Action;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pr.tongson.base.mvp.BaseMVPFragment;
import pr.tongson.base.recycler.adapter.RViewAdapter;
import pr.tongson.base.recycler.listener.IOnItemListener;
import pr.tongson.module_main.R;

/**
 * @author tongson
 */
public class HomeFragment extends BaseMVPFragment<HomePresenter> implements HomeContract.View, IOnItemListener<HomeListBean> {

    private RecyclerView mRecyclerView;
    private RViewAdapter<HomeListBean> rViewAdapter;

    @Override
    public void setupPresenter() {
        mPresenter = new HomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_home;
    }

    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) mRootView;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        rViewAdapter = new RViewAdapter<HomeListBean>(mPresenter.getItemList(), mPresenter.getItemTypes());
        mRecyclerView.setAdapter(rViewAdapter);
    }

    @Override
    protected void setListener() {
        rViewAdapter.setItemListener(this);
    }

    @Override
    protected void processLogic() {

    }
    @Override
    public void onItemClick(View view, HomeListBean entity, int position) {
        if (!TextUtils.isEmpty(entity.getHostAndPath())) {
            Router.
                    with(this).
                    hostAndPath(entity.getHostAndPath()).
                    forward();
        }
    }

    @Override
    public boolean onItemLongClick(View view, HomeListBean entity, int position) {
        return false;
    }
}
