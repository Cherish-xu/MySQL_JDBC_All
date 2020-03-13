package JDBC07_26_country_city.service;

import JDBC07_26_country_city.dao.AreaDao;

import java.util.ArrayList;
import java.util.HashMap;

public class AreaService {

    private AreaDao dao = new AreaDao();

    public ArrayList<String> select1(){
        return dao.select1(1000,2000);
    }

    public ArrayList<HashMap<String,String>> select3(){
        return dao.select3();
    }

    public ArrayList<HashMap<String,String>> select5(){
        return dao.select5();
    }

}
