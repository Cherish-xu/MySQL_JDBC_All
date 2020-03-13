package JDBC05_24_likeandpaging.service;

import JDBC05_24_likeandpaging.dao.EmpDao;
import JDBC05_24_likeandpaging.domain.Emp;

import java.util.ArrayList;

public class EmpService {

    private EmpDao dao = new EmpDao();

    //负责将用户从视图层传递过来的页码转换为dao层需要的行索引
//    public ArrayList<Emp> changePagToRowIndex(int page){
//        //1.将page计算成索引 rowIndex
//        int rowIndex = (page-1)*5;
//        //2.调用方法
//        ArrayList<Emp> list = dao.selectByPaging(rowIndex);
//        return list;
//    }
}
