package pr.tongson.demo_mvvm;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import pr.tongson.demo_mvvm.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.container, MainFragment.newInstance()).
                    commitNow();
        }
    }
}
