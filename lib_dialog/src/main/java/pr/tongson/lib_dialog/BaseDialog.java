package pr.tongson.lib_dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * <b>Create Date:</b> 2020-01-30<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public abstract class BaseDialog extends DialogFragment {

    private View mRootView = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.ZiweiFragmentDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutRes() > 0) {
            mRootView = inflater.inflate(getLayoutRes(), container, false);
        } else if (getDialogView() != null) {
            mRootView = getDialogView();
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            //如果isCancelable()是false 则会屏蔽物理返回键
            dialog.setCancelable(isCancelable());
            //isCanTouchOutside()为false 点击屏幕外Dialog不会消失；反之会消失
            dialog.setCanceledOnTouchOutside(isCanTouchOutside());
            //如果isCancelable()设置的是false 会屏蔽物理返回键
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    return i == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_DOWN && !isCancelable();
                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null) {
            return;
        }
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (getAnimRes() > 0) {
            window.setWindowAnimations(getAnimRes());
        }
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (getDialogWidth() > 0) {
            layoutParams.width = getDialogWidth();
        } else {
            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        if (getDialogHeight() > 0) {
            layoutParams.height = getDialogHeight();
        } else {
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        layoutParams.dimAmount = getDimAmount();
        layoutParams.gravity = getGravity();
        window.setAttributes(layoutParams);
    }

    protected View getRootView() {
        return mRootView;
    }

    protected abstract View getDialogView();

    protected abstract int getLayoutRes();

    protected boolean isCanTouchOutside() {
        return false;
    }

    protected int getGravity() {
        return Gravity.CENTER;
    }

    protected float getDimAmount() {
        return 0.2f;
    }

    protected int getDialogWidth() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    protected int getDialogHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    protected int getAnimRes() {
        return 0;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
