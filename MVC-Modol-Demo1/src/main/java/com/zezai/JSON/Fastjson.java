package com.zezai.JSON;

import com.alibaba.fastjson.JSON;
import com.zezai.pojo.Brand;

public class Fastjson {
    public static void main(String[] args) {
        //java对象转JSON
        Brand brand=new Brand();
        brand.setBrandName("三只松鼠");
        brand.setId(0);
        String jsonStr= JSON.toJSONString(brand);
        System.out.println(jsonStr);

        //JSON转Java对象
        Brand brand1=JSON.parseObject("{\"id\":1, \"brandName\":\"三只松鼠\"}",Brand.class);
        System.out.println(brand1);
    }
}
