package pr.tongson.module_main.style;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pr.tongson.base.mvp.BaseMVPFragment;
import pr.tongson.base.recycler.adapter.RViewAdapter;
import pr.tongson.module_main.R;

/**
 * @author tongson
 */
public class StyleListFragment extends BaseMVPFragment<StyleListPresenter> implements StyleListContract.View {

    private RecyclerView mRecyclerView;
    private RViewAdapter<StyleListBean> rViewAdapter;

    @Override
    public void setupPresenter() {
        mPresenter = new StyleListPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_style_list_list;
    }

    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) mRootView;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        rViewAdapter = new RViewAdapter<StyleListBean>(mPresenter.getItemList(), mPresenter.getItemTypes());
        mRecyclerView.setAdapter(rViewAdapter);
    }

    @Override
    protected void setListener() {
        //rViewAdapter.setItemListener(this);
    }

    @Override
    protected void processLogic() {

    }

}
