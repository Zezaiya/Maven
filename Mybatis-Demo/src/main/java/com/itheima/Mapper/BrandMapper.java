package com.itheima.Mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    List<Brand> selectAll();//查找所有

    Brand selectOne(int id);//通过id查找某一记录

    List<Brand> variousSelect(@Param("status") Integer status, @Param("companyName") String companyName, @Param("brandName") String brandName);//通过多种条件查询(状态,企业名称,品牌名称),其中企业品牌名称使用模糊查询

    List<Brand> variousSelect(Map map);

    List<Brand> variousSelect(Brand brand);

    List<Brand> singleSelect(Brand brand);

    void add(Brand brand);

    void update(Brand brand);

    void movingUpdate(Brand brand);

    void deleteOne(int id);

    void movingDelete(@Param("ids") int[] id);
}
