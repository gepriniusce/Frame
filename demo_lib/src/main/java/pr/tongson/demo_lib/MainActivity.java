package pr.tongson.demo_lib;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import pr.tongson.lib_dialog.Dialog;

public class MainActivity extends AppCompatActivity {

    private TextView mTvClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mTvClick = (TextView) findViewById(R.id.tv_click);
        mTvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog.Builder builder = new Dialog.Builder(MainActivity.this);
                builder.
                        setWidth(500).
                        setHeight(500).
                        show(getSupportFragmentManager());
            }
        });
    }
}
