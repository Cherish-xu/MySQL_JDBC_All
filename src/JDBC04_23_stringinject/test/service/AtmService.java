package JDBC04_23_stringinject.test.service;

import JDBC04_23_stringinject.test.dao.AtmDao;
import JDBC04_23_stringinject.test.domain.Atm;

public class AtmService {

    private AtmDao dao = new AtmDao();

    //设计一个登录方法
    public String login(String aname,String apassword) {
        String result = "用户名或密码错误";
        Atm atm = dao.selectOne(aname);
        if (atm != null) {
            if (atm.getApassword().equals(apassword)) {
                result = "登录成功";
            }
        }
        return result;
    }
    //查询
    public float chaxun(String aname){
        float result = 0;
        Atm atm = dao.selectOne(aname);
        result = atm.getAbalance();
        return result;
    }
    //存款
    public void cunkuan(String aname,float money){
        Atm atm = dao.selectOne(aname);
        atm.setAbalance(atm.getAbalance()+money);
        dao.update(atm);
    }
    //取款
    public void qukuan(String aname,float money){
        Atm atm = dao.selectOne(aname);
        atm.setAbalance(atm.getAbalance()-money);
        dao.update(atm);
    }
    //转账
    public void zhuanzhang(String outUser,String inUser,float money){
        if(dao.selectOne(outUser).getAbalance() >= money){
            Atm outAtm = dao.selectOne(outUser);
            outAtm.setAbalance(outAtm.getAbalance()-money);
            dao.update(outAtm);
            Atm inAtm = dao.selectOne(inUser);
            inAtm.setAbalance(inAtm.getAbalance()+money);
            dao.update(inAtm);
        }else {
            System.out.println("对不起，您的账户余额不足");
        }
    }
    //开户
    public void kai(String aname,String apassword,float abalance){
        Atm atm = new Atm(aname,apassword,abalance);
        dao.insert(atm);
    }
    //销户
    public void xiao(String aname){
        Atm atm = dao.selectOne(aname);
        if(atm != null){
            dao.delete(aname);
        }
    }
    //管理员权限登录
    public String root(String user,String password){
        String result = dao.root(user,password);
        return result;
    }
}
