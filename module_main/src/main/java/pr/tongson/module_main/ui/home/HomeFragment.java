package pr.tongson.module_main.ui.home;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pr.tongson.base.mvp.BaseMVPFragment;
import pr.tongson.base.recycler.adapter.RViewAdapter;
import pr.tongson.base.recycler.listener.IOnItemListener;
import pr.tongson.module_main.R;

public class HomeFragment extends BaseMVPFragment<HomePresenter> implements HomeContract.View, IOnItemListener<HomeListBean> {

    @Override
    public void setupPresenter() {
        mPresenter = new HomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_home;
    }

    private RecyclerView mRecyclerView;

    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) mRootView;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic() {
        RViewAdapter<HomeListBean> rViewAdapter = new RViewAdapter<HomeListBean>(mPresenter.getItemList(), mPresenter.getItemTypes());
        rViewAdapter.setItemListener(this);
        mRecyclerView.setAdapter(rViewAdapter);
    }


    @Override
    public void onItemClick(View view, HomeListBean entity, int position) {

    }

    @Override
    public boolean onItemLongClick(View view, HomeListBean entity, int position) {
        return false;
    }
}
