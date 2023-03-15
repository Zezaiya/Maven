package JavaWeb.src.数据库版学生管理系统;

public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private int age;

    public User() {
    }

    ;

    public User(int id, String name, String username, String password, int age) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
    }

    ;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {return age;}

    public String getUser() {
        String All = "序号:"+this.id+"\t"+"姓名:"+this.name+"\t"+"用户名:"+this.username+"\t"+"密码:"+this.password+"\t"+"年龄:"+this.age;
        return All;
    }
}
