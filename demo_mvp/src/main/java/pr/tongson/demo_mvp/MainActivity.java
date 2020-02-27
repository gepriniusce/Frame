package pr.tongson.demo_mvp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import pr.tongson.demo_mvp.model.IpInfoTask;
import pr.tongson.demo_mvp.presenter.IpInfoPresenter;

public class MainActivity extends AppCompatActivity {

    private IpInfoPresenter mIpInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lib_dialog_default);

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
