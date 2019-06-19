package com.wxy.common.entity;

import org.apache.poi.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @Author wangxy
 * @Date 2019/6/19 13:49
 * @Description TODO
 **/
public class SerializeBean implements Serializable {

    private static final long serialVersionUID = -4078339096039423264L;

    /**
     * 序列化方法
     *
     * @return
     */
    public byte[] serialize() {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(oos);
            IOUtils.closeQuietly(baos);
        }
        return null;
    }
}
