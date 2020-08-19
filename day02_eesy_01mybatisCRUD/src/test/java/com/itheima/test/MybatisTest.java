package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Target;
import java.util.Date;
import java.util.List;

/**
 * @author: zhangycl
 * @date: 2020/8/17
 * @description:
 */
public class MybatisTest {

    /*
    * 入门案例
    * */
    public static void main(String[] args) throws Exception {
        //1. 读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2. 创建SqlsessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3. 利用工厂生产SQLSession对象
        SqlSession session = factory.openSession();
        //4. 使用SQLSession创建Dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);
        //5. 使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user : users) {
            System.out.println(user);
        }
        //6. 释放资源
        session.close();
        in.close();

    }


    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        //1. 读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2. 获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3. 获取SQLSession 对象
        sqlSession = factory.openSession();
        //4. 获取dao的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }


    @After //用于在测试方法执行之后执行
    public void destroy() throws IOException {
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        in.close();
    }


    /*
    测试查询所有
    * */
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /*
    * 测试保存操作
    * */
    @Test
    public void testSave() {
        User user = new User();
        user.setUserName("zhang");
        user.setUserAddress("北京市顺义区");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        System.out.println("保存操作之前："+user);
        //执行保存方法
        userDao.saveUser(user);

        System.out.println("保存操作之后："+user);
    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdate(){
        User user = new User();
        user.setUserId(57);
        user.setUserName("mybastis ok");
        user.setUserAddress("北京市顺义区");
        user.setUserSex("女");
        user.setUserBirthday(new Date());

        //5.执行保存方法
        userDao.updateUser(user);
    }


    /**
     * 测试删除操作
     */
    @Test
    public void testDelete(){
        //5.执行删除方法
        userDao.deleteUser(58);
    }

    /**
     * 测试删除操作
     */
    @Test
    public void testFindOne(){
        //5.执行查询一个方法
        User  user = userDao.findById(57);
        System.out.println(user);
    }

    /**
     * 测试模糊查询操作
     */
    @Test
    public void testFindByName(){
        //5.执行查询一个方法
//        List<User> users = userDao.findByName("%超%");
        List<User> users = userDao.findByName("超");
        for(User user : users){
            System.out.println(user);
        }
    }




    /**
     * 测试查询总记录条数
     */
    @Test
    public void testFindTotal(){
        //5.执行查询一个方法
        int count = userDao.findTotal();
        System.out.println(count);
    }

    /**
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void testFindByVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%超%");
        vo.setUser(user);
        //执行查询一个方法
        List<User> users = userDao.findUserByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }


}
