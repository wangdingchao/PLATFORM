package com.example.platform.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * SHA加密
     *
     * @param str 加密字段
     * @return 加密后的40位字符
     * @throws NoSuchAlgorithmException     没有加密类型异常
     * @throws UnsupportedEncodingException 编码格式不支持异常
     */
    public static String getSHA(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String encryption = getEncryption(str, DigestUtils.getSha1Digest());
        return encryption;
    }

    /**
     * SHA加密
     *
     * @param str 加密字段
     * @return 加密后的40位字符
     * @throws NoSuchAlgorithmException     没有加密类型异常
     * @throws UnsupportedEncodingException 编码格式不支持异常
     */
    public static String getEncryption(String str, MessageDigest messageDigest) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] byteArray = str.getBytes("UTF-8");
        byte[] md5Bytes = messageDigest.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * MD5加密
     *
     * @param inStr 加密字段
     * @return 加密后的MD5码
     * @author Alex xu
     * @date 2018/11/27
     */
    public static String getMD5(String inStr) {
        try {
            return DigestUtils.md5Hex(inStr.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误");
        }
    }

    public static String HmacSha256ToString(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    public static byte[] HmacSha256ToByte(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return sha256_HMAC.doFinal(data.getBytes("UTF-8"));
    }

    /**
     * 在指定的数字的ASCII码区间内生成定长字符串
     *
     * @param min    数字区间最小值
     * @param max    数字区间最大值
     * @param length 生成字符串长度
     * @return 生成的字符串
     */
    public static String getString(int min, int max, int length) {
        StringBuilder builder = new StringBuilder();
        if (max > 127) {
            max = 127;
        }
        if (min < 48) {
            min = 48;
        }
        if (length <= 0 || max <= min) {
            return null;
        }
        for (int t = 0; t < length; t++) {
            int i = (int) (min + Math.random() * (max - min + 1));
            if (((i >= 91) && (i <= 96)) || ((i >= 58) && (i <= 64))) {
                i = 65;
            }
            if (i < 48) {
                i = 48;
            }
            if (i > 122) {
                i = 122;
            }
            char c = (char) i;
            builder.append(c);
        }
        return builder.toString();
    }

    /**
     *  字符串模板变量替换（map中不存在的key将不予替换）
     *      example
     *       Map<String, Object> map = new HashMap<>();
     *       map.put("name", "张三");
     *       map.put("code", "10012");
     *       map.put("age", 29);
     *
     *       String template = "{name}今年{age}岁了";
     *
     *       String result = render(template, map);
     *       System.out.println(result);
     *       == 张三今年29岁了
     *
     * @param content 模板
     * @param map 参数
     * @return 渲染之后的新字符串
     */
    public static String render(String content, Map<String, Object> map) {

        String result = content;

        for (Map.Entry<String, Object> entry : map.entrySet()) {

            String regex = "\\{" + entry.getKey() + "}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(result);

            result = matcher.replaceAll(String.valueOf(entry.getValue()));
        }

        return result;
    }

//    public static <T> String render(String content, T t) {
//
//        Map<String, Object> map = MBConverter.beanToMap(t);
//
//        return render(content, map);
//    }

    //只获取中文字符
    public static String getChinese(String paramValue) {
        String regex = "([\u4e00-\u9fa5]+)";
        String str = "";
        Matcher matcher = Pattern.compile(regex).matcher(paramValue);
        while (matcher.find()) {
            str+= matcher.group(0);
        }
        return str;
    }

    /**
     * 获取中文的拼音首字母大写
     * @param chineseStr 中文字符串
     * @return
     */
//    public static String chineseStrToPingYinInitialUppercase(String chineseStr) {
//        StringBuffer ret = new StringBuffer();
//        for (char c : chineseStr.toCharArray()) {
//            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
//                String[] charPingYin = PinyinHelper.toHanyuPinyinStringArray(c);
//                if(ArrayUtils.isNotEmpty(charPingYin) && StringUtils.isNotEmpty(charPingYin[0])) {
//                    ret.append(String.valueOf(charPingYin[0].charAt(0)).toUpperCase());
//                }
//            } else {
//                ret.append(c);
//            }
//
//        }
//        return ret.toString();
//    }

}
