package pr.tongson.demo_plugin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * @author tongson
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        printClassLoader();
    }


    private void printClassLoader() {
        ClassLoader classLoader = getClassLoader();
        while (classLoader != null) {
            Log.i("Tongson", "printClassLoader:" + classLoader);
            classLoader = classLoader.getParent();
        }
        Log.i("Tongson", "Activity classLoader:" + Activity.class.getClassLoader());
        Log.i("Tongson", "AppCompatActivity classLoader:" + AppCompatActivity.class.getClassLoader());


    }
}
