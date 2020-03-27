package pr.tongson.module_welcome;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.xiaojinzi.component.anno.RouterAnno;
import com.xiaojinzi.component.impl.Router;
import com.xiaojinzi.component.support.Action;

import androidx.appcompat.app.AppCompatActivity;

@RouterAnno(path = "Splash")
public class SplashActivity extends AppCompatActivity {

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity_splash);
        mHandler = new Handler(Looper.getMainLooper());
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startNextActivity();
            }
        }, 1000);
    }

    @Override
    protected void onStop() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        super.onStop();
    }

    private void startNextActivity() {
        try {
            Router.
                    with().
                    hostAndPath("moduleWelcome/GuideGallery").
                    afterJumpAction(new Action() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }).
                    forward();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
