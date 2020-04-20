package pr.tongson.module_webview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.webview_fragment.*
import pr.tongson.module_webview.BaseWebChromeClient
import pr.tongson.module_webview.BaseWebJsBridge
import pr.tongson.module_webview.BaseWebViewClient
import pr.tongson.module_webview.R
import pr.tongson.module_webview.ui.webviewbase.WebViewBaseViewModel

class BaseWebViewFragment : Fragment() {
    /**
     * 懒加载：使用了才初始化
     */
    private val mWebView: WebView by lazy {
        web_view
    }

    companion object {
        fun newInstance() = BaseWebViewFragment()
    }

    private lateinit var viewModel: WebViewBaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.webview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WebViewBaseViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWebView()
    }

    var setWebView = {
        mWebView.settings.run {
            javaScriptEnabled = true
        }

        mWebView.run {
            webViewClient = BaseWebViewClient()
            webChromeClient = BaseWebChromeClient()
            val jsBridge = BaseWebJsBridge(activity, mWebView)
            lifecycle.addObserver(jsBridge)
            addJavascriptInterface(jsBridge, "JsBridge")
        }

        mWebView.loadUrl("file:///android_asset/test.html")
    }
}
