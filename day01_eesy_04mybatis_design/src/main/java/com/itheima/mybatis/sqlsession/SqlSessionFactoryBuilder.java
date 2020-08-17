package com.itheima.mybatis.sqlsession;

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.itheima.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * @author: zhangycl
 * @date: 2020/8/17
 * @description: 用于创建一个SqlSessionFactory 对象
 */
public class SqlSessionFactoryBuilder {

    /*
    * 根据参数的字节输入流来构建一个SQLSessionFactory工厂
    * */
    public SqlSessionFactory build(InputStream config) {
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }

}
