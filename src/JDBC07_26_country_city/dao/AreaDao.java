package JDBC07_26_country_city.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AreaDao {

    private String className = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/lianxi?useSSL=false&serverTimezone=CST";
    private String user = "zhangtianxu";
    private String password = "1211";

    //1.查询人数在1000到2000之间的城市所属地区
    //参数   人口范围
    public ArrayList<String> select1(int begin,int end){
        ArrayList<String> list = new ArrayList<String>();
        String sql = "select aname from area a inner join city ci on a.aid = ci.aid where ci.citysize between ? and ? group by aname";
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1,begin);
            pstat.setInt(2,end);
            rs = pstat.executeQuery();
            while (rs.next()){
                String value = rs.getString("aname");
                list.add(value);
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
        return list;
    }
    public ArrayList<HashMap<String,String>> select3(){
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        String sql = "select aname,avg(citysize) from area \n" +
                "inner join city on area.aid = city.aid \n" +
                "group by aname order by avg(citysize) desc;";
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            rs = pstat.executeQuery();
            while (rs.next()){
                HashMap<String,String> map = new HashMap<String, String>();
                map.put("aname",rs.getString("aname"));
                map.put("avg(citysize)",rs.getString("avg(citysize)"));
                list.add(map);
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
        return list;
    }

    public ArrayList<HashMap<String,String>> select5(){
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        String sql = "select aname,sum(citysize) from area \n" +
                "inner join city on area.aid = city.aid \n" +
                "group by aname;";
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            rs = pstat.executeQuery();
            while (rs.next()){
                HashMap<String,String> map = new HashMap<String, String>();
                map.put("aname",rs.getString("aname"));
                map.put("sum(citysize)",rs.getString("sum(citysize)"));
                list.add(map);
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
        return list;
    }
}
