package pr.tongson.base.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * <b>Create Date:</b> 2020/3/27<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public class BaseMVPPresenterImpl<T extends BaseMVPView> implements BaseMVPPresenter<T> {

    protected T mView;
    private CompositeDisposable compositeDisposable;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void addRxBindingSubscribe(Disposable disposable) {
        addSubscribe(disposable);
    }

    protected void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    @Override
    public boolean getNightModeState() {
        return false;
    }

//    @Override
//    public void setLoginStatus(boolean loginStatus) {
//
//    }
//
//    @Override
//    public boolean getLoginStatus() {
//        return false;
//    }
//
//    @Override
//    public String getLoginAccount() {
//        return null;
//    }
//
//    @Override
//    public void setLoginAccount(String account) {
//
//    }
//
//    @Override
//    public void setLoginPassword(String password) {
//
//    }

//    @Override
//    public int getCurrentPage() {
//        return 0;
//    }
}
