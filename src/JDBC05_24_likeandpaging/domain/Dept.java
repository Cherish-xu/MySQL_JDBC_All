package JDBC05_24_likeandpaging.domain;

public class Dept {

    private Integer deptno;
    private String dname;
    private String loc;

    public Dept(Integer deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }
    public Dept() {}
    public Integer getDeptno() {
        return deptno;
    }
    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }
    public String getDname() {
        return dname;
    }
    public void setDname(String dname) {
        this.dname = dname;
    }
    public String getLoc() {
        return loc;
    }
    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder("{");
        builder.append(this.deptno);
        builder.append(",");
        builder.append(this.dname);
        builder.append(",");
        builder.append(this.loc);
        builder.append("}");
        return builder.toString();
    }
}
