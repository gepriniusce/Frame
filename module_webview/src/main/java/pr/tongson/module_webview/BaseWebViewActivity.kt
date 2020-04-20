package pr.tongson.module_webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pr.tongson.module_webview.ui.BaseWebViewFragment

class BaseWebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, BaseWebViewFragment.newInstance())
                    .commitNow()
        }
    }
}
