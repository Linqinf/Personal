import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Recursion {
    static int line = 0;
    static int words = 0;
    static int character = 0;
    static int blank = 0;
    static int comment = 0;
    static int codeline = 0;
    static int  FilesNumber;
    static  String msg = "";

    public  String showFile(File file){
        calculation(file);
        return  msg;
    }

    public String showFile(File file ,String order , String format){//D://test文件夹
        File[] files = file.listFiles();//test文件夹所有子元素
        if(files!=null && files.length!=0){
            for(File f:files){//遍历每个子文件
                this.showFile(f,order,format);//递归子元素
            }
        }
        if(file.getAbsolutePath().split("\\.").length == 2){
            if(file.isDirectory() == false && (file.getAbsolutePath().split("\\.")[1].equals(format)) || format.equals("*")){
                    //判断文件类型和文件格式
                System.out.println("w lai la ");
                    FilesNumber++;
                    msg += "第" +  FilesNumber + "个文件名为" ;
                    System.out.println("第" +  FilesNumber + "个文件为" + file.getAbsolutePath());
                    calculation(file);//进行统计计算
                    if(order.equals("-c")){
                        System.out.println("文本的字符数为"+Recursion.character);
                    }
                    else if(order.equals("-w")){
                        System.out.println("文本的字符数为"+Recursion.words);
                    }
                    else if(order.equals("-l")){
                        System.out.println("文本的行数为"+Recursion.line);
                    }
                    else if(order.equals("-a")){
                        System.out.println("文本的空白行为"+Recursion.blank);
                        System.out.println("文本的注释行为"+Recursion.comment);
                        System.out.println("文本的代码行为"+Recursion.codeline);
                    }
                    else  return  msg;
                }
        }
        return msg;
    }

    public String calculation(File file){
         line = 0;
         words = 0;
         character = 0;
         blank = 0;
         comment = 0;
         codeline = 0;
         msg +=   file.getAbsolutePath() + "\n";
            BufferedReader bf = null;
            FileReader fr = null;
            try {
                fr = new FileReader(file);//读文件
                bf = new BufferedReader(fr);
                String value = bf.readLine();

                while(value != null){
                    value = value.trim();//去除前后的空格
                    if(value.equals("")||value.matches("\\{|\\}")) blank++; //累加空白行数
                    else if (value.matches("(//|\\{//|\\}//).*")) comment++;    //累加注释行数
                    else codeline++; //代码行数
                    //msg += value + "\n";
                    String r = value.replaceAll("\\.","");
                    r = r.replaceAll("[^\\w]", " ");//特殊符号都去除，变为空格,方便统计单词数
                    r = r.replaceAll("\\s+"," ").replaceAll("\""," ");
                    r = r.trim();//去除头尾的空格
                    String s[] = r.split(" ");//按空格拆分得到单词
                    if(!s[0].equals("")) {//空行不计算单词数
                        words += s.length; //累加单词数
                    }
                    character += value.length();//累加字符数量
                    line++;//累加行数
                    value = bf.readLine();//读取下一行，重新进行循环
                }
                msg =    msg + "文本行数为 " + line + "\n" + "文本的单词数量为 " + words + "\n" + "文本的字符数为 "
                        + character + "\n" + "文本的空白行为 " + blank + "\n" + "文本的注释行为 " + comment  + "\n" +
                        "文本的代码行为 " + codeline + "\n" + "\n";
            } catch (IOException e) { //编译时异常
                e.printStackTrace();
            }finally {
                try {
                    fr.close();
                    bf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        return  msg;
    }
}
