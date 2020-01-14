package pr.tongson.base.ui.fragment.i;

/**
 * <b>Create Date:</b> 2019-04-27<br>
 * <b>Email:</b> 289286298@qq.com<br>
 * <b>Description:</b>  <br>
 *
 * @author tongson
 */
public interface ILazyLoad extends IPreload {
    /**
     * 懒加载的时候初始化view，只加载一次。
     */
    void initViewLazy();

    /**
     * 懒加载的时候初始化data，只加载一次。
     */
    void loadDataLazy();
}
