package JDBC08_27_atmsystem.view;

import JDBC08_27_atmsystem.service.AtmService;
import JDBC08_27_atmsystem.util.BaseFrame;
import JDBC08_27_atmsystem.util.MySpring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("all")
public class RegistFrame extends BaseFrame {

    private RegistFrame(){
        super("注册窗口");
        this.init();
    }

    private static RegistFrame registFrame;
    public synchronized static RegistFrame getRegistFrame(){
        if (registFrame==null){
            registFrame = new RegistFrame();
        }
        return registFrame;
    }


    //添加一个控制页面登录的属性
    private LoginFrame loginFrame = LoginFrame.getLoginFrame();
    //添加一些组件的属性
    private JPanel mainPanel = new JPanel();
    private JLabel logoLabel = new JLabel();
    private JLabel titleLabel = new JLabel("请 您 填 写 注 册 信 息");
    private JLabel accountLabel = new JLabel("请输入账号:");
    private JTextField accountField = new JTextField();//输入账号
    private JLabel passwordLabel = new JLabel("请输入密码");
    private JTextField passwordField = new JTextField();
    private JLabel balanceLabel = new JLabel("请输入金额:");
    private JTextField balanceField = new JTextField();
    private JButton registButton = new JButton("注 册");
    private JButton resetButton = new JButton("重 置");
    private JButton backButton = new JButton("返 回");


    @Override
    protected void setFontAndSoOn() {
        mainPanel.setLayout(null);
        logoLabel.setBounds(135,40,40,40);
        logoLabel.setIcon(this.drawImage("src//JDBC08_27_atmsystem//img//duyi.jpg",40,40));
        titleLabel.setBounds(185,40,200,40);
        titleLabel.setFont(new Font("微软雅黑",Font.BOLD,24));
        accountLabel.setBounds(40,100,140,40);
        accountLabel.setFont(new Font("微软雅黑",Font.BOLD,18));
        accountField.setBounds(170,105,260,32);
        accountField.setFont(new Font("微软雅黑",Font.BOLD,20));

        passwordLabel.setBounds(40,150,140,40);
        passwordLabel.setFont(new Font("微软雅黑",Font.BOLD,18));
        passwordField.setBounds(170,155,260,32);
        passwordField.setFont(new Font("微软雅黑",Font.BOLD,20));

        balanceLabel.setBounds(40,200,140,40);
        balanceLabel.setFont(new Font("微软雅黑",Font.BOLD,18));
        balanceField.setBounds(170,205,260,32);
        balanceField.setFont(new Font("微软雅黑",Font.BOLD,20));

        registButton.setBounds(60,260,100,32);
        registButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        registButton.setBackground(Color.LIGHT_GRAY);

        resetButton.setBounds(190,260,100,32);
        resetButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        resetButton.setBackground(Color.LIGHT_GRAY);

        backButton.setBounds(320,260,100,32);
        backButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        backButton.setBackground(Color.LIGHT_GRAY);
    }
    @Override
    protected void addElements() {
        mainPanel.add(logoLabel);
        mainPanel.add(titleLabel);
        mainPanel.add(accountLabel);
        mainPanel.add(accountField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(balanceLabel);
        mainPanel.add(balanceField);
        mainPanel.add(registButton);
        mainPanel.add(resetButton);
        mainPanel.add(backButton);
        this.add(mainPanel);

    }
    @Override
    protected void addListener() {
        //注册按钮
        registButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取新输入的 名字 密码 余额
                String aname = accountField.getText();
                String apassword = passwordField.getText();
                String abalance = balanceField.getText();
                //调用新增方法
                AtmService service = MySpring.getBean("JDBC08_27_atmsystem.service.AtmService");
                if(service.isExist(aname)){//账号存在
                    JOptionPane.showMessageDialog(RegistFrame.this,"账号已存在");
                    RegistFrame.this.reset();//所有输入框清空
                }else {
                    try{
                        //zhangsan  333  abc
                        service.regist(aname,apassword,Float.parseFloat(abalance));
                        JOptionPane.showMessageDialog(RegistFrame.this, "注册成功,请登录系统");
                        RegistFrame.this.back();
                    }catch(NumberFormatException nfe){
                        JOptionPane.showMessageDialog(RegistFrame.this, "对不起,您输入的金额有误");
                        RegistFrame.this.reset();//所有输入框清空
                    }
                }
            }
        });
        //重置按钮
        registButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistFrame.this.reset();
            }
        });
        //返回按钮
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.setVisible(true);
                RegistFrame.this.setVisible(false);//将当前注册窗口隐藏
                loginFrame.reset();
            }
        });
    }
    //设计一个自定义方法 功能为将所有输入框清空
    void reset(){
        accountField.setText("");
        passwordField.setText("");
        balanceField.setText("");
    }

    //设计一个自定义方法 设置返回登录界面
    private void back(){
        this.setVisible(false);
        loginFrame.setVisible(true);
    }

    @Override
    protected void setFrameSelf() {
        this.setBounds(430,200,500,360);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//2
        this.setResizable(false);
        this.setVisible(true);
    }
}
