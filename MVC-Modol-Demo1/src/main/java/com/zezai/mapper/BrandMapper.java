package com.zezai.mapper;

import com.zezai.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    @Select("select * from tb_brand")
    @ResultMap("Map")
    List<Brand> selectAll();
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void addBrand(Brand brand);

    @Select("select * from tb_brand where id=#{id}")
    @ResultMap("Map")
    Brand selectById(int id);

    @Update("update tb_brand set brandName=#{brandName},companyName=#{companyName}, ordered=#{ordered}, description=#{description},status=#{status}")
    @ResultMap("Map")
    void update(Brand brand);

    @Delete("delete from tb_brand where id=#{id}")
    void deleteBrandById(int id);
}
