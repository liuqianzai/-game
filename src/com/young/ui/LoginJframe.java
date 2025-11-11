package com.young.ui;

import Utils.CodeUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginJframe extends JFrame implements ActionListener {

    //利用静态变量存储一个用户表
    static ArrayList<User> list = new ArrayList<>();
    static {
        list.add(new User("zhangsan","123"));
        list.add(new User("lisi","1234"));
    }
    JButton login = new JButton("登录");


    //界面启动
    public LoginJframe()
    {
        initloginframe();
        this.setSize(488,430);
        addcomponent();
        this.setVisible(true);
    }


    private void initloginframe() {
        //标题
        this.setTitle("欢迎登录！");
        this.setSize(603, 680);
//        总是置顶窗口
        this.setAlwaysOnTop(true);
        //位于屏幕中心
        this.setLocationRelativeTo(null);
        //点击关闭键时退出程序，还有别的参数比如点击关闭什么也不干、关闭走后一个窗口时退出程序
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中设置，才会按照xy来拜访组件
        this.setLayout(null);
        //将键盘监听添加到啊gameframe，同时传入苯类（接口实现类）的对象，  给出重写后方法的位置方便调用重写后的方法
//        this.addKeyListener(this);
    }

    private void  addcomponent()
    {
        //1. 添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("image/login/用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框
        JTextField username = new JTextField();
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("image/login/密码.png"));
        passwordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordText);

        //4.密码输入框
        JTextField password = new JTextField();
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);

        //验证码提示
        JLabel codeText = new JLabel(new ImageIcon("image/login/验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        //验证码的输入框
        JTextField code = new JTextField();
        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);

        String codeStr = CodeUtil.getCode();
        JLabel rightCode = new JLabel();
        //设置内容
        rightCode.setText(codeStr);
        //位置和宽高
        rightCode.setBounds(300, 256, 50, 30);
        //添加到界面
        this.getContentPane().add(rightCode);

        //5.添加登录按钮

        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("image/login/登录按钮.png"));
        //去除按钮的默认边框
        login.setBorderPainted(false);
        //去除按钮的默认背景
        login.setContentAreaFilled(false);
        this.getContentPane().add(login);

        //6.添加注册按钮
        JButton register = new JButton();
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image/login/注册按钮.png"));
        //去除按钮的默认边框
        register.setBorderPainted(false);
        //去除按钮的默认背景
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);

        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("image/login/background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }
    private void listenbutton()
    {
//如果登录

// 如果注册

    }
    private void loginjudge(){}//登录判断
    private void message(String context){}//弹框提醒

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

//待完成的功能
//1、实现登录功能，获取输入框中的用户名】密码】验证码】当用户名密码为空时弹出窗口提醒，当验证码错误时弹出窗口提醒，成功也弹出窗口提醒，同时切换到游戏界面
//2、按钮绑定点击监听，登录绑定actionlistener，点击时调用登录判断；同时应该实现点击时切换到另一张图片实现点击变色功能
//3、注册功能学习数据库、持久化之后再说吧




