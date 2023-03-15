package com.itheima;

import com.itheima.Mapper.jdbcMapper;
import com.itheima.pojo.User1;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test以后都写test的java文件里 {
    public static void main(String[] args) throws IOException {
        //加载Mybatis核心配置文件,获取SqlSessionFactory
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        //获取SqlSession对象,以执行SQL语句(类似Statement)
        SqlSession sqlSession=sqlSessionFactory.openSession();

        //执行SQL
        // List<User1> users=sqlSession.selectList("jdbctest.selectAll"); 以前的方法

        jdbcMapper mapper=sqlSession.getMapper(jdbcMapper.class);//参数为Mapper配置文件名.class
        List<User1> users=mapper.selectAll();//通过Mapper代理开发实现
        System.out.println(users);

        //释放资源
        sqlSession.close();
    }
}
