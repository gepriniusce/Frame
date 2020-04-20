package pr.tongson.module_main.ui.home;

import pr.tongson.module_main.ui.setting.viewitem.bean.CacheBean;
import pr.tongson.module_main.ui.setting.viewitem.bean.DividerBean;
import pr.tongson.module_main.ui.setting.viewitem.bean.SkinBean;

/**
 * @Email:289286298@qq.com
 * @Author tongson
 * @Date 2020/3/29
 * @Version
 * @Since
 * @Description
 */
public class HomeListBean {

    private String moduleName;

    private String hostAndPath;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getHostAndPath() {
        return hostAndPath;
    }

    public void setHostAndPath(String hostAndPath) {
        this.hostAndPath = hostAndPath;
    }
}
