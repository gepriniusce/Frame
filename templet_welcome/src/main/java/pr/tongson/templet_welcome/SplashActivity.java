package pr.tongson.templet_welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.xiaojinzi.component.anno.RouterAnno;

import androidx.appcompat.app.AppCompatActivity;
import pr.tongson.library.utils.L;

@RouterAnno(path = "splash")
public class SplashActivity extends AppCompatActivity {

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mHandler = new Handler(Looper.getMainLooper());
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent = new Intent(SplashActivity.this, GuideGalleryActivity.class);
//                startActivity(intent);
//                finish();
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
}
