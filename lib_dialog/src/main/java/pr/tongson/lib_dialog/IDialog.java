package pr.tongson.lib_dialog;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.FragmentManager;

/**
 * <b>Create Date:</b> 2020-01-30<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public interface IDialog {

    /**
     * 消失方法加工
     */
    void dismiss();

    /**
     * 构造dialog里的View
     */
    interface onBuildListener {
        /**
         *
         * @param dialog
         * @param view
         * @param layoutRes
         */
        void onBuildChildView(IDialog dialog, View view, int layoutRes);
    }

    /**
     * 点击事件接口
     */
    interface onClickListerner {
        void onClick(IDialog dialog);
    }

    /**
     * 消失事件接口
     */
    interface onDismissListener {
        void onDismiss(IDialog dialog);
    }

    Context getContext();
}
