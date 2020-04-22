package pr.tongson.module_main.style;

import android.os.Bundle;

import com.xiaojinzi.component.anno.RouterAnno;

import androidx.appcompat.app.AppCompatActivity;
import pr.tongson.module_main.R;

@RouterAnno(path = "Style")
public class StyleListActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_list);

        if (savedInstanceState == null) {
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.container,new StyleListFragment()).
                    commitNow();
        }
    }

}
