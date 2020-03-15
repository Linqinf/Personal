import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

@SuppressWarnings("all")
public class wc extends JFrame{
    private JPanel panel = new JPanel();
    //组件
    private JFileChooser file = new JFileChooser();
    //发送信息的文本域(下面部分)
    private JTextArea speakArea = new JTextArea();
    //滚动条 包含发送信息的文本域
    private JScrollPane speakPane = new JScrollPane(speakArea);
    //发送按钮
    private JButton sendButton = new JButton("打开");
    //清空按钮
    private JButton clearButton = new JButton("清空");
    //推出按钮
    private  JButton exitButton = new JButton("退出");
    public wc(){
        this.initOther();
        this.initElements();
        this.initListener();
        this.initSelf();
    }


    //方法
    //1.设置了一些乱七八糟的东西  布局  位置   字体
    private void initOther(){
        //将默认的布局管理清空
        panel.setLayout(null);
        //设置上面文本域不让修改
        //messArea.setEditable(false);
        //设置一下文本域的大小
        //messPane.setBounds(10,10,305,220);
        speakPane.setBounds(30,50,600,400);
        sendButton.setBounds(180,20,60,30);
        clearButton.setBounds(300,20,60,30);
        exitButton.setBounds(400,20,60,30);
    }

    //2.将各种组件组合在一起 互相添加
    private void initElements(){
        //先将组件添加在panel里
        //panel.add(messPane);
        panel.add(file);
        panel.add(speakPane);
        panel.add(sendButton);
        panel.add(clearButton);
        panel.add(exitButton);
        //将panel添加在frame里
        this.add(panel);
}

    //*3.给绑定事件  键盘 鼠标 响应
    private void initListener(){

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int option = fileChooser.showOpenDialog(null);
                Recursion r1 = new Recursion();
                if(option == JFileChooser.APPROVE_OPTION){
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    System.out.println(file.getAbsolutePath());
                    Recursion.FilesNumber = 0;
                    Recursion.msg = "";
                    String msg = "**********************************************" + "\n";
                    msg += r1.showFile(file, "*" , "*");
                    msg += "**********************************************" + "\n";
                    speakArea.setText(msg);
                }else{
                    System.out.println("文件未能打开");
                }
            }
        });

        //给清空按钮绑定事件
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                speakArea.setText("");
            }
        });

        //给退出按钮绑定事件
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }


    //4.设置这个窗体对象自己的一些属性   自己大小  自己关闭  展示
    private void initSelf(){
        //设置frame点击X时候程序结束
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置frame默认的大小和位置
        this.setBounds(500,200,650,500);
        this.setVisible(true);//设置窗口显示状态
    }
}
