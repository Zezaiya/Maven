package JavaWeb.src.数据库版学生管理系统;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

//实现对数据库的增删查改
public class Test {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        //连接数据库
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "123456");
        String SQL = "select *from user";
        Statement statement = conn.createStatement();
        while (true) {
            //创建集合存储User对象集
            ArrayList<User> List = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getInt("age"));
                List.add(user);//每一次操作会刷新表

            }
            System.out.println("表长:" + List.size());
            System.out.println("查找记录------1");
            System.out.println("添加记录------2");
            System.out.println("删除记录------3");
            System.out.println("修改记录------4");
            System.out.println("安全退出------0");
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    Find(List);
                    System.out.print("请输入任意键继续:");
                    sc.next();
                    break;
                case 2:
                    Add(List, conn);
                    System.out.print("请输入任意键继续:");
                    sc.next();
                    break;
                case 3:
                    Delete(List, conn);
                    System.out.print("请输入任意键继续:");
                    sc.next();
                    break;
                case 4:
                    Alter(List,conn);
                    System.out.print("请输入任意键继续:");
                    sc.next();
                    break;
                case 0:
                    System.exit(0);
                    statement.close();
                    conn.close();
                    break;
            }
        }
    }

    public static void Find(ArrayList<User> List) {
        int b = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入对应序号:");
        int input = sc.nextInt();
        for (int i = 0; i < List.size(); i++) {
            if (input == List.get(i).getId()) {
                System.out.println("查找成功,记录如下");
                System.out.println(List.get(i).getUser());
                b++;
            }
        }
        if (b == 0) {
            System.out.println("无此记录,请重试!");
        }

    }

    public static void Add(ArrayList<User> List, Connection conn) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请按顺序完成添加");
        System.out.println("\n");
        System.out.print("姓名:");
        String name = sc.next();
        System.out.print("用户名:");
        String username = sc.next();
        System.out.print("密码:");
        String password = sc.next();
        System.out.print("年龄:");
        int age = sc.nextInt();
        String AddSql = "insert into user(id,name,username,password,age) value(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(AddSql);
        ps.setInt(1, List.size() + 1);
        ps.setString(2, name);
        ps.setString(3, username);
        ps.setString(4, password);
        ps.setInt(5, age);
        int resultSet = ps.executeUpdate();
        if (resultSet > 0) {
            System.out.println("添加成功");
        } else
            System.out.println("添加失败");
    }

    public static void Delete(ArrayList<User> List, Connection conn) throws Exception {
        if (List.size() == 0) {
            System.out.println("系统内暂无记录，请重试");
            System.out.println("\n");
            return;
        }
        int b = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入对应序号");
        int input = sc.nextInt();
        for (int i = 0; i < List.size(); i++) {
            if (input == List.get(i).getId()) {
                System.out.println("查找成功,记录如下");
                System.out.println(List.get(i).getUser());
                System.out.println("删除----1,取消----2");
                int input1= sc.nextInt();
                if (input1 == 1) {
                    String DeleteSql = "delete from user where id=?";
                    PreparedStatement ps=conn.prepareStatement(DeleteSql);
                    ps.setInt(1,input);
                    ps.executeUpdate();
                    b++;
                    break;
                }
                else{
                    System.out.println("您已取消删除");
                    return;
                }
            }
        }
        if (b == 0)
            System.out.println("记录不存在,请重试!");
        else
            System.out.println("删除成功!");
    }


    public static void Alter(ArrayList<User> List,Connection conn) throws Exception{
        if (List.size() == 0) {
            System.out.println("系统内暂无记录，请重试");
            System.out.println("\n");
            return;
        }
        int b = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入对应序号");
        int input = sc.nextInt();
        for (int i = 0; i < List.size(); i++) {
            if (input == List.get(i).getId()) {
                System.out.println("查找成功,记录如下");
                System.out.println(List.get(i).getUser());
                System.out.println("修改name-------1, 修改username---2, 修改password---3, 修改age--------4, 取消-----------0");
                int input1= sc.nextInt();
                if (input1 == 1) {
                    System.out.print("请输入修改后的姓名:");
                    String str=sc.next();
                    String UpdateSql = "Update user set name=? where id = ?";
                    PreparedStatement ps=conn.prepareStatement(UpdateSql);
                    ps.setString(1,str);
                    ps.setInt(2,input);
                    ps.executeUpdate();
                    b++;
                    break;
                }
               else if (input1 == 2) {
                    System.out.print("请输入修改后的用户名:");
                    String str=sc.next();
                    String UpdateSql = "Update user set username=? where id = ?";
                    PreparedStatement ps=conn.prepareStatement(UpdateSql);
                    ps.setString(1,str);
                    ps.setInt(2,input);
                    ps.executeUpdate();
                    b++;
                    break;
                }
                else if (input1 ==3 ) {
                    System.out.print("请输入修改后的密码:");
                    String str=sc.next();
                    String UpdateSql = "Update user set password=? where id = ?";
                    PreparedStatement ps=conn.prepareStatement(UpdateSql);
                    ps.setString(1,str);
                    ps.setInt(2,input);
                    ps.executeUpdate();
                    b++;
                    break;
                }
               else if (input1 == 4) {
                    System.out.print("请输入修改后的年龄:");
                    int a=sc.nextInt();
                    String UpdateSql = "Update user set age=? where id = ?";
                    PreparedStatement ps=conn.prepareStatement(UpdateSql);
                    ps.setInt(1,a);
                    ps.setInt(2,input);
                    ps.executeUpdate();
                    b++;
                    break;
                }
               else if(input1==0){
                    System.out.println("您已取消修改");
                    return;
                }
                else{
                    System.out.println("输入的值有误,请重试");
                    return;
                }
            }
        }
        if (b == 0)
            System.out.println("记录不存在,请重试!");
        else
            System.out.println("修改成功!");
    }
}
