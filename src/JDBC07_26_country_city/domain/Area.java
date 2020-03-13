package JDBC07_26_country_city.domain;

public class Area {

    private Integer aid;
    private String aname;
    private Country country;

    public Area(Integer aid, String aname, Country country) {
        this.aid = aid;
        this.aname = aname;
        this.country = country;
    }

    public Area() {}

    public Integer getAid() {
        return aid;
    }
    public void setAid(Integer aid) {
        this.aid = aid;
    }
    public String getAname() {
        return aname;
    }
    public void setAname(String aname) {
        this.aname = aname;
    }
    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }
}
