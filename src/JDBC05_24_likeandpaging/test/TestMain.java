package JDBC05_24_likeandpaging.test;

import JDBC05_24_likeandpaging.dao.EmpDao;
import JDBC05_24_likeandpaging.domain.Emp;
import JDBC05_24_likeandpaging.service.EmpService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TestMain {

    public static void main(String[] args) {
        EmpDao dao = new EmpDao();
        ArrayList<HashMap<String,Object>> list = dao.selectCountByGroup();
        for (HashMap<String,Object> map:list){
            System.out.println(map.get("deptno")+"---"+map.get("ct"));
        }





//        EmpDao dao = new EmpDao();
//        ArrayList<Emp> list = dao.selectAllEmpAndDept();
//        for (Emp emp:list){
//            System.out.println(emp);
//        }



//        EmpService service = new EmpService();
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请您输入需要查询的页码");
//        int page = sc.nextInt();
//        ArrayList<Emp> list = service.changePagToRowIndex(page);
//        for (Emp e:list){
//            System.out.println(e);
//        }

    }
}
