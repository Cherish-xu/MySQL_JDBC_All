package JDBC07_26_country_city.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CountryDao {

    private String className = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/lianxi?useSSL=false&serverTimezone=CST";
    private String user = "zhangtianxu";
    private String password = "1211";

    public ArrayList<HashMap<String,String>> select2(){
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        String sql = "select cname,count(ci.cityid) as citycount from country c \n" +
                "inner join area a on c.cid = a.cid \n" +
                "inner join city ci on a.aid = ci.aid \n" +
                "group by c.cname \n" +
                "order by citycount asc;";
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
                map.put("cname",rs.getString("cname"));
                map.put("citycount",rs.getString("citycount"));
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


    public ArrayList<String> select11(int sumCitySize){
        ArrayList<String> list = new ArrayList<String>();
        String sql = "select c.cname from country c \n" +
                "inner join area a on c.cid = a.cid \n" +
                "inner join city ci on a.aid = ci.aid \n" +
                "group by c.cname \n" +
                "having sum(ci.citysize) > ?;";
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1,sumCitySize);
            rs = pstat.executeQuery();
            while (rs.next()){
                String value = rs.getString("cname");
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

    public ArrayList<String> select4(String cityname){
        ArrayList<String> list = new ArrayList<String>();
        String sql = "select cname from country \n" +
                "inner join area on country.cid = area.cid \n" +
                "inner join city on area.aid = city.aid where cityname = ?;\n;";
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            pstat.setString(1,cityname);
            rs = pstat.executeQuery();
            while (rs.next()){
                String value = rs.getString("cname");
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

    public ArrayList<String> select6(String cname){
        ArrayList<String> list = new ArrayList<String>();
        String sql = "select cityname from country \n" +
                "inner join area on country.cid = area.cid\n" +
                "inner join city on area.aid = city.aid \n" +
                "where cname = ?;";
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            pstat.setString(1,cname);
            rs = pstat.executeQuery();
            while (rs.next()){
                String value = rs.getString("cityname");
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
}
