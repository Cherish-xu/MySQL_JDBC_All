package JDBC08_27_atmsystem.dao;

import JDBC08_27_atmsystem.domain.Atm;

import java.net.URI;
import java.sql.*;

public class AtmDao {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/atm?useSSL=false&serverTimezone=CST";
    private String user = "root";
    private String password = "0504";

    //Dao--->数据的读写（JDBC）--持久层

    //设计一个方法    负责查询一行记录
    public Atm selectOne(String aname){
        Atm atm = null;
        String sql = "SELECT ANAME,APASSWORD,ABALANCE FROM ATM WHERE ANAME = ?";

        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            pstat.setString(1,aname);
            rs = pstat.executeQuery();
            if (rs.next()){
                atm = new Atm();
                atm.setAname(rs.getString("aname"));
                atm.setApassword(rs.getString("apassword"));
                atm.setAbalance(rs.getFloat("abalance"));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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

    //设计一个方法    负责新增一条记录
    public int insert(Atm atm){
        String sql = "INSERT INTO ATM VALUES(?,?,?)";
        int count = 0;//表示数据库新增的行数
        Connection conn = null;
        PreparedStatement pstat = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            pstat.setString(1,atm.getAname());
            pstat.setString(2,atm.getApassword());
            pstat.setFloat(3,atm.getAbalance());
            count = pstat.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
        return count;
    }

    //数据库的更新操作----修改
    public int update(Atm atm) {
        //更改的行数
        int count = 0;
        String sql = "UPDATE ATM SET APASSWORD = ?,ABALANCE = ? WHERE ANAME = ?";
        Connection conn = null;
        PreparedStatement pstat = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, atm.getApassword());
            pstat.setFloat(2,atm.getAbalance());
            pstat.setString(3,atm.getAname());
            count = pstat.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
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
        return count;
    }

    //删除数据操作
    public int delete(String aname){
        String sql = "DELETE FROM ATM WHERE ANAME = ?";
        int count = 0;//表示数据库新增的行数
        Connection conn = null;
        PreparedStatement pstat = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            pstat.setString(1,aname);
            count = pstat.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
        return count;
    }
}
