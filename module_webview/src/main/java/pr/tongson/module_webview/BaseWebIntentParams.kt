package pr.tongson.module_webview

import android.os.Parcel
import android.os.Parcelable

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/4/7
 * @Version
 * @Since
 * @Description
 */
class BaseWebIntentParams() : Parcelable {

    private var mUrl: String? = null


    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(mUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BaseWebIntentParams> {
        override fun createFromParcel(parcel: Parcel): BaseWebIntentParams {
            return BaseWebIntentParams(parcel)
        }

        override fun newArray(size: Int): Array<BaseWebIntentParams?> {
            return arrayOfNulls(size)
        }
    }

    fun getUrl(): String? {
        return mUrl
    }

    fun setUrl(url: String) {
        mUrl = url
    }

}