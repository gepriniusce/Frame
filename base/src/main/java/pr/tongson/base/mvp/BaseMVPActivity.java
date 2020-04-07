package pr.tongson.base.mvp;

import androidx.appcompat.app.AppCompatDelegate;
import pr.tongson.base.mvc.BaseMVCActivity;
import pr.tongson.base.utils.ToastUtil;

/**
 * <b>Create Date:</b> 2020/3/27<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public abstract class BaseMVPActivity<T extends BaseMVPPresenter> extends BaseMVCActivity implements BaseMVPView {

    protected T mPresenter;

    @Override
    protected void onViewCreated() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void useNightMode(boolean isNightMode) {
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    @Override
    public void showToast(String message) {
        ToastUtil.showMessage(this, message);
    }

    @Override
    public void showSnackBar(String message) {
        ToastUtil.showSnackMessage(this, message);
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        ToastUtil.showSnackMessage(this, errorMsg);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

}
