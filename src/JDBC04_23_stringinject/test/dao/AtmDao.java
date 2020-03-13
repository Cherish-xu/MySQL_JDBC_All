package JDBC04_23_stringinject.test.dao;

import JDBC04_23_stringinject.test.domain.Atm;

import java.sql.*;

public class AtmDao {
    //优化的查询
    public Atm selectOne(String aname){
        Atm atm = null;
        String className = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/atm?useSSL=false&serverTimezone=CST";
        String user = "zhangtianxu";
        String password = "1211";
        String sql = "select * from atm where aname = ?";
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);//预处理状态参数
            // 给sql语句中的问号赋值
            pstat.setString(1,aname);
            rs = pstat.executeQuery();

            //将结果集内的内容取出来存入一个对象
            if (rs.next()){
                atm = new Atm();
                atm.setAname(rs.getString("aname"));
                atm.setApassword(rs.getString("apassword"));
                atm.setAbalance(rs.getFloat("abalance"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstat != null){
                try {
                    pstat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return atm;
    }

    //优化的修改     存款 and 取款
    public void update(Atm atm){
        //1.建立jdbc连接
        String className = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/atm?useSSL=false&serverTimezone=CST";
        String user = "zhangtianxu";
        String password = "1211";
        String sql = "update atm set abalance = "+atm.getAbalance()+",apassword = '"+atm.getApassword()+"' where aname = ? ";
        Connection conn = null;
        PreparedStatement pstat = null;
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            pstat.setString(1,atm.getAname());
            pstat.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(pstat != null){
                try {
                    pstat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //优化添加
    public void insert(Atm atm){
        String className = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/atm?useSSL=false&serverTimezone=CST";
        String user = "zhangtianxu";
        String password = "1211";
        String sql = "insert into atm values(?,?,?)";

        Connection conn = null;
        PreparedStatement pstat = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            pstat.setString(1,atm.getAname());
            pstat.setString(2,atm.getApassword());
            pstat.setFloat(3,atm.getAbalance());
            pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (pstat != null){
                try {
                    pstat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //优化删除
    public void delete(String aname){
        String className = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/atm?useSSL=false&serverTimezone=CST";
        String user = "zhangtianxu";
        String password = "1211";
        String sql = "delete from atm where aname = ?";

        Connection conn = null;
        PreparedStatement pstat = null;
        try {
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            pstat.setString(1,aname);
            pstat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (pstat != null){
                try {
                    pstat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //管理员登录数据库
    public String root(String user,String passwore){
        String result = "登录失败";
        String className = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/atm?useSSL=false&serverTimezone=CST";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,passwore);
            if (conn != null){
                result = "管理员权限登录成功";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }{
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
