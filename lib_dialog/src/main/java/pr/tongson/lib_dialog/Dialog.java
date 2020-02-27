package pr.tongson.lib_dialog;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

/**
 * <b>Create Date:</b> 2020-01-30<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class Dialog extends BaseDialog implements IDialog {

    private Context mContext;
    private Builder mBuilder;

    public Dialog(Builder builder) {
        mBuilder = builder;
    }

    public Dialog() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mContext = activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutRes() {
        return mBuilder.layoutRes;
    }

    @Override
    protected View getDialogView() {
        return mBuilder.dialogView;
    }

    @Override
    protected boolean isCanTouchOutside() {
        return mBuilder.isCanTouchOutside;
    }

    @Override
    protected int getGravity() {
        return mBuilder.gravity;
    }

    @Override
    protected float getDimAmount() {
        return mBuilder.dimAmount;
    }

    @Override
    protected int getDialogWidth() {
        return mBuilder.dialogWidth;
    }

    @Override
    protected int getDialogHeight() {
        return mBuilder.dialogHeight;
    }

    @Override
    protected int getAnimRes() {
        return mBuilder.animRes;
    }


    public static class Builder {
        int layoutRes, animRes;
        int dialogWidth, dialogHeight;
        float dimAmount = 0.2f;
        int gravity = Gravity.CENTER;
        boolean isCanTouchOutside = true, cancelable = false;
        View dialogView;

        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("context can't null");
            }
            if (!(context instanceof Activity)) {
                throw new IllegalArgumentException("context must be Activity");
            }
        }

        public Builder setDialogView(@LayoutRes int layoutRes) {
            this.layoutRes = layoutRes;
            return this;
        }

        public Builder setDialogView(View dialogView) {
            this.dialogView = dialogView;
            return this;
        }

        public Builder setAnimStyle(int animStyle) {
            this.animRes = animStyle;
            return this;
        }

        public Builder setDimAmout(float dimAmount) {
            this.dimAmount = dimAmount;
            return this;
        }

        public Builder setGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setCanTouchOutside(boolean b) {
            this.isCanTouchOutside = b;
            return this;
        }

        public Builder setCancelable(boolean b) {
            this.cancelable = b;
            return this;
        }

        public Builder setHeight(int height) {
            this.dialogHeight = height;
            return this;
        }

        public Builder setWidth(int width) {
            this.dialogWidth = width;
            return this;
        }

        public Dialog onCreate() {
            Dialog dialog = new Dialog(this);
            return dialog;
        }

        public Dialog show(FragmentManager fragmentManager) {
            if (this.layoutRes <= 0 && this.dialogView == null) {
                setDefaultView();
            }
            Dialog dialog = onCreate();
            dialog.show(fragmentManager, "TAG");
            return dialog;
        }

        private void setDefaultView() {
            this.cancelable = false;
            this.isCanTouchOutside = true;
            this.gravity = Gravity.CENTER;
            this.layoutRes = R.layout.lib_dialog_default;
            this.dimAmount = 0.5f;
        }


    }

}
