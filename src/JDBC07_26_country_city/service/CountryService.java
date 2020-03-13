package JDBC07_26_country_city.service;

import JDBC07_26_country_city.dao.CountryDao;

import java.util.ArrayList;
import java.util.HashMap;

public class CountryService {

    private CountryDao dao = new CountryDao();

    public ArrayList<HashMap<String,String>> select2(){
        return dao.select2();
    }

    public ArrayList<String> select11(int sumCitySize){
        return dao.select11(sumCitySize);
    }

    public ArrayList<String> select4(String cityname){
        return dao.select4(cityname);
    }
    public ArrayList<String> select6(String cname){
        return dao.select6(cname);
    }
}
