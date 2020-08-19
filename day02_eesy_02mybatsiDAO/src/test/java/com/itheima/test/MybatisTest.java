package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.dao.impl.UserDaoImpl;
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

    private InputStream in;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        //1.��ȡ�����ļ��������ֽ�������
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2. ��ȡSqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3. ʹ�ù������󣬴���dao����
        userDao = new UserDaoImpl(factory);
    }

    @After
    public void destroy() throws IOException {
        //�ͷ���Դ
        in.close();
    }


    //���Բ�ѯ����
    @Test
    public void testFindAll(){
        //5.ִ�в�ѯ���з���
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }

    }

    /**
     * ���Ա������
     */
    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("modify User property");
        user.setAddress("������˳����");
        user.setSex("��");
        user.setBirthday(new Date());
        System.out.println("�������֮ǰ��"+user);
        //5.ִ�б��淽��
        userDao.saveUser(user);

        System.out.println("�������֮��"+user);
    }

    /**
     * ����ģ����ѯ����
     */
    @Test
    public void testFindByName(){
        //5.ִ�в�ѯһ������
        List<User> users = userDao.findByName("%��%");
        for(User user : users){
            System.out.println(user);
        }
    }


    /**
     * ���Բ�ѯ�ܼ�¼����
     */
    @Test
    public void testFindTotal(){
        //5.ִ�в�ѯһ������
        int count = userDao.findTotal();
        System.out.println(count);
    }

}
