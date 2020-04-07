package pr.tongson.base.mvp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import pr.tongson.base.mvc.BaseMVCDialogFragment;
import pr.tongson.base.utils.ToastUtil;

/**
 * <b>Create Date:</b> 2020/3/27<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public abstract class BaseMVPDialogFragment<T extends BaseMVPPresenter> extends BaseMVCDialogFragment implements BaseMVPView {


    protected T mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }


    @Override
    public void useNightMode(boolean isNightMode) {

    }

    @Override
    public void showToast(String message) {
        if (getActivity() == null) {
            return;
        }
        ToastUtil.showMessage(getActivity(), message);
    }

    @Override
    public void showSnackBar(String message) {
        if (getActivity() == null) {
            return;
        }
        ToastUtil.showSnackMessage(getActivity(), message);
    }

    @Override
    public void showErrorMsg(String errorMsg) {
        if (getActivity() == null || !isAdded()) {
            return;
        }
        ToastUtil.showSnackMessage(getActivity(), errorMsg);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter = null;
        }
    }
}
