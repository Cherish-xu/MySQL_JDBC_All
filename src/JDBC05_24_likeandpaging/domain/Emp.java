package JDBC05_24_likeandpaging.domain;

import java.sql.Date;

public class Emp {
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate;
    private Float sal;
    private Float comm;
    //private Integer deptno;   //将这个外键改为一个对象
    private Dept dept;

    public Emp() {}
    public Emp(Integer empno, String ename, String job, Integer mgr, Date hiredate, Float sal, Float comm, Integer deptno) {
        this.empno = empno;
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
    }

    public Integer getEmpno() {
        return empno;
    }
    public void setEmpno(Integer empno) {
        this.empno = empno;
    }
    public String getEname() {
        return ename;
    }
    public void setEname(String ename) {
        this.ename = ename;
    }
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }
    public Integer getMgr() {
        return mgr;
    }
    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }
    public Date getHiredate() {
        return hiredate;
    }
    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }
    public Float getSal() {
        return sal;
    }
    public void setSal(Float sal) {
        this.sal = sal;
    }
    public Float getComm() {
        return comm;
    }
    public void setComm(Float comm) {
        this.comm = comm;
    }
    public Dept getDept() {
        return dept;
    }
    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder("{");
        builder.append(this.empno);
        builder.append(",");
        builder.append(this.ename);
        builder.append(",");
        builder.append(this.job);
        builder.append(",");
        builder.append(this.mgr);
        builder.append(",");
        builder.append(this.hiredate);
        builder.append(",");
        builder.append(this.sal);
        builder.append(",");
        builder.append(this.comm);
        builder.append(",");
        builder.append(this.dept);
        builder.append("}");
        return builder.toString();
    }
}
