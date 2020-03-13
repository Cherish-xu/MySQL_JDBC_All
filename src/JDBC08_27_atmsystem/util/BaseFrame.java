package JDBC08_27_atmsystem.util;

import javax.swing.*;
import java.awt.*;

public abstract class BaseFrame extends JFrame {
    //模板模式

    public BaseFrame() {}

    public BaseFrame(String title) {
        super(title);
    }

    //通用的画图方法   给定宽高
    protected ImageIcon drawImage(String path, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(path);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        return imageIcon;
    }

    //设计一个具体的方法 规定加载窗体时的执行顺序
    protected void init() {
        this.setFontAndSoOn();
        this.addElements();
        this.addListener();
        this.setFrameSelf();
    }

    //1.用来设置字体、颜色、背景、布局管理 等待
    protected abstract void setFontAndSoOn();

    //2.用来添加所有的组件
    protected abstract void addElements();

    //3.添加事件监听器
    protected abstract void addListener();

    //4.设置窗体自身的属性
    protected abstract void setFrameSelf();
}
