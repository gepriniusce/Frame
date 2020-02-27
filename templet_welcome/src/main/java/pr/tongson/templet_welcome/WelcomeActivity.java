package pr.tongson.templet_welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xiaojinzi.component.impl.Router;
import com.xiaojinzi.component.support.Action;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Router.
                with().
                hostAndPath("templetUsersystem/login").
                afterJumpAction(new Action() {
                    @Override
                    public void run() {
                        finish();
                    }
                }).
                forward();

    }
}
