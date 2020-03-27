package pr.tongson.module_welcome;

import android.os.Bundle;

import com.xiaojinzi.component.anno.RouterAnno;
import com.xiaojinzi.component.impl.Router;
import com.xiaojinzi.component.support.Action;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import pr.tongson.module_welcome.ui.main.SectionsPagerAdapter;

@RouterAnno(path = "GuideGallery")
public class GuideGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity_guide_gallery);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int scrollCount;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == viewPager.getAdapter().getCount() - 1) {
                    if (positionOffset == 0.0) {
                        if (positionOffsetPixels == 0) {
                            scrollCount++;
                        }
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (scrollCount > 2) {
                    startNextActivity();
                }
                scrollCount = 0;
            }

        });
    }

    /**
     * 当划到最后一页再多划一点时，就结束，并且开启下一个页面
     */
    private void startNextActivity() {
        Router.
                with().
                hostAndPath("moduleMain/Main").
                //hostAndPath("moduleUserSystem/Login").
                afterJumpAction(new Action() {
                    @Override
                    public void run() {
                        finish();
                    }
                }).
                forward();
    }
}