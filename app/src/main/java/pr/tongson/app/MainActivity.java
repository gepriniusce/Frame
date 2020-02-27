package pr.tongson.app;

import androidx.appcompat.app.AppCompatActivity;
import pr.tongson.templet_welcome.SplashActivity;

import android.content.Intent;
import android.os.Bundle;

import com.xiaojinzi.component.anno.RouterAnno;
import com.xiaojinzi.component.impl.Router;
import com.xiaojinzi.component.support.Action;

@RouterAnno(path = "first")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(this, SplashActivity.class);
//        startActivity(intent);
//        finish();
        Router.
                with().
                hostAndPath("templetWelcome/splash").
                afterJumpAction(new Action() {
                    @Override
                    public void run() {
                        finish();
                    }
                }).
                forward();
    }
}
