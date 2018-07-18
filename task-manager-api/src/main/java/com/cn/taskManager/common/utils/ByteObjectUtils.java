package com.cn.taskManager.common.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * 二进制对象翻译工具类
 *
 * @author Evan
 */
public final class ByteObjectUtils implements Serializable {
    private static final long serialVersionUID = -8046639762909773723L;
    
    /**
     * 单例
     */
    private ByteObjectUtils() {
    }
    
    /**
     * 深度克隆对象
     *
     * @param src 源对象
     * @return 克隆的对象
     */
    @SuppressWarnings("unchecked")
    public static <E> E clone(E src) {
        byte[] bs = ByteObjectUtils.objectToByte(src);
        return (E) ByteObjectUtils.byteToObject(bs);
    }
    
    /**
     * 对象转二进制,方便存储到数据库中,如果对象无法序列将会抛出异常
     *
     * @param obj 需要转换的对象
     * @return 转换对象后的二进制
     */
    public static byte[] objectToByte(Object obj) {
        byte[] bytes;
        ByteArrayOutputStream bo = null;
        ObjectOutputStream oo = null;
        try {
            bo = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(bo);
            IOUtils.closeQuietly(oo);
        }
        return (bytes);
    }
    
    /**
     * 二进制转对象,方便重数据库中取出对象
     *
     * @param bytes 二进制
     * @return 转换后的对象
     */
    public static Object byteToObject(byte[] bytes) {
        Object obj;
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        try {
            bi = new ByteArrayInputStream(bytes);
            oi = new ObjectInputStream(bi);
            obj = oi.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(bi);
            IOUtils.closeQuietly(oi);
        }
        return obj;
    }
}
