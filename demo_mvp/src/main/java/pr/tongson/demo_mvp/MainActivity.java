package pr.tongson.demo_mvp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pr.tongson.demo_mvp.model.IpInfo;
import pr.tongson.demo_mvp.model.IpInfoTask;
import pr.tongson.demo_mvp.presenter.IpInfoPresenter;

public class MainActivity extends AppCompatActivity {

    private IpInfoPresenter mIpInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IpInfoFragment ipInfoFragment = IpInfoFragment.newInstance(null, null);

        setupFragment(R.id.content, ipInfoFragment);

        IpInfoTask ipInfoTask = IpInfoTask.getInstance();
        mIpInfoPresenter = new IpInfoPresenter(ipInfoFragment, ipInfoTask);
        ipInfoFragment.setPresenter(mIpInfoPresenter);
    }

    public void setupFragment(int layoutid, Fragment fragment) {
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(layoutid, fragment);
        ft.commitAllowingStateLoss();
    }
}
