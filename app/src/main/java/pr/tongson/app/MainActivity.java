package pr.tongson.app;

import androidx.appcompat.app.AppCompatActivity;

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

        Router.
                with().
                hostAndPath("moduleWelcome/Splash").
                afterJumpAction(new Action() {
                    @Override
                    public void run() {
                        finish();
                    }
                }).
                forward();
    }
}
