package com.itheima.mybatis.io;

import jdk.internal.util.xml.impl.Input;

import java.io.InputStream;

/**
 * @author: zhangycl
 * @date: 2020/8/17
 * @description: 使用类加载器读取配置文件的类
 */
public class Resources {

    /*
    * 根据传入的参数，获取一个字节输入流
    * */

    public static InputStream getResourceAsStream(String filePath) {
        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }
}
