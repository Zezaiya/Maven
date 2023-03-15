package com.zezai.controller;

import com.zezai.pojo.Brand;
import com.zezai.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/addServlet")
    public class addServlet extends HttpServlet {
        //获取BrandService对象,这样就可以使用service进行操作
        private BrandService service=new BrandService();
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //处理乱码问题
            request.setCharacterEncoding("utf-8");

            Brand brand=new Brand();
            String brandName=request.getParameter("brandName");
            String companyName=request.getParameter("companyName");
            String ordered=request.getParameter("ordered");
            String description=request.getParameter("description");
            String status=request.getParameter("status");

            brand.setBrandName(brandName);
            brand.setCompanyName(companyName);
            brand.setOrdered(Integer.parseInt(ordered));
            brand.setDescription(description);
            brand.setStatus(Integer.parseInt(status));

            service.addBrand(brand);
            //转发给addServlet
            request.getRequestDispatcher("/selectAllServlet").forward(request,response);

        }

        @Override
        protected void doPost (HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
            this.doGet(request, response);
        }
    }

