package JDBC03_21.atmProject.dao;

import JDBC03_21.atmProject.domain.Atm;

import java.sql.*;

public class AtmDao {
    //登录和查询余额方法内部几乎一致
    //优化查询
    public Atm selectOne(String aname){
        Atm atm = null;
        String className = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/atm?useSSL=false&serverTimezone=CST";
        String use = "zhangtianxu";
        String password = "1211";
        String sql = "SELECT ANAME,APASSWORD,ABALANCE FROM ATM WHERE ANAME = '"+aname+"'";
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,use,password);
            stat =  conn.createStatement();
            rs = stat.executeQuery(sql);
            if (rs.next()){
                atm = new Atm();
                atm.setAname(rs.getString("aname"));
                atm.setApassword(rs.getString("apassword"));
                atm.setAbalance(rs.getFloat("abalance"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return atm;
    }

    //存款和取款代码大量冗余
    //优化修改
    public void update(Atm atm){
        //修改数据库信息
        //1.jdbc连接
        String className = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/atm?useSSL=false&serverTimezone=CST";
        String use = "zhangtianxu";
        String password = "1211";
        String sql = "update atm set apassword = '"+atm.getApassword()+"',abalance = "+atm.getAbalance()+"where aname = '"+atm.getAname()+"'";

        Connection conn = null;
        Statement stat = null;
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,use,password);
            stat =  conn.createStatement();
            stat.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //负责添加新的记录
    public void insert(Atm atm){
        String className = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/atm?useSSL=false&serverTimezone=CST";
        String use = "zhangtianxu";
        String password = "1211";
        String sql = "insert into atm values('"+atm.getAname()+"','"+atm.getApassword()+"',a+"+atm.getAbalance()+")";

        Connection conn = null;
        Statement stat = null;
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,use,password);
            stat =  conn.createStatement();
            stat.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //负责删除一条记录
    public void delete(String aname){
        String className = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/atm?useSSL=false&serverTimezone=CST";
        String use = "zhangtianxu";
        String password = "1211";
        String sql = "delete from atm where aname = '"+aname+"'";

        Connection conn = null;
        Statement stat = null;
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,use,password);
            stat =  conn.createStatement();
            stat.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
