package pr.tongson.base.ui.fragment.i;

/**
 * <b>Create Date:</b> 2019-04-27<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public interface IPreload {
    /**
     * @return 获取layout的id
     */
    int getLayoutId();

    /**
     * 初始化view，只加载一次。
     */
    void initView();

    /**
     * 初始化数据，只加载一次。
     */
    void loadData();
}
