package JDBC08_27_atmsystem.view;

import JDBC08_27_atmsystem.service.AtmService;
import JDBC08_27_atmsystem.util.BaseFrame;
import JDBC08_27_atmsystem.util.MySpring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AtmFrame extends BaseFrame {

    private AtmFrame(String aname){
        super("操作窗口");
        this.aname = aname;
        this.init();
    }
    private static AtmFrame atmFrame;
    public synchronized static AtmFrame getAtmFrame(String aname){
        if (atmFrame == null){
            atmFrame = new AtmFrame(aname);
        }
        return atmFrame;
    }

    /**创建一个AtmService对象作为属性  支持着所有的业务    查询  存款  取款  转账*/
    private AtmService service = MySpring.getBean("JDBC08_27_atmsystem.service.AtmService");

    /**添加一个用来管理当前用户的用户名*/
    private String aname;


    /**添加窗体上的组件*/
    private JPanel mainPanel = new JPanel();
    private JLabel logoLabel = new JLabel();//logo
    private JLabel titleLabel = new JLabel("渡一银行");
    private JLabel balanceLabelChinese = new JLabel();
    private JLabel balanceLabelEnglish = new JLabel();
    private JLabel selectServerLabelCinese = new JLabel("您好!请选择所需服务");
    private JLabel selectServerLabelEnglish = new JLabel("Please Select Service");
    private JButton messageButton = new JButton("销户");
    private JButton exitButton = new JButton("退出");
    private JButton depositButton = new JButton("存款");
    private JButton withdrawalButton = new JButton("取款");
    private JButton transferButton = new JButton("转账");


    @Override
    protected void setFontAndSoOn() {
        mainPanel.setLayout(null);
        logoLabel.setBounds(20,20,80,80);
        logoLabel.setIcon(this.drawImage("src//JDBC08_27_atmsystem//img//duyi.jpg",80,80));
        titleLabel.setBounds(120,30,160,60);
        titleLabel.setFont(new Font("微软雅黑",Font.ITALIC,32));

        balanceLabelChinese.setBounds(250,200,300,40);
        balanceLabelChinese.setFont(new Font("微软雅黑",Font.BOLD,24));
        balanceLabelChinese.setHorizontalAlignment(JTextField.CENTER);
        balanceLabelChinese.setText("账户余额:￥"+service.inquire(aname));
        balanceLabelEnglish.setBounds(240,240,320,40);
        balanceLabelEnglish.setFont(new Font("微软雅黑",Font.BOLD,24));
        balanceLabelEnglish.setHorizontalAlignment(JTextField.CENTER);
        balanceLabelEnglish.setText("Account Balance:￥"+service.inquire(aname));

        selectServerLabelCinese.setBounds(250,370,300,40);
        selectServerLabelCinese.setFont(new Font("微软雅黑",Font.BOLD,16));
        selectServerLabelCinese.setHorizontalAlignment(JTextField.CENTER);
        selectServerLabelEnglish.setBounds(250,400,300,40);
        selectServerLabelEnglish.setFont(new Font("微软雅黑",Font.BOLD,16));
        selectServerLabelEnglish.setHorizontalAlignment(JTextField.CENTER);

        messageButton.setBounds(10,150,120,40);
        messageButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        messageButton.setBackground(Color.lightGray);
        messageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        exitButton.setBounds(10,390,120,40);
        exitButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        exitButton.setBackground(Color.lightGray);
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        depositButton.setBounds(670,150,120,40);
        depositButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        depositButton.setBackground(Color.lightGray);
        depositButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        withdrawalButton.setBounds(670,270,120,40);
        withdrawalButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        withdrawalButton.setBackground(Color.lightGray);
        withdrawalButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        transferButton.setBounds(670,390,120,40);
        transferButton.setFont(new Font("微软雅黑",Font.BOLD,14));
        transferButton.setBackground(Color.lightGray);
        transferButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }
    @Override
    protected void addElements() {
        mainPanel.add(logoLabel);
        mainPanel.add(titleLabel);
        mainPanel.add(balanceLabelChinese);
        mainPanel.add(balanceLabelEnglish);
        mainPanel.add(selectServerLabelCinese);
        mainPanel.add(selectServerLabelEnglish);
        mainPanel.add(messageButton);
        mainPanel.add(exitButton);
        mainPanel.add(depositButton);
        mainPanel.add(withdrawalButton);
        mainPanel.add(transferButton);
        this.add(mainPanel);

    }

    @Override
    protected void addListener() {

        //退出按钮
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //value的值 0：是  1：否   2：取消
                int value = JOptionPane.showConfirmDialog(AtmFrame.this, "确认退出吗");
                if (value == 0) {
                    System.exit(0);
                }
            }
        });

        //销户按钮
        messageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = JOptionPane.showConfirmDialog(AtmFrame.this, "确认注销当前账户吗");
                if (value == 0) {
                    if (service.closeAccount(aname) == 1){
                        JOptionPane.showMessageDialog(AtmFrame.this, "销户成功");
                        System.exit(0);
                    }else {
                        JOptionPane.showMessageDialog(AtmFrame.this,"销户失败");
                    }
                }
            }
        });

        //存款按钮
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = JOptionPane.showInputDialog(AtmFrame.this,"请输入存款金额");
                try{
                    if (value != null && !"".equals(value)) {
                        //转化为存款金额（float型）
                        Float depositMonry = Float.parseFloat(value);
                        if (depositMonry <= 0){
                            throw new NumberFormatException();
                        }
                        int count = service.deposit(aname,depositMonry);
                        if (count == 1){
                            JOptionPane.showMessageDialog(AtmFrame.this, "存款成功");
                            balanceLabelChinese.setText("账户余额:￥" + service.inquire(aname));
                            balanceLabelEnglish.setText("Account Balance:￥" + service.inquire(aname));

                        }else {
                            JOptionPane.showMessageDialog(AtmFrame.this,"存款失败");
                        }
                    }
                }catch (NumberFormatException nfe){
                    JOptionPane.showMessageDialog(AtmFrame.this,"输入的金额有误");

                }
            }
        });

        //取款按钮
        withdrawalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = JOptionPane.showInputDialog(AtmFrame.this,"请输入取款金额");
                try{
                    if (value != null && !"".equals(value)) {
                        //转化为存款金额（float型）
                        Float witchdrawalMonry = Float.parseFloat(value);
                        if (witchdrawalMonry <= 0){
                            throw new NumberFormatException();
                        }
                        int count = service.withdrawal(aname,witchdrawalMonry);
                        if (count == 1){
                            JOptionPane.showMessageDialog(AtmFrame.this, "取款成功");
                            balanceLabelChinese.setText("账户余额:￥" + service.inquire(aname));
                            balanceLabelEnglish.setText("Account Balance:￥" + service.inquire(aname));

                        }else if(count == -1){
                            JOptionPane.showMessageDialog(AtmFrame.this,"余额不足");
                        }else {
                            JOptionPane.showMessageDialog(AtmFrame.this,"取款失败");
                        }
                    }
                }catch (NumberFormatException nfe){
                    JOptionPane.showMessageDialog(AtmFrame.this,"输入的金额有误");

                }
            }
        });

        //转账按钮
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(AtmFrame.this,"请输入转账账户");
                try{
                    //名字非等于空，不等于空串，并且名字存在
                    if (name != null && !"".equals(name) && service.isExist(name)) {
                        String value = JOptionPane.showInputDialog(AtmFrame.this, "请输入转账金额");
                        if (value != null && !"".equals(value)){
                            Float transferMoney = Float.parseFloat(value);
                            if (transferMoney < 0){
                                throw new NumberFormatException();
                            }
                            //aname：自己的账户  name：转账的账户
                            int count = service.transfer(aname,name,transferMoney);
                            if (count == 2){
                                JOptionPane.showMessageDialog(AtmFrame.this, "转账成功");
                                balanceLabelChinese.setText("账户余额:￥" + service.inquire(aname));
                                balanceLabelEnglish.setText("Account Balance:￥" + service.inquire(aname));
                            }else if(count == -1){
                                JOptionPane.showMessageDialog(AtmFrame.this,"余额不足");
                            }else {
                                JOptionPane.showMessageDialog(AtmFrame.this,"转账失败");
                            }
                        }else {
                            throw new NumberFormatException();
                        }

                    }else {
                        JOptionPane.showMessageDialog(AtmFrame.this,"账户不存在");
                    }
                }catch (NumberFormatException nfe){
                    JOptionPane.showMessageDialog(AtmFrame.this,"输入的金额有误");

                }
            }
        });
    }

    @Override
    protected void setFrameSelf() {
        this.setBounds(300,200,800,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
}
