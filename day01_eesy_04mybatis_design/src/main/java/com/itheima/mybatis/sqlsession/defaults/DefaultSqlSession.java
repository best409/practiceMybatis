package com.itheima.mybatis.sqlsession.defaults;

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.sqlsession.SqlSession;
import com.itheima.mybatis.sqlsession.proxy.MapperProxy;
import com.itheima.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author: zhangycl
 * @date: 2020/8/17
 * @description:
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;
    private Connection connection;
    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        connection = DataSourceUtil.getConnection(cfg);
    }


    /*
    * 用于创建代理对象
    * daoInterfaceClass dao的接口字节码
    * */
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                                            new Class[]{daoInterfaceClass},
                                            new MapperProxy(cfg.getMappers(), connection)
                                            );
    }

    /*
    * 用于释放资源
    * */
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
