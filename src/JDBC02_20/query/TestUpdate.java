package JDBC02_20.query;

import java.sql.*;
import com.mysql.cj.jdbc.Driver;

public class TestUpdate {

    public static void main(String[] args) {
        try {
            //第一种加载方式
            Class.forName("com.mysql.cj.jdbc.Driver");
            //第二种加载方式（与第三种差不多，不过会加载两遍）
            //DriverManager.registerDriver(new Driver());
            //第三种加载方式
            //new Driver();
            //通过System类中设置属性值来加载
            //System.setProperty("jdbc.driver","com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=CST";
            String use = "root";
            String password = "0504";
            Connection conn = DriverManager.getConnection(url,use,password);
            Statement stat = conn.createStatement();
//            String sql = "insert into teacher values(4,'zzt4','nan','now()')";
//            int count = stat.executeUpdate(sql);//返回值表示改变的行数
//            System.out.println("执行完毕");
            //查询操作
            //stat.executeUpdate("update  teacher set tbirthday = '2000-02-02' where tid = 4");
            ResultSet rs = stat.executeQuery("select * from teacher");
            while (rs.next()){
                int tid = rs.getInt("tid");
                String tname = rs.getString("tname");
                String tsex = rs.getString("tsex");
                String tbirthday = rs.getString("tbirthday");
                System.out.println(tid+"--"+tname+"--"+tsex+"--"+tbirthday);
            }
            System.out.println("执行完毕");
            stat.close();
            conn.close();
        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
