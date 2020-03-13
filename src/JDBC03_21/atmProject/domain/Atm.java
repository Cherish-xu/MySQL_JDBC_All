package JDBC03_21.atmProject.domain;

public class Atm {

    private String aname;
    private String apassword;
    private Float abalance;

    public Atm(){}

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword;
    }

    public Float getAbalance() {
        return abalance;
    }

    public void setAbalance(Float abalance) {
        this.abalance = abalance;
    }

    public Atm(String aname, String password, Float abalance){
        this.abalance = abalance;
        this.aname = aname;
        this.apassword = apassword;
    }
}
