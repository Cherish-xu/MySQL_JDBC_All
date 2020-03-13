package JDBC01_19_firstjdbc.testjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC {

    public static void main(String[] args) {
        try {
            //1.导包
            //2.加载驱动类   com.mysql.jdbc.Driver
            String className = "com.mysql.cj.jdbc.Driver";
            Class.forName(className);
            //3.创建连接    DrverManager
            //结构：jdbc:mysql://ip:port/databese名字
            String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=GMT";
            String user = "root";//数据库用户名
            String password = "0504";//数据库密码
            Connection conn = DriverManager.getConnection(url,user,password);
            //4.创建连接参数
            Statement stat = conn.createStatement();
            //5.执行操作
            //添加数据
            //String sql = "insert into teacher(tid,tname) values(4,'zzt4')";
            //String sql = "delete from teacher where tid = 4";
            //stat.executeUpdate(sql);
            System.out.println("执行完毕");
            //6.关闭
            stat.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
