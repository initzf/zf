package com.md.zhihu.main.tools;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by John.
 * 字符串的处理类
 */
public class StringUtil {


    /**
     * 判断是否为null或空值
     *
     * @param str String
     * @return true or false
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断str1和str2是否相同
     *
     * @param str1 str1
     * @param str2 str2
     * @return true or false
     */
    public static boolean equals(String str1, String str2) {
        return str1 == str2 || str1 != null && str1.equals(str2);
    }

    /**
     * 判断str1和str2是否相同(不区分大小写)
     *
     * @param str1 str1
     * @param str2 str2
     * @return true or false
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 != null && str1.equalsIgnoreCase(str2);
    }

    /**
     * 判断字符串str1是否包含字符串str2
     *
     * @param str1 源字符串
     * @param str2 指定字符串
     * @return true源字符串包含指定字符串，false源字符串不包含指定字符串
     */
    public static boolean contains(String str1, String str2) {
        return str1 != null && str1.contains(str2);
    }

    /**
     * 判断字符串是否为空，为空则返回一个空值，不为空则返回原字符串
     *
     * @param str 待判断字符串
     * @return 判断后的字符串
     */
    public static String getString(String str) {
        return str == null ? "" : str;
    }

    /**
     * 判断字符串是否为空，为空则返回一个空值，不为空则返回原字符串
     *
     * @param str 待判断字符串
     * @return 判断后的字符串
     */
    public static String getshuangpar(String str) {
        return str == null ? "" : str;
    }

    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     *
     * @param str 无逗号的数字
     * @return 加上逗号的数字
     */
    public static String addComma(String str) {
        // 将传进数字反转
        String reverseStr = new StringBuilder(str).reverse().toString();
        String strTemp = "";
        for (int i = 0; i < reverseStr.length(); i++) {
            if (i * 3 + 3 > reverseStr.length()) {
                strTemp += reverseStr.substring(i * 3, reverseStr.length());
                break;
            }
            strTemp += reverseStr.substring(i * 3, i * 3 + 3) + ",";
        }
        // 将[789,456,] 中最后一个[,]去除
        if (strTemp.endsWith(",")) {
            strTemp = strTemp.substring(0, strTemp.length() - 1);
        }
        // 将数字重新反转
        String resultStr = new StringBuilder(strTemp).reverse().toString();
        return resultStr;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }


    /**
     * 93#汽油京沪地区油价类型
     *
     * @param cityStr
     * @return
     */
    public static String getOil93Text(String cityStr) {
        if ("北京".equals(cityStr)) {
            return "京92#";
        } else if ("上海".equals(cityStr)) {
            return "沪92#";
        }

        return "93#";
    }

    /**
     * 93#汽油京沪地区油价类型
     *
     * @param cityStr
     * @return
     */
    public static String getOil93NoCityText(String cityStr) {
        if ("北京".equals(cityStr)) {
            return "92#";
        } else if ("上海".equals(cityStr)) {
            return "92#";
        }

        return "93#";
    }


    /**
     * 97#汽油京沪地区油价类型
     *
     * @param cityStr
     * @return
     */
    public static String getOil97Text(String cityStr) {
        if ("北京".equals(cityStr)) {
            return "京95#";
        } else if ("上海".equals(cityStr)) {
            return "沪95#";
        }

        return "97#";
    }


    /**
     * 97#汽油京沪地区油价类型
     *
     * @param cityStr
     * @return
     */
    public static String getOil97NoCityText(String cityStr) {
        if ("北京".equals(cityStr)) {
            return "95#";
        } else if ("上海".equals(cityStr)) {
            return "95#";
        }

        return "97#";
    }

    /**
     * 转换成2位小数
     *
     * @param o
     * @return
     */
    public static String formatNumber(Object o) {
        try {
            return null == o ? "" : String.format("%.2f", o);
        } catch (Exception e) {
            return "0.00";
        }
    }

    /**
     * 截取到第一个空格前的字符
     *
     * @param s
     * @return
     */
    public static String CarNamesubmit(String s) {
        String str = s.substring(0, s.lastIndexOf(" "));
        return str;
    }

    /**
     * 捕捉到短信验证码
     *
     * @param patternContent
     * @return
     */
    public static String patternCode(String patternContent) {
        String patternCoder = "(?<!\\d)\\d{6}(?!\\d)";
        if (TextUtils.isEmpty(patternContent)) {
            return null;
        }
        Pattern p = Pattern.compile(patternCoder);
        Matcher matcher = p.matcher(patternContent);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }


    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { // 如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }


    public static String formatNumberTwo(double f){
        DecimalFormat dTwo = new DecimalFormat("0.00");
        return dTwo.format(f);
    }
    public static String formatNumberOne(double f){
        DecimalFormat dTwo = new DecimalFormat("0.0");
        return dTwo.format(f);
    }

    public static String format(String s){
        String str=s.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", "");
        return str;
    }

    public static boolean isBiaoDain(String name){
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(name);
        if(m.find())
            return  true;
        else
            return false;

    }
}

