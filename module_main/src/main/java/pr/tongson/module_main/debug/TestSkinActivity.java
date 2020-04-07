package pr.tongson.module_main.debug;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import java.io.File;

import androidx.appcompat.app.AppCompatDelegate;
import pr.tongson.base.skin.SkinActivity;
import pr.tongson.library.utils.L;
import pr.tongson.module_main.R;
import pr.tongson.module_main.debug.dummy.DummyContent;

public class TestSkinActivity extends SkinActivity implements View.OnClickListener,TestSkinFragment.OnListFragmentInteractionListener {

    private boolean b = false;
    private String skinPath;
    private TextView mClickNight;
    private TextView mClickPackage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_test_skin);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, TestSkinFragment.newInstance(1)).commitNow();
        }

        // 运行时权限申请（6.0+）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (checkSelfPermission(perms[0]) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(perms, 200);
            }
        }

        ///storage/emulated/0/TongsonV1.skin
        skinPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "TongsonV2.skin";
        L.i("Tongson skinPath=>" + skinPath);
        initView();
    }

    @Override
    protected boolean openChangeSkin() {
        return true;
    }

    private void initView() {
        mClickNight = (TextView) findViewById(R.id.click_night);
        mClickNight.setOnClickListener(this);
        mClickPackage = (TextView) findViewById(R.id.click_package);
        mClickPackage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       change();
    }

    private void change() {
        if (b) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            defaultSkin();
            b = false;
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            dynamicSkin(skinPath);
            b = true;
        }
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        change();
    }
}
