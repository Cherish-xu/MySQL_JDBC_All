package JDBC08_27_atmsystem.service;

import JDBC08_27_atmsystem.dao.AtmDao;
import JDBC08_27_atmsystem.domain.Atm;
import JDBC08_27_atmsystem.util.MySpring;

public class AtmService {
    //业务层---->数据处理  判断  比较  计算。。。

    private AtmDao dao = MySpring.getBean("JDBC08_27_atmsystem.dao.AtmDao");

    //设计一个登录方法
    public String login(String aname,String apassword){
        String result = "";
        Atm atm = dao.selectOne(aname);
        if (atm != null && atm.getApassword().equals(apassword)){
            result = "登录成功";
        }else {
            result = "用户名或密码错误";
        }
        return result;
    }

    //设计一个查询余额的方法
    public Float inquire(String aname){
        return  dao.selectOne(aname).getAbalance();
    }

    //设计一个注册方法
    public int regist(String aname,String apassword,Float abalance){
        Atm atm = new Atm(aname,apassword,abalance);
        return dao.insert(atm);
    }

    //判断账号名是否存在
    public boolean isExist(String aname){
        if(dao.selectOne(aname)!=null){
            return true;//当前账号存在
        }
        return false;//账号不存在
    }

    //存款
    public int deposit(String aname,Float money){
        Atm atm = dao.selectOne(aname);
        atm.setAbalance(atm.getAbalance()+money);
        return dao.update(atm);
    }

    //取款
    public int withdrawal(String aname,Float money){
        Atm atm = dao.selectOne(aname);
        if (atm.getAbalance() >= money){
            atm.setAbalance(atm.getAbalance()-money);
            return dao.update(atm);
        }else {
            //余额不足
            return -1;
        }
    }

    //转账
    public int transfer(String outName,String inName,Float money){
        Atm outAtm = dao.selectOne(outName);
        Atm inAtm = dao.selectOne(inName);
        if (outAtm.getAbalance() >= money){
            outAtm.setAbalance(outAtm.getAbalance() - money);
            inAtm.setAbalance(inAtm.getAbalance() + money);
            //如果结果是2则说明两个操作同时成功
            return dao.update(outAtm) + dao.update(inAtm);
        }else {
            //金额不足
            return -1;
        }
    }

    //销户
    public int closeAccount(String aname){
        return dao.delete(aname);
    }
}
