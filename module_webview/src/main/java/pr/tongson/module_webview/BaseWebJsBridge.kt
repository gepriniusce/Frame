package pr.tongson.module_webview

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import pr.tongson.library.utils.GsonUtils
import pr.tongson.library.utils.L
import java.util.*

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/4/7
 * @Version
 * @Since
 * @Description
 * Kotlin与H5通信桥梁类
 */
class BaseWebJsBridge : LifecycleObserver {
    private var mContext: Context? = null
    private var mWebView: WebView? = null
    private var mhandler: Handler? = null

    constructor(mContext: Context?, mWebView: WebView?) {
        this.mContext = mContext
        this.mWebView = mWebView
        mhandler = CmdH(mWebView)
    }


    class CmdH(private val webView: WebView?) : Handler() {

        companion object {
            val BIND_APPLICATION = 110
        }

        fun codeToString(code: Int): String? {
            when (code) {
                BIND_APPLICATION -> "666"
            }
            return ""
        }

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            L.i("handleMessageThread:" + Thread.currentThread().name)
            when (msg?.what) {
                BIND_APPLICATION -> {
                    try {
                        val b = msg.data
                        var cmd = b.get("cmd")
                        L.i("cmd=$cmd")
                        var jsonParams = b.get("jsonParams")
                        L.i("jsonParams=$jsonParams")

                        //{"type":"account","callback":"djAPI_callback_1586332589225_3032"}

                        var jsJson = JSONObject(jsonParams.toString())
                        var callback = jsJson.optString("callback")

                        val param: HashMap<String, Any> = HashMap()
                        param["result"] = 666
                        param["callbackName"] = callback
                        callbackJS("dj.callback", param)
                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                }
                else -> {
                    L.d("else")
                }
            }
        }

        fun callbackJS(cmd: String?, param: Any?) {
            webView?.let {
                val paramsJson = GsonUtils.toJson(param)
                val trigger = "javascript:$cmd($paramsJson)"
                it.loadUrl(trigger)
            }
        }

    }


    @JavascriptInterface
    fun handleWebAction(cmd: String?, jsonParams: String?) {
        Thread(Runnable {
            L.i("handleWebAction:" + Thread.currentThread().name)
            var message = Message.obtain()
            var bundle = Bundle()
            bundle.putString("cmd", cmd)
            bundle.putString("jsonParams", jsonParams)
            message.what = CmdH.BIND_APPLICATION
            message.data = bundle
            mhandler?.sendMessage(message)
            //mContext?.toast(cmd + jsonParams)
        })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun release() {
        L.i("release")
        mhandler?.let {
            it.removeMessages(CmdH.BIND_APPLICATION)
            it.removeCallbacksAndMessages(null);
        }
        mhandler = null
    }
}