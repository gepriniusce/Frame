package pr.tongson.module_player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xiaojinzi.component.anno.RouterAnno

@RouterAnno(path = "Player")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
