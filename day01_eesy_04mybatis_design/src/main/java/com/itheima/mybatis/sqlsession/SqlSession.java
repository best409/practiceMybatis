package com.itheima.mybatis.sqlsession;

/**
 * @author: zhangycl
 * @date: 2020/8/17
 * @description: 自定义Mybatis中和数据库交互的核心类，它里面可以创建dao接口的代理对象
 */
public interface SqlSession {
    /*
    * 根据参数创建一个代理对象
    * daoInterfaceClass dao的接口字节码
    * */
    <T> T getMapper(Class<T> daoInterfaceClass);

    /*
    * 释放资源
    * */
    void close();
}
