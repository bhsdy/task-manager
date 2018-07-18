package com.cn.taskManager.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 字符串编码工具类
 *
 * @author Evan
 */
public final class EscapeUtils {
    
    /**
     * 单例
     */
    private EscapeUtils() {
    }
    
    /**
     * base64编码
     *
     * @param bs
     * @return
     */
    public static String encodeBase64(byte[] bs) {
        return Base64.encodeBase64String(bs);
    }
    
    /**
     * base64解码
     *
     * @param str
     * @return
     */
    public static byte[] decodeBase64(String str) {
        return Base64.decodeBase64(str);
    }
    
    /**
     * cvs解码
     *
     * @param str 要解码的字符串
     * @return cvs解码字符串
     */
    public static String decodeCsv(String str) {
        return StringEscapeUtils.unescapeCsv(str);
    }
    
    /**
     * HTML解码
     *
     * @param str 要解码的字符串
     * @return html解码字符串
     */
    public static String decodeHtml(String str) {
        return StringEscapeUtils.unescapeHtml(str);
    }
    
    /**
     * java解码
     *
     * @param str 要解码的字符串
     * @return java解码字符串
     */
    public static String decodeJava(String str) {
        return StringEscapeUtils.unescapeJava(str);
    }
    
    /**
     * javascript解码
     *
     * @param str 要解码的字符串
     * @return javascript解码字符串
     */
    public static String decodeJavaScript(String str) {
        return StringEscapeUtils.unescapeJavaScript(str);
    }
    
    /**
     * url解码
     *
     * @param str 要解码的字符串
     * @return url解码字符串
     */
    public static String decodeUrl(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * XML解码
     *
     * @param str 要解码的字符串
     * @return xml解码字符串
     */
    public static String decodeXml(String str) {
        return StringEscapeUtils.unescapeXml(str);
    }
    
    /**
     * cvs编码
     *
     * @param str 要编码的字符串
     * @return cvs编码字符串
     */
    public static String encodeCsv(String str) {
        return StringEscapeUtils.escapeCsv(str);
    }
    
    /**
     * html编码
     *
     * @param str 要编码的字符串
     * @return html编码字符串
     */
    public static String encodeHtml(String str) {
        return StringEscapeUtils.escapeHtml(str);
    }
    
    /**
     * java编码
     *
     * @param str 要编码的字符串
     * @return java编码字符串
     */
    public static String encodeJava(String str) {
        return StringEscapeUtils.escapeJava(str);
    }
    
    /**
     * javascript编码
     *
     * @param str 要编码的字符串
     * @return javascript编码字符串
     */
    public static String encodeJavaScript(String str) {
        return StringEscapeUtils.escapeJavaScript(str);
    }
    
    /**
     * sql编码
     *
     * @param str 要编码的字符串
     * @return sql编码字符串
     */
    public static String encodeSql(String str) {
        return StringEscapeUtils.escapeSql(str);
    }
    
    /**
     * url编码
     *
     * @param str 要编码的字符串
     * @return url编码字符串
     */
    public static String encodeUrl(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * XML编码
     *
     * @param str 要编码的字符串
     * @return xml编码字符串
     */
    public static String encodeXml(String str) {
        return StringEscapeUtils.escapeXml(str);
    }
}
