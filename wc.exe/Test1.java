import java.io.*;
import java.util.Scanner;


public class Test1 {
    public  static  void main(String[] args) {
        System.out.println("-c file.c    返回文件 file.c 的字符数\n" +
                "-w file.c    返回文件 file.c 的词的数目  \n" +
                "-l file.c    返回文件 file.c 的行数  \n" +
                "-l file.c    返回更复杂的数据(代码行 / 空行 / 注释行) \n" +
                "-s -a *.c    返回当前目录及子目录中所有*.c 文件的代码行数、空行数、注释行数 \n"+
                "-x           显示图形界面，用户可以通过界面选取单个文件或文件夹 \n");
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("please input '[order] [filename]':");
            String order[] = input.nextLine().split(" ");
            Recursion r1 = new Recursion();
            Recursion.FilesNumber = 0;
            if(order.length == 1){
                        if(order[0].equals("-x")){
                            new wc();       //GUI
                        }
                        else {
                            System.out.println("输入有误，请重新输入");
                        }
            }
            else if (order.length==2) {
                        File file = new File(order[1]);
                        if(!file.exists()){
                            System.out.println("文件不存在，请重新输入");
                        }
                        else if(file.isDirectory()){ //输入的为目录时候
                                r1.showFile(file, order[0] , "*");
                        }
                        else {
                                r1.showFile(file);
                                if (order[0].equals("-c")) {
                                    System.out.println("文本的字符数为" + Recursion.character);
                                } else if (order[0].equals("-w")) {
                                    System.out.println("文本的字符数为" + Recursion.words);
                                } else if (order[0].equals("-l")) {
                                    System.out.println("文本的行数为" + Recursion.line);
                                } else if (order[0].equals("-a")) {
                                    System.out.println("文本的空白行为" + Recursion.blank);
                                    System.out.println("文本的注释行为" + Recursion.comment);
                                    System.out.println("文本的代码行为" + Recursion.codeline);
                                }
                                 else {
                                    System.out.println("输入有误，请重新输入");
                                }
                        }
            }else if(order.length==3 && "-s".equals(order[0])){
                   File file = null;
                   if(order[2].split("\\.")[0].equals("*")){
                         file = new File(System.getProperty("user.dir"));
                   }
                   else  {
                         file = new File(order[2].split("\\.")[0]);
                   }

                    if(!file.exists()){
                          System.out.println("文件不存在，请重新输入");
                    }
                    else {
                        System.out.println(file.getAbsolutePath());
                        r1.showFile(file, order[1], order[2].split("\\.")[1]);
                    }
            }else System.out.println("输入有误，请重新输入");
        }
    }
}
