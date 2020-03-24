package pr.tongson.library.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.appcompat.app.AppCompatActivity;
import pr.tongson.library.utils.L;

/**
 * <b>Create Date:</b> 2020-01-29<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *https://www.jianshu.com/p/89e0a7533dbe
 * @author tongson
 */
public class SaveActivity extends AppCompatActivity {

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        L.i("onSaveInstanceState(Bundle outState)");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        L.i("onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState)");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        L.i("onRestoreInstanceState(Bundle savedInstanceState)");

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        L.i("onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState)");

    }
}
