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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        //接收用户名，密码
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println("用户名:"+username);
        System.out.println("密码:"+password);
        //通过Mybatis操作数据库的数据，以下都是Mybatis的操作步骤
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession=sqlSessionFactory.openSession();

        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        User user=userMapper.select(username,password);

        sqlSession.close();
        //对得到的数据进行判断,并且输出结果
        response.setContentType("text/html;charset=utf-8");
        PrintWriter Writer=response.getWriter();
        if(user!=null){
            HttpSession session= request.getSession();
            session.setAttribute("username",username);

            //获取当前页面位置并实现页面跳转
            String contextPath=request.getContextPath();
            response.sendRedirect(contextPath+"/");
        }else{
            Writer.write("登录失败");
        }

    }
    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
               this.doGet(request,response);
    }
}
