package com.zezai.web;

import com.zezai.mapper.UserMapper;
import com.zezai.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        //获取输入的用户名,密码
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        //通过Mybatis操作数据库的数据，以下都是Mybatis的操作步骤
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession=sqlSessionFactory.openSession();

        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        User user=userMapper.check(username);

        //对得到的数据进行判断,并且输出结果
        response.setContentType("text/html;charset=utf-8");
        PrintWriter Writer=response.getWriter();
        if(user!=null){
            Writer.write("用户名已存在,请重试");
        }else{
            userMapper.register(username,password);
            sqlSession.commit();//一定要记得提交事务
            Writer.write("注册成功");
        }
        sqlSession.close();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        this.doGet(request,response);
    }
}
