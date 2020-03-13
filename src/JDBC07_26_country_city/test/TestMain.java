package JDBC07_26_country_city.test;

import JDBC03_21.atmProject.test.TestAtm;
import JDBC06_25_practice.domain.Area;
import JDBC07_26_country_city.service.AreaService;
import JDBC07_26_country_city.service.CountryService;

import java.util.ArrayList;
import java.util.HashMap;

public class TestMain {

    public static void main(String[] args) {
        //TestMain.select1();
        //TestMain.select2();
        //TestMain.select11();
        //TestMain.select3();
        //TestMain.select4();
        //TestMain.select5();
        TestMain.select6();
    }

    public static void select6(){
        CountryService countryService = new CountryService();
        ArrayList<String> list = countryService.select6("美国");
        for (String s : list){
            System.out.println(s);
        }
    }

    public static void select5(){
        AreaService areaService = new AreaService();
        ArrayList<HashMap<String,String>> list = areaService.select5();
        for (HashMap<String,String> map : list){
            System.out.println(map.get("aname"+":"+map.get("sum(citysize)")));
        }
    }

    public static void select1(){
        AreaService areaService = new AreaService();
        ArrayList<String> list = areaService.select1();
        for (String s:list){
            System.out.println(s);
        }
    }

    public static void select2(){
        CountryService countryService = new CountryService();
        ArrayList<HashMap<String,String>> list = countryService.select2();
        for (HashMap<String,String> map:list){
            System.out.println(map.get("cname")+":"+map.get("citycount"));
        }
    }

    public static void select3(){
        AreaService areaService = new AreaService();
        ArrayList<HashMap<String,String>> list = areaService.select3();
        for (HashMap<String,String> map:list){
            System.out.println(map.get("aname"+":"+map.get("avgsize")));
        }
    }

    public static void select11(){
        CountryService countryService = new CountryService();
        ArrayList<String> list = countryService.select11(5000);
        for (String value:list){
            System.out.println(value);
        }
    }

    public static void select4(){
        CountryService countryService = new CountryService();
        ArrayList<String> list = countryService.select4("哈尔滨");
        for (String s : list){
            System.out.println(s);
        }
    }

}
