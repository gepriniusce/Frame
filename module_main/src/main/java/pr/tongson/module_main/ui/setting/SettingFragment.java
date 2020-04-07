package pr.tongson.module_main.ui.setting;

import android.os.Bundle;
import android.view.View;

import java.io.File;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pr.tongson.base.mvp.BaseMVPFragment;
import pr.tongson.base.recycler.adapter.RViewAdapter;
import pr.tongson.base.recycler.listener.IOnItemListener;
import pr.tongson.library.cache.ACache;
import pr.tongson.module_main.R;
import pr.tongson.module_main.ui.setting.viewitem.bean.SettingListBean;

/**
 * @author tongson
 */
public class SettingFragment extends BaseMVPFragment<SettingPresenter> implements SettingContract.View, IOnItemListener<SettingListBean> {
    private File cacheFile;

    public SettingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupPresenter() {
        mPresenter = new SettingPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_setting_list;
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
        cacheFile = new File(ACache.PATH_CACHE);
        RViewAdapter<SettingListBean> rViewAdapter = new RViewAdapter<SettingListBean>(mPresenter.getItemList(), mPresenter.getItemTypes());
        rViewAdapter.setItemListener(this);
        mRecyclerView.setAdapter(rViewAdapter);
    }

    @Override
    public void onItemClick(View view, SettingListBean entity, int position) {
        if (entity.getCacheBean() != null) {
            clearCache();
        }
    }

    @Override
    public boolean onItemLongClick(View view, SettingListBean entity, int position) {
        return false;
    }

    @Override
    public void changeNightMode() {
        if (getActivity() != null) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            getActivity().recreate();
        }
    }

    @Override
    public void clearCache() {
        ACache.deleteDir(cacheFile);
    }

    @Override
    public String getCacheMsg() {
        return ACache.getCacheSize(cacheFile);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
