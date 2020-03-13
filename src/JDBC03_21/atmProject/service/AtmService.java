package JDBC03_21.atmProject.service;

import JDBC03_21.atmProject.dao.AtmDao;
import JDBC03_21.atmProject.domain.Atm;

public class AtmService {

    //Service业务层需要Dao持久层的支持
    //在Service层中存储一个Dao层的对象作为属性
    private AtmDao dao = new AtmDao();

    //登录
    public String login(String aname,String apassword) {
        String result = "用户名或密码错误";
        Atm atm = dao.selectOne(aname);
        if(atm != null){
            if(atm.getApassword().equals(apassword)){
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
        //先找到原始的数据库  atm = selectOne
        Atm atm = dao.selectOne(aname);
        //只需要做修改的操作
        atm.setAbalance(atm.getAbalance()+money);
        //将最终的数据交给  update
        dao.update(atm);
    }

    //取款
    public void qukuan(String aname,float money){
        //先找到原始的数据库  atm = selectOne
        Atm atm = dao.selectOne(aname);
        //只需要做修改的操作
        //先判断
        if (atm.getAbalance() >= money) {
            atm.setAbalance(atm.getAbalance() - money);
            //将最终的数据交给  update
            dao.update(atm);
        }else{
            System.out.println("对不起，您的余额不足");
        }
    }

    //转账
    public void zhuanzhang(String outName,String inName,float zhuanMoney){
//        this.qukuan(outName,zhuanMoney);
//        this.cunkuan(inName,zhuanMoney);
        Atm outAtm = dao.selectOne(outName);
        Atm inAtm = dao.selectOne(inName);
        if(outAtm.getAbalance() >= zhuanMoney){
            outAtm.setAbalance(outAtm.getAbalance() - zhuanMoney);
            inAtm.setAbalance(inAtm.getAbalance() + zhuanMoney);
            dao.update(outAtm);
            dao.update(inAtm);
        }else {
            System.out.println("对不起，您的余额不足");
        }
    }

    //开户
    public void kai(String aname,String apassword,Float abalance){
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
}
