package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

/**
 * @author: zhangycl
 * @date: 2020/8/17
 * @description:
 */
public interface IUserDao {

    /*
    * 查询所有操作
    * */
    List<User> findAll();
}
