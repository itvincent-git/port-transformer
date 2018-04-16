package net.port.transformer.util;

/**
 *
 * Created by zhongyongsheng on 2018/4/16.
 */
public class StringUtil {

    /**
     * 首字母最小化
     * @param string
     * @return
     */
    public static String decapitalize(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }
        char c[] = string.toCharArray();
        c[0] = Character.toLowerCase(c[0]);
        return new String(c);
    }

//    public static String stripNonJava(String string) {
//        Arrays.stream(string.split("[^a-zA-Z0-9]")).map(s -> s.trim()).join
//    }
}
