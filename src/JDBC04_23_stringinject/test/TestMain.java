package JDBC04_23_stringinject.test;

import JDBC04_23_stringinject.test.domain.Atm;
import JDBC04_23_stringinject.test.service.AtmService;

import java.sql.BatchUpdateException;
import java.util.Scanner;

public class TestMain {

    public static void main(String[] args) {
        AtmService as = new AtmService();
        Scanner sc = new Scanner(System.in);
        System.out.println("请选择管理员登录或客户登录");
        System.out.println("管理员登录请输入1，客户登录请输入2");
        String button1 = sc.nextLine();
        switch (button1) {
            case "1":
                System.out.println("请输入管理员用户名");
                String user = sc.nextLine();
                System.out.println("请输入管理员账户密码");
                String password = sc.nextLine();
                if (as.root(user,password).equals("管理员权限登录成功")) {
                    while (true) {
                        System.out.println("请选择开通账户或注销账户");
                        System.out.println("开通账户请输入1，注销账户请输入2,退出请输入0");
                        String button3 = sc.nextLine();
                        if (button3.equals("0")){
                            break;
                        }
                        switch (button3) {
                            case "1":
                                System.out.println("请输入账户名称");
                                String newUser = sc.nextLine();
                                System.out.println("请输入用户密码");
                                String newPassword = sc.nextLine();
                                System.out.println("请输入账户初始金额");
                                String newBalance = sc.nextLine();
                                as.kai(newUser, newPassword, Float.parseFloat(newBalance));
                                break;
                            case "2":
                                System.out.println("请输入要注销的账户用户名");
                                String dUser = sc.nextLine();
                                as.xiao(dUser);
                                break;
                        }
                        break;
                    }
                }else {
                    System.out.println("管理员权限账户登录失败");
                }
            case"2":
                System.out.println("请输入账号");
                String aname = sc.nextLine();
                System.out.println("请输入密码");
                String apassword = sc.nextLine();
                AtmService t = new AtmService();
                String result = t.login(aname,apassword);
                if (result.equals("登录成功")){
                    System.out.println("欢迎进入银行系统");
                    while(true){
                        System.out.println("请输入操作选项");
                        System.out.println("查询余额请输入1，存款请输入2，取款请输入3，转账请输入4，退出请输入0");
                        String button2 = sc.nextLine();
                        if (button2.equals("0")){
                            break;
                        }
                        switch (button2) {
                            case "1":
                                float abalance = as.chaxun(aname);
                                System.out.println("尊敬的" + aname + "，您的账户余额为人民币" + abalance + "元。");
                                break;
                            case "2":
                                System.out.println("请输入存款金额");
                                String cunMoney = sc.nextLine();
                                as.cunkuan(aname,Float.parseFloat(cunMoney));
                                break;
                            case "3":
                                System.out.println("请输入取款金额");
                                String quMoney = sc.nextLine();
                                as.qukuan(aname,Float.parseFloat(quMoney));
                                break;
                            case "4":
                                System.out.println("请输入转出账户名");
                                String outUser = sc.nextLine();
                                System.out.println("请输入转入账户名");
                                String inUser = sc.nextLine();
                                System.out.println("请输入转账金额");
                                String zhuanMoney = sc.nextLine();
                                as.zhuanzhang(outUser,inUser,Float.parseFloat(zhuanMoney));
                                break;
                        }
                    }
                }else {
                    System.out.println(result);
                }
        }
    }
}
