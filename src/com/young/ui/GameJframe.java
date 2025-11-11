package com.young.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJframe extends JFrame implements KeyListener, ActionListener {//继承java界面类，实现键盘监听接口
    int[][] data = new int[4][4];//记录各张图片的路径
    int x, y;//记录空白方块的位置坐标
    String path = "image/girl/girl3/";//记录当前图片的路径，方便后面随机图片
    int[][] win = new int[][]{
            {0, 1, 2, 3},
            {4, 5, 6, 7,},
            {8, 9, 10, 11},
            {12, 13, 14, 15}};
    int step=0;

    //菜单组成
    JMenuItem f_restart = new JMenuItem("重新游戏");
    JMenuItem f_relogin = new JMenuItem("重新登陆");
    JMenuItem f_close = new JMenuItem("关闭游戏");

    JMenuItem weixin = new JMenuItem("微信号");

    //jframe表示窗口，游戏窗口很明显是他的子类所以继承jframe









    //实现各种与游戏窗口相关的逻辑
    public GameJframe() {
        initgameframe();
        initmenu();
        initdata();
        addimage();
//窗口可视选择放在最后面
        this.setVisible(true);
    }


    private void initgameframe() {
        //标题
        this.setTitle("拼图游戏！");
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
        this.addKeyListener(this);
    }

    private void initmenu() {
        //新建菜单栏
        JMenuBar jMenuBar = new JMenuBar();
//新建菜单*2
        JMenu function = new JMenu("功能");
        JMenu aboutme = new JMenu("关于我");

//        //菜单组成
//        JMenuItem f_restart = new JMenuItem("重新游戏");
//        JMenuItem f_relogin = new JMenuItem("重新登陆");
//        JMenuItem f_close = new JMenuItem("关闭游戏");
//
//        JMenuItem weixin = new JMenuItem("微信号");

        //绑定事件
        f_restart.addActionListener(this);
        f_relogin.addActionListener(this);
        f_close.addActionListener(this);
        weixin.addActionListener(this);
//        添加到菜单
        function.add(f_restart);
        function.add(f_relogin);
        function.add(f_close);

        aboutme.add(weixin);

        //添加到菜单栏
        jMenuBar.add(function);
        jMenuBar.add(aboutme);

        //添加到主界面
        this.setJMenuBar(jMenuBar);
    }

    //利用二维数组存储图片的索引，然后打乱里面的顺序做到随机加载
    private void initdata() {
        //新建一维数组，打乱顺序后存储到二维数组中
        //打乱
        int a[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};//图片的路径都是从1开始，数组从零开始保证有一个空位且一定是元素0对应的
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            int index = random.nextInt(a.length);
            int temp = a[i];
            a[i] = a[index];
            a[index] = temp;
        }
        //一维数组存储到二维中,data已经定义为成员变量
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (a[index] == 0)//这里就是记录了元素零/拼图空位置的坐标
                {
                    x = i;
                    y = j;
                }
                data[i][j] = a[index];
                index++;
            }
        }


    }


    //循环创建对象并添加
    private void addimage() {
        this.getContentPane().removeAll();//加载前先清空，不然图片不会加载，移动拼图也不会生效

        //判断胜利
        if(judgewin()){
        ImageIcon win = new ImageIcon("image/win.png");
        JLabel j_win = new JLabel(win);
        j_win.setBounds(203,283,197,73);
        this.getContentPane().add(j_win);}
        //添加步数
        JLabel j_step = new JLabel("步数:"+step);
        j_step.setBounds(50,30,100,20);//像素要设置好不能太小不然显示不出来
        this.getContentPane().add(j_step);
        //一共添加四行
        for (int i = 0; i < 4; i++) {
//            添加一行图片
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];//指定分割后的图片序号
                ImageIcon imageIcon = new ImageIcon(path + num + ".jpg");
                JLabel jLabel = new JLabel(imageIcon);
//                指定jlabel组件的位置和大小（0，0）表示隐藏容器的左上角位置
                //美化：添加偏移量
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                //为每张图片添加边框,bevelborder表示斜面边框  Raised为bevelboeder的自定义属性 意为升起 bevelborder是border的实现类
                jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
                //取消隐藏容器的布局居中设置，从而按照坐标来拜访组件
                this.getContentPane().setLayout(null);
                //将jlabel添加到隐藏容器中
                this.getContentPane().add(jLabel);
            }

        }
//添加背景图片同时为背景图片添加边框
        ImageIcon background = new ImageIcon("image/background.png");
        JLabel Jbackground = new JLabel(background);
        Jbackground.setBounds(40, 40, 508, 560);
        this.getContentPane().add(Jbackground);

        this.getContentPane().repaint();//重绘方法，刷新界面
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) //表示A键
        {//这里也是个小bug，不加的话胜利后还可以查看原图
            if (judgewin())
            return;

            this.getContentPane().removeAll();
            ImageIcon imageIcon = new ImageIcon(path + "all.jpg");
            JLabel j_all = new JLabel(imageIcon);
            j_all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(j_all);
//添加背景图片同时为背景图片添加边框
            ImageIcon background = new ImageIcon("image/background.png");
            JLabel Jbackground = new JLabel(background);
            Jbackground.setBounds(40, 40, 508, 560);
            this.getContentPane().add(Jbackground);
            this.getContentPane().repaint();

        } else if (code == 87) {//表示w键，作弊
//            重置data中的值，然后重新加载
            data = new int[][]{
                    {0, 1, 2, 3},
                    {4, 5, 6, 7,},
                    {8, 9, 10, 11},
                    {12, 13, 14, 15}
            };
            addimage();//addimgae中已经加入了判断胜利的渲染，所以这里直接调用即可

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(judgewin())//胜利之后就不能移动了直接返回
        {
            return;
        }
        int code = e.getKeyCode();

        if (code == 37)//←对应的数值  当code==37是表示输入的←  同时表示将空位  右方  的图片移动上来
        {
            if (y == 3) {
                return;//当空白块在最右边时，没法向左移动其他方块
            }
            data[x][y] = data[x][y + 1];//空白位置 存储移动的图片路径
            x = x;
            y = y + 1;//更新空白位置的坐标
            data[x][y] = 0;//此步是必须的   新空白位置的值必须重置为零，不然会出现复制情况
            step++;
            addimage();//重新加载
        } else if (code == 38)//↑
        {
            if (x == 3) {//空白快在最下面，其他方块没法向上
                return;
            }
            data[x][y] = data[x + 1][y];
            x = x + 1;
            y = y;
            data[x][y] = 0;
            step++;
            addimage();
        } else if (code == 39)//→
        {
            if (y == 0) {
                return;
            }
            data[x][y] = data[x][y - 1];
            x = x;
            y = y - 1;
            data[x][y] = 0;
            step++;
            addimage();
        } else if (code == 40)//↓
        {
            if (x == 0) {
                return;
            }
            data[x][y] = data[x - 1][y];
            x = x - 1;
            y = y;
            data[x][y] = 0;
            step++;
            addimage();
        } else if (code == 65) {//松开A显示拼图
            addimage();
        }
        }


//判断胜利
    public boolean judgewin() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j]!=win[i][j])
                {
                    return false;//一个不同就退出
                }
            }
        }
        return true;
    }
//actionlistener
    @Override
    public void actionPerformed(ActionEvent e) {
        //读取e的源头识别动作
        Object source= e.getSource();
        if (source==f_restart)//点击重新开始
        {
            //清空步数，必须先清空step再渲染
            step=0;
            //打乱图片（data）
            initdata();
            //重新加载
            addimage();
        }
        if (source==f_relogin)
        {//关闭当前界面
            System.out.println("重新登陆");
            this.setVisible(false);
            //新建loginframe
            new LoginJframe();

        }
        if (source==f_close)
        {//直接关闭虚拟机
            System.exit(0);

        }
        if (source==weixin)
        {
            //新建弹出式窗口,级别和frame一致，内部有隐藏容器
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("image/aboutme.png"));
            jLabel.setBounds(0,0,210,210);
//注释，不取消居中            jDialog.getContentPane().setLayout(null);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(300,300);
            //屏幕正中央
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭无法操作下面的界面
            jDialog.setModal(true);
            jDialog.setVisible(true);


        }

    }
}
