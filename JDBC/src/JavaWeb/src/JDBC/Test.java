package JavaWeb.src.JDBC;

import java.sql.*;

public class Test {
    public static void main(String[] args) throws Exception {
        //注册驱动（未来可以不写）
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取连接
        //url(连接路径):
        /*
        语法:jdbc:mysql://ip地址(域名):端口号/数据库名字
        例:jdbc:mysql://localhost:3306/jdbc
        细节:如果连接的是本机mysql服务器,并且mysql服务器默认端口是3306,则url可简写为:jdbc:mysql:///
             配置useSSL=false参数,禁用安全连接方式,解决警告提示  例:jdbc:mysql://localhost:3306/jdbc?useSSL=false
         */
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "123456");
        //定义SQL语句
        String SQL1 = "Update user set age=30 where id=1";
        String SQL2 = "Update user set age=20 where id=2";
        String SQL3 = "Select * from user";

        //获取执行SQL的对象
        Statement statement = conn.createStatement();
        //执行SQL,并创建事务
        int a = 0;//该返回值是一个int类型的受影响的行--1
        int b = 0;//该返回值是一个int类型的受影响的行--1
        try {
            conn.setAutoCommit(false);
            a = statement.executeUpdate(SQL1);
            b = statement.executeUpdate(SQL2);
            ResultSet resultSet=statement.executeQuery(SQL3);
            while(resultSet.next()) {
                String c=resultSet.getString(2);
                System.out.println(c);
            }
            //无异常直接提交事务
            conn.commit();
        } catch (Exception e) {
            //如果抓到异常，直接回滚事务
            conn.rollback();
            throw new RuntimeException(e);
        }
        //处理结果
        if (a > 0) {
            System.out.println("修改成功");
        } else
            System.out.println("修改失败");
        if (b > 0) {
            System.out.println("修改成功");
        } else
            System.out.println("修改失败");
        //释放资源
        statement.close();
        conn.close();
    }
}

/*
Connection事务管理
  开启事务:setAutoCommit(boolean)  //true为自动提交失误,false为手动提交事务

  提交事务:commit();

  回滚事务:rollback();
 */

/*
Statement用于执行SQL语句
int executeUpdate(SQL) //执行增删改操作,返回的是影响的行数,执行删除操作成功也可能返回0

ResultSet executeQuery(SQL) //执行查找操作,返回的是结果集对象,Resultset就是已经封装好的返回值结果
Resultset提供了两种方法:
1.boolean(返回值) next() //将光标从当前位置(默认位置是指向第一个字段的上面一行)向下移动一行,并且判断该行是否有数据,有则返回true,反之false
2.数据类型(返回值) get数据类型(参数)  //获取当前光标具体的数据,参数有两种写法,一是(int)列号(从1开始),二是直接用(String)列的名称
 */