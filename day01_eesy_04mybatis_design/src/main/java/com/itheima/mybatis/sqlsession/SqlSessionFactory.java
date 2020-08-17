package com.itheima.mybatis.sqlsession;

/**
 * @author: zhangycl
 * @date: 2020/8/17
 * @description:
 */
public interface SqlSessionFactory {

    /*
    * 用于打开一个新的SqlSession 对象
    * */
    SqlSession openSession();
}
