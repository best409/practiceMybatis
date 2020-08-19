package com.itheima.dao.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author: zhangycl
 * @date: 2020/8/18
 * @description:
 */
public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory =factory;
    }

    public List<User> findAll() {
        //1. 根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2. 调用SqlSession中的方法，实现查询列表
        List<User> users = session.selectList("com.itheima.dao.IUserDao.findAll");//参数就是能获取配置信息的key
        //3. 释放资源
        session.close();
        return users;
    }

    public void saveUser(User user) {
        //1. 根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2. 调用SqlSession中的方法，实现查询列表
        session.insert("com.itheima.dao.IUserDao.saveUser", user);//参数就是能获取配置信息的key
        //3. 提交事务
        session.commit();
        //4. 释放资源
        session.close();
    }

    public void updateUser(User user) {

    }

    public void deleteUser(Integer userId) {

    }

    public User findById(Integer userId) {
        return null;
    }

    public List<User> findByName(String username) {
        //1. 根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2. 调用SqlSession中的方法，实现查询列表
        List<User> users = session.selectList("com.itheima.dao.IUserDao.findByName", username);//参数就是能获取配置信息的key
        //3. 释放资源
        session.close();
        return users;
    }

    public int findTotal() {
        //1. 根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2. 调用SqlSession中的方法，实现查询列表
        int count = session.selectOne("com.itheima.dao.IUserDao.findTotal");//参数就是能获取配置信息的key
        //3. 释放资源
        session.close();
        return count;
    }

    public List<User> findUserByVo(QueryVo vo) {
        return null;
    }
}
