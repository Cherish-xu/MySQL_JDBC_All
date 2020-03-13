package JDBC05_24_likeandpaging.dao;

import JDBC05_24_likeandpaging.domain.Dept;
import JDBC05_24_likeandpaging.domain.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmpDao {
    private String className = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/lianxi?useSSL=false&serverTimezone=CST";
    private String user = "zhangtianxu";
    private String password = "1211";

    //设计一个方法查询emp中每个部门的人数
    public ArrayList<HashMap<String,Object>> selectCountByGroup(){
        ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
        String sql = "select deptno,count(empno) as ct from emp group by deptno";
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            rs = pstat.executeQuery();
            while (rs.next()){
                HashMap<String,Object> map = new HashMap<>();
                map.put("deptno",rs.getInt("deptno"));
                map.put("ct",rs.getInt("ct"));
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




    //设计一个方法    用来查询所有emp关联dept的数据
    public ArrayList<Emp> selectAllEmpAndDept(){
        ArrayList<Emp> list = new ArrayList<Emp>();
        String sql = "select * from emp e,dept d where e.deptno = d.deptno";
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            Class.forName(className);
            conn = DriverManager.getConnection(url,user,password);
            pstat = conn.prepareStatement(sql);
            rs = pstat.executeQuery();
            while (rs.next()){
                Emp emp = new Emp();
                emp.setEmpno(rs.getInt("empno"));
                emp.setEname(rs.getString("ename"));
                emp.setJob(rs.getString("job"));
                emp.setMgr(rs.getInt("mgr"));
                emp.setHiredate(rs.getDate("hiredate"));
                emp.setSal(rs.getFloat("sal"));
                emp.setComm(rs.getFloat("comm"));
                Dept dept = new Dept();
                dept.setDeptno(rs.getInt("deptno"));
                dept.setDname(rs.getString("dname"));
                dept.setLoc(rs.getString("loc"));
                emp.setDept(dept);
                list.add(emp);
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





    //分页查询
//    public ArrayList<Emp> selectByPaging(int rowIndex) {
//        ArrayList<Emp> list = new ArrayList<Emp>();
//        String sql = "select * from emp order by sal desc limit ?,5";
//        Connection conn = null;
//        PreparedStatement pstat = null;
//        ResultSet rs = null;
//        try {
//            Class.forName(className);
//            Class.forName(className);
//            conn = DriverManager.getConnection(url, user, password);
//            pstat = conn.prepareStatement(sql);
//            pstat.setInt(1, rowIndex);
//            rs = pstat.executeQuery();
//            while (rs.next()) {
//                Emp emp = new Emp();
//                emp.setEmpno(rs.getInt("empno"));
//                emp.setEname(rs.getString("ename"));
//                emp.setJob(rs.getString("job"));
//                emp.setMgr(rs.getInt("mgr"));
//                emp.setHiredate(rs.getDate("hiredate"));
//                emp.setSal(rs.getFloat("sal"));
//                emp.setComm(rs.getFloat("comm"));
//                emp.setDeptno(rs.getInt("deptno"));
//                list.add(emp);
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (pstat != null) {
//                try {
//                    pstat.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return list;
//    }








    //模糊查询
//    public ArrayList<Emp> selectForLike(String letter){
//        ArrayList<Emp> list = new ArrayList<Emp>();
//        String sql = "select empno,ename,job,mgr,hiredate,sal,comm,deptno from emp where ename like ?";
//        Connection conn = null;
//        PreparedStatement pstat = null;
//        ResultSet rs = null;
//        try {
//            Class.forName(className);
//            conn = DriverManager.getConnection(url,user,password);
//            pstat = conn.prepareStatement(sql);
//            pstat.setString(1,"%"+letter+"%");
//            rs = pstat.executeQuery();
//            while (rs.next()){
//                Emp emp = new Emp();
//                emp.setEmpno(rs.getInt("empno"));
//                emp.setEname(rs.getString("ename"));
//                emp.setJob(rs.getString("job"));
//                emp.setMgr(rs.getInt("mgr"));
//                emp.setHiredate(rs.getDate("hiredate"));
//                emp.setSal(rs.getFloat("sal"));
//                emp.setComm(rs.getFloat("comm"));
//                emp.setDeptno(rs.getInt("deptno"));
//                list.add(emp);
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            if (rs != null){
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (pstat != null){
//                try {
//                    pstat.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (conn != null){
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return list;
//    }






    //查询多条记录
//    public ArrayList<Emp> selectAll(){
//        ArrayList<Emp> list = new ArrayList<Emp>();
//        String sql = "select empno,ename,job,mgr,hiredate,sal,comm,deptno from emp";
//        Connection conn = null;
//        PreparedStatement pstat = null;
//        ResultSet rs = null;
//        try {
//            Class.forName(className);
//            Class.forName(className);
//            conn = DriverManager.getConnection(url,user,password);
//            pstat = conn.prepareStatement(sql);
//            rs = pstat.executeQuery();
//            while (rs.next()){
//                Emp emp = new Emp();
//                emp.setEmpno(rs.getInt("empno"));
//                emp.setEname(rs.getString("ename"));
//                emp.setJob(rs.getString("job"));
//                emp.setMgr(rs.getInt("mgr"));
//                emp.setHiredate(rs.getDate("hiredate"));
//                emp.setSal(rs.getFloat("sal"));
//                emp.setComm(rs.getFloat("comm"));
//                emp.setDeptno(rs.getInt("deptno"));
//                list.add(emp);
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            if (rs != null){
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (pstat != null){
//                try {
//                    pstat.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (conn != null){
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return list;
//    }



    //设计一个方法    查询单条的emp表格记录
//    public Emp selectOne(Integer empno){
//        Emp emp = null;
//        String sql = "select empno,ename,job,mgr,hiredate,sal,comm,deptno from emp where empno = ?";
//        Connection conn = null;
//        PreparedStatement pstat = null;
//        ResultSet rs = null;
//        try {
//            Class.forName(className);
//            conn = DriverManager.getConnection(url,user,password);
//            pstat = conn.prepareStatement(sql);
//            pstat.setInt(1,empno);
//            rs = pstat.executeQuery();
//            if (rs.next()){
//                emp = new Emp();
//                emp.setEmpno(rs.getInt("empno"));
//                emp.setEname(rs.getString("ename"));
//                emp.setJob(rs.getString("job"));
//                emp.setMgr(rs.getInt("mgr"));
//                emp.setHiredate(rs.getDate("hiredate"));
//                emp.setSal(rs.getFloat("sal"));
//                emp.setComm(rs.getFloat("comm"));
//                emp.setDeptno(rs.getInt("deptno"));
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            if (rs != null){
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (pstat != null){
//                try {
//                    pstat.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (conn != null){
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return emp;
//    }
}
