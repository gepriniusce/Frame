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


    /**
     * 获取类名与方法名
     */
    static String getLogTag() {
        StringBuilder builder = new StringBuilder();
        try {
            StackTraceElement[] stes = Thread.currentThread().getStackTrace();
            StackTraceElement ste = stes[6];
            final String steStr = ste.toString();
            String fileName = ste.getFileName();
            builder.append(fileName.substring(0, fileName.lastIndexOf(".") + 1));
            builder.append(ste.getMethodName());
            builder.append(steStr.substring(steStr.lastIndexOf("("), steStr.length()));
        } catch (Exception e) {
            e.printStackTrace()
            println(e.getMessage())
            // ignore
        }
        return builder.toString();
    }
}