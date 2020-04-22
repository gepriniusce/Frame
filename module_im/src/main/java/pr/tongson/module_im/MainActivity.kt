package pr.tongson.module_im

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xiaojinzi.component.anno.RouterAnno

@RouterAnno(path = "im")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
