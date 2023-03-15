package com.zezai.controller;

import com.zezai.pojo.Brand;
import com.zezai.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

    @WebServlet("/selectByIdServlet")
    public class selectByIdServlet extends HttpServlet {
        //获取BrandService对象,这样就可以使用service进行操作
        private BrandService service=new BrandService();
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String id=request.getParameter("id");
            //调用里面的方法获取数据库内的数据
            Brand brand=service.selectById(Integer.parseInt(id));
            //将得到的数据存入request域中
            request.setAttribute("brand",brand);
            //转发给视图jsp
            request.getRequestDispatcher("/update.jsp").forward(request,response);

        }

        @Override
        protected void doPost (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
            this.doGet(request, response);
        }
    }

