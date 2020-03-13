package JDBC03_21.atmProject.test;

import JDBC03_21.atmProject.service.AtmService;

import java.util.Scanner;

public class TestMain {

    public static void main(String[] args) {
        AtmService ta = new AtmService();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String aname = sc.nextLine();
        System.out.println("请输入密码");
        String apassword = sc.nextLine();
        String result = ta.login(aname,apassword);
        System.out.println(result);
        if(result.equals("登录成功")){
            System.out.println("欢迎您登录");
            while (true) {
                System.out.println("请输入业务选项");
                System.out.println("查询余额请按1，存款业务请按2，取款业务请按3，转账请按4,退出请按0 ");
                String button = sc.nextLine();
                if(button.equals("0")) {
                    break;
                }
                switch (button) {
                    case "1":
                        float abalance = ta.chaxun(aname);
                        System.out.println("尊敬的" + aname + "，您的账户余额为人民币" + abalance + "元。");
                        break;
                    case "2":
                        System.out.println("请输入存款金额");
                        String cunMoney = sc.nextLine();
                        ta.cunkuan(aname, Float.parseFloat(cunMoney));
                        abalance = ta.chaxun(aname);
                        System.out.println("尊敬的" + aname + "，您的账户余额为人民币" + abalance + "元。");
                        break;
                    case "3":
                        System.out.println("请输入取款金额");
                        String quMoney = sc.nextLine();
                        ta.qukuan(aname,Float.parseFloat(quMoney));
                        abalance = ta.chaxun(aname);
                        System.out.println("尊敬的" + aname + "，您的账户余额为人民币" + abalance + "元。");
                        break;
                    case "4":
                        System.out.println("请输入转出方账户");
                        String outName = sc.nextLine();
                        System.out.println("请输入接收方账户");
                        String inName = sc.nextLine();
                        System.out.println("请输入转款金额");
                        String zhuanMoney = sc.nextLine();
                        ta.zhuanzhang(outName,inName,Float.parseFloat(zhuanMoney));
                        break;
                }
            }
        }
    }
}
