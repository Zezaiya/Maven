package com.zezai.service;

import com.zezai.mapper.BrandMapper;
import com.zezai.pojo.Brand;
import com.zezai.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService {
    public List<Brand> selectAll(){
        //通过service,可以避免在service中直接使用mapper,提高代码的复用性
        //因为我们已经创建了SqlSessionFactory工具类,所以直接调用即可
        SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession=factory.openSession();
        BrandMapper mapper=sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands=mapper.selectAll();
        sqlSession.close();
        return brands;
    };
    public void addBrand(Brand brand){
        SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession=factory.openSession();
        BrandMapper mapper=sqlSession.getMapper(BrandMapper.class);
        mapper.addBrand(brand);
        //增删改记得提交事务
        sqlSession.commit();
        sqlSession.close();
    }
    public Brand selectById(int id){
        SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession=factory.openSession();
        BrandMapper mapper=sqlSession.getMapper(BrandMapper.class);
        Brand brand=mapper.selectById(id);
        sqlSession.close();
        return brand;
    }

    public void update(Brand brand){
        SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession=factory.openSession();
        BrandMapper mapper=sqlSession.getMapper(BrandMapper.class);
        mapper.addBrand(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    public void deleteBrandById(int id){
        SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession=factory.openSession();
        BrandMapper mapper=sqlSession.getMapper(BrandMapper.class);
        mapper.deleteBrandById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
