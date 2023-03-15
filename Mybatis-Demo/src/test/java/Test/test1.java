package Test;

import com.itheima.Mapper.BrandMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class test1 {
    public static void main(String[] args) throws IOException {
        //加载Mybatis核心配置文件,获取SqlSessionFactory
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

         int[] arr={5,6};
        //获取SqlSession对象,以执行SQL语句(类似Statement)
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        /*Brand brand=new Brand();
        brand.setStatus(1);
        brand.setCompanyName("波导手机");          //使用实体类对象传参方式
        brand.setBrandName("波导");
        brand.setOrdered(50);
        brand.setDescription("手机中的战斗机");*/

        /*Map map = new HashMap();
        map.put("status", 1);
        map.put("companyName", "%华为%");         //使用Map传参方式
        map.put("brandName", "%华为%");*/

        //获取mapper代理对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);//参数为Mapper配置文件名.class
        mapper.selectAll();//通过Mapper代理开发实现
        System.out.println( mapper.selectAll());
        //System.out.println(users);
        sqlSession.close();
    }
}
