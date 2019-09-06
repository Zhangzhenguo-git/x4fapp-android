package com.x4fhuozhu.app.kit;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串处理常用方法
 */
public abstract class RegExpKit {


    /**
     * 匹配非负整数（正整数 + 0）
     */
    public final static String UNSIGNED = "^\\d+$";

    /**
     * 匹配整数
     */
    public final static String INTERGER = "^-?\\d+$";

    /**
     * 匹配非负浮点数（正浮点数 + 0）
     */
    public final static String FLOAT = "^(-?\\d+)(\\.\\d+)?$";

    /**
     * 匹配由26个英文字母组成的字符串
     */
    public final static String EN = "^[A-Za-z]+$";

    /**
     * 匹配由26个英文字母的大写组成的字符串
     */
    public final static String UCASE = "^[A-Z]+$";

    /**
     * 匹配由26个英文字母的小写组成的字符串
     */
    public final static String LCASE = "^[a-z]+$";

    /**
     * 匹配由数字和26个英文字母组成的字符串
     */
    public final static String STRING = "^[A-Za-z0-9]+$";


    /**
     * 匹配email地址
     */
    public final static String EMAIL = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";

    /**
     * 匹配url
     */
    public final static String URL = "^[a-zA-z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$";

    /**
     * 匹配中文字符
     */
    public final static String CN = "[\\u4e00-\\u9fa5]";
    /**
     * 匹配中文字姓名
     */
    public final static String CN_NAME = "^[\\u4e00-\\u9fa5]{2,4}$";
    /**
     * 匹配小数位数1
     */
    public final static String FLOAT_ONE = "^(([1-9][0-9]{0,4})|([0-9]{1,3}\\.[1-9]))$";
    /**
     * 匹配双字节字符(包括汉字在内)
     */
    public final static String CNS = "[^\\x00-\\xff]";

    /**
     * 匹配空行
     */
    public final static String SPACE = "\\n[\\s ? ]*\\r";

    /**
     * 匹配HTML标记
     */
    public final static String HTML = "/ <(.*)>.* <\\/\\1> ? <(.*) \\/>/";

    /**
     * 匹配国内电话号码，匹配形式如 0511-4405222 或 021-87888822
     */
    public final static String TEL = "\\d{3}-\\d{8} ?\\d{4}-\\d{7}";

    /**
     * 腾讯QQ号, 腾讯QQ号从10000开始
     */
    public final static String QQ = "[1-9][0-9]{4,}";

    /**
     * 车牌号
     */
    public final static String CAR_NO = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";
    /**
     * 手机号1开头
     */
    public final static String PHONE = "1[0-9]{10}";

    /**
     * 短信验证码6位数字
     */
    public final static String SMS = "[0-9]{6}";
    /**
     * 匹配中国邮政编码
     */
    public final static String ZIP_CODE = "[1-9]\\d{5}(?!\\d)";

    /**
     * 匹配身份证, 18位
     */
    public final static String ID_CARD = "^[1-9][0-9]{16}[0-9X]$";

    /**
     * IP
     */
    public final static String IP = "\\d+\\.\\d+\\.\\d+\\.\\d+";

    /**
     * 日期时间
     */
    public final static String DATETIME = "\\d{4}-\\d{1,2}-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}(:\\d{1,2})?";

    /**
     * 上传图片
     */
    public final static String IMAGE = "^[0-9a-z]{1,2}/[0-9a-z]{30,32}\\.(jpg|png|gif)$";

    /**
     * 正则验证
     *
     * @param content 内容
     * @param regExp  正则表达式
     * @return boolean
     */
    public static boolean regExp(String content, String regExp) {
        if (content == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(content);
        return matcher.find();
    }


    /**
     * 正则验证
     *
     * @param content 内容
     * @param regExp  正则表达式
     * @return boolean
     */
    public static boolean regExp(Object content, String regExp) {
        if (content == null) {
            return false;
        }
        return regExp(content.toString(), regExp);
    }

    /**
     * 是否非零
     *
     * @param o 内容
     * @return boolean
     */
    public static boolean notZero(Object o) {
        if (!isMoney(o)) {
            return false;
        }
        return regExp(o, "[1-9]+");
    }

    /**
     * 是否为0
     *
     * @param o
     * @return boolean
     */
    public static boolean isZero(Object o) {
        return !regExp(o, "[1-9]+");
    }

    /**
     * 是否为非零数字
     *
     * @param o
     * @return boolean
     */
    public static boolean isId(Object o) {
        return regExp(o, "^[1-9]+[0-9]*$");
    }

    /**
     * 是否为密码6个数字
     *
     * @param o 内容
     * @return boolean
     */
    public static boolean isPwd(Object o) {
        return regExp(o, "^\\d{6}$");
    }

    /**
     * 是否为手机号
     *
     * @param o
     * @return boolean
     */
    public static boolean isPhone(Object o) {
        return regExp(o, PHONE);
    }

    /**
     * 是否为图片
     *
     * @param o
     * @return boolean
     */
    public static boolean isImage(Object o) {
        return regExp(o, IMAGE);
    }

    /**
     * 是否为油联卡
     *
     * @param o
     * @return boolean
     */
    public static boolean isUnionCard(Object o) {
        return regExp(o, "^\\d{16}$");
    }

    /**
     * 是否为中文姓名2-4个汉字
     *
     * @param o
     * @return boolean
     */
    public static boolean isCnName(Object o) {
        return RegExpKit.regExp(o, "^[\\u4e00-\\u9fa5]{2,4}$");
    }

    public static boolean isMoney(Object o) {
        return RegExpKit.regExp(o, "(^-?[1-9](\\d+)?(\\.\\d{1,2})?$)|(^-?0$)|(^-?\\d\\.\\d{1,2}$)");
    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     *
     * @param value 指定的字符串
     * @return 字符串的长度
     */
    public static int strLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }
}
