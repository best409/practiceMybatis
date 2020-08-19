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
    * ���Ű���
    * */
    public static void main(String[] args) throws Exception {
        //1. ��ȡ�����ļ�
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2. ����SqlsessionFactory����
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3. ���ù�������SQLSession����
        SqlSession session = factory.openSession();
        //4. ʹ��SQLSession����Dao�ӿڵĴ������
        IUserDao userDao = session.getMapper(IUserDao.class);
        //5. ʹ�ô������ִ�з���
        List<User> users = userDao.findAll();
        for(User user : users) {
            System.out.println(user);
        }
        //6. �ͷ���Դ
        session.close();
        in.close();

    }


    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        //1. ��ȡ�����ļ��������ֽ�������
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2. ��ȡSqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3. ��ȡSQLSession ����
        sqlSession = factory.openSession();
        //4. ��ȡdao�Ĵ������
        userDao = sqlSession.getMapper(IUserDao.class);
    }


    @After //�����ڲ��Է���ִ��֮��ִ��
    public void destroy() throws IOException {
        //�ύ����
        sqlSession.commit();
        //�ͷ���Դ
        sqlSession.close();
        in.close();
    }


    /*
    ���Բ�ѯ����
    * */
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /*
    * ���Ա������
    * */
    @Test
    public void testSave() {
        User user = new User();
        user.setUserName("zhang");
        user.setUserAddress("������˳����");
        user.setUserSex("��");
        user.setUserBirthday(new Date());
        System.out.println("�������֮ǰ��"+user);
        //ִ�б��淽��
        userDao.saveUser(user);

        System.out.println("�������֮��"+user);
    }

    /**
     * ���Ը��²���
     */
    @Test
    public void testUpdate(){
        User user = new User();
        user.setUserId(57);
        user.setUserName("mybastis ok");
        user.setUserAddress("������˳����");
        user.setUserSex("Ů");
        user.setUserBirthday(new Date());

        //5.ִ�б��淽��
        userDao.updateUser(user);
    }


    /**
     * ����ɾ������
     */
    @Test
    public void testDelete(){
        //5.ִ��ɾ������
        userDao.deleteUser(58);
    }

    /**
     * ����ɾ������
     */
    @Test
    public void testFindOne(){
        //5.ִ�в�ѯһ������
        User  user = userDao.findById(57);
        System.out.println(user);
    }

    /**
     * ����ģ����ѯ����
     */
    @Test
    public void testFindByName(){
        //5.ִ�в�ѯһ������
//        List<User> users = userDao.findByName("%��%");
        List<User> users = userDao.findByName("��");
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

    /**
     * ����ʹ��QueryVo��Ϊ��ѯ����
     */
    @Test
    public void testFindByVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%��%");
        vo.setUser(user);
        //ִ�в�ѯһ������
        List<User> users = userDao.findUserByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }


}
