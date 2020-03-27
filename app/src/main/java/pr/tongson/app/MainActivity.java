package pr.tongson.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.xiaojinzi.component.anno.RouterAnno;
import com.xiaojinzi.component.impl.Router;
import com.xiaojinzi.component.support.Action;

/**
 * 这个也没啥用，以后可能会去掉
 * @author tongson
 */
@RouterAnno(path = "First")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_activity_main);


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
