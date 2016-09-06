package com.sai.base.utils;


public class StringUtils {
    private StringUtils() {
    }

    /**
     * 检查字符串是否为空
     * isEmpty
     *
     * @param string
     * @return: boolean
     */
    public static boolean isEmpty(String string) {
        if (string != null && string.length() > 0) {
            return false;
        }
        return true;
    }


    /**
     * 如果为null, 返回空字符串
     *
     * @param string
     * @return
     * @return: String
     */
    public static String trim(String string) {
        if (isEmpty(string)) {
            return "";
        }
        return string.trim();
    }

    /**
     * 如果为null，则返回defValue
     *
     * @param string
     * @param defValue
     * @return
     */
    public static String trim(String string, String defValue) {
        if (isEmpty(string)) {
            return defValue;
        }
        return string.trim();
    }


    /**
     * 使用StringBuilder 生成String
     *
     * @param msgs
     * @return
     */
    public static String toString(String... msgs) {

        StringBuilder builder = new StringBuilder();
        for (String msg : msgs) {
            if (!isEmpty(msg)) {
                builder.append(msg);
            }
        }
        return builder.toString();
    }
}
