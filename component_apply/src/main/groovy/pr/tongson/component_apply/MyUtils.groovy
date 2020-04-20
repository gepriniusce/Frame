package pr.tongson.component_apply

import java.util.regex.Pattern

class MyUtils {
    /**
     * 是否是maven 坐标
     *
     * @return
     */
    static boolean isMavenArtifact(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return Pattern.matches("\\S+(\\.\\S+)+:\\S+(:\\S+)?(@\\S+)?", str);
    }
}