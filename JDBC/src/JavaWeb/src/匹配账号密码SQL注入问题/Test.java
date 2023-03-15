package JavaWeb.src.匹配账号密码SQL注入问题;

import java.sql.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc&useServerPrepStmts=true", "root", "123456");
        System.out.print("请输入您的用户名:");                                                        //开启预编译功能
        String username = sc.next();
        System.out.print("请输入您的密码:");
        String password = sc.next();
        //方法一:
        /*String SQL = "Select * from user where username='"+username+"' and password ='"+password+"'";
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery(SQL);
                if(resultSet.next()){
                System.out.println("登录成功");                           //此方法会被非法注入 ('or'1'='1)
                }
                else{
                System.out.println("登陆失败");
                }*/

        //方法二:
        String SQL = "select * from user where username = ? and password = ?";//原来的值被?代替
        PreparedStatement ps = connection.prepareStatement(SQL);
        ps.setString(1, username);//第一个问号表示
        ps.setString(2, password);//第二个问号表示
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("登录成功");                          //此方法通过转义字符达到安全目标.并且预编译还可以提高运行效率
        } else {
            System.out.println("登陆失败");
        }
    }
}
