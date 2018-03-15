package ZZZKN;
//后台可能不会用到此函数，需要从数据库中以返回值的形式赋值各个变量。前端可参考这个函数从txt文件读入信息

import javax.xml.transform.TransformerException;
import java.io.*;

public class Datainput {

    static String id1 = null;
    static String id2 = null;
    static String postcode = null;
    static String tele_number = null;
    static String address = null;
    static String ID_number = null;
    static String bank_card = null;
    static String object_characteristic = null;
    static int object_num = 0;
    static int begin_time_year = 0;
    static int begin_time_month = 0;
    static int begin_time_day = 0;
    static int delivery_time_year = 0;
    static int delivery_time_month = 0;
    static int delivery_time_day = 0;
    static boolean express = false;
    static double express_free = 0;
    static String express_company = null;
    static boolean receipt = false;
    static double receipt_free = 0;

    public static void readTxT(String filepath) throws IOException{
        FileReader read = new FileReader(filepath);
        BufferedReader br = new BufferedReader(read);
        String line;
        String str;
        int flg = 1;
        while((line = br.readLine())!=null){
            if(flg==1){
                id1 = line.split(":")[1];
            }
            if(flg==2){
                id2 = line.split(":")[1];
            }
            if(flg==3){
                postcode = line.split(":")[1];
            }
            if(flg==4){
                tele_number = line.split(":")[1];
            }
            if(flg==5){
                address = line.split(":")[1];
            }
            if(flg==6){
                ID_number = line.split(":")[1];
            }
            if(flg==7){
                bank_card = line.split(":")[1];
            }
            if(flg==8){
                object_characteristic = line.split(":")[1];
            }
            if(flg==9){
                str = line.split(":")[1];
                object_num = Integer.parseInt(str);
            }
            if(flg==10){
                str = line.split(":")[1];
                begin_time_year = Integer.parseInt(str);
            }
            if(flg==11){
                str = line.split(":")[1];
                begin_time_month = Integer.parseInt(str);
            }
            if(flg==12){
                str = line.split(":")[1];
                begin_time_day = Integer.parseInt(str);
            }
            if(flg==13){
                str = line.split(":")[1];
                delivery_time_year = Integer.parseInt(str);
            }
            if(flg==14){
                str = line.split(":")[1];
                delivery_time_month = Integer.parseInt(str);
            }
            if(flg==15){
                str = line.split(":")[1];
                delivery_time_day = Integer.parseInt(str);
            }
            if(flg==16){
                str = line.split(":")[1];
                express = Boolean.getBoolean(str);
            }
            if(flg==17){
                str = line.split(":")[1];
                express_free = Double.parseDouble(str);
            }
            if(flg==18){
                express_company = line.split(":")[1];
            }
            if(flg==19){
                str = line.split(":")[1];
                receipt = Boolean.getBoolean(str);
            }
            if(flg==20){
                str = line.split(":")[1];
                receipt_free = Double.parseDouble(str);
            }
            flg++;
        }
        System.out.println(id1);
        System.out.println(id2);
        System.out.println(postcode);
        System.out.println(tele_number);
        System.out.println(address);
        System.out.println(ID_number);
    }

    public static void confirmTxT(String[] str,boolean[] bool){
        String str2;
        for(int i = 0;i<bool.length;i++){
            if(bool[i]){
                if(i==0) id1 = str[0];
                if(i==1) id2 = str[1];
                if(i==2) postcode = str[2];
                if(i==3) tele_number = str[3];
                if(i==4) address = str[4];
                if(i==5) ID_number = str[5];
                if(i==6) bank_card = str[6];
                if(i==7) object_characteristic = str[7];
                if(i==8){
                    str2 = str[8];
                    object_num = Integer.parseInt(str2);
                }
                if(i==9){
                    str2 = str[9];
                    begin_time_year = Integer.parseInt(str2);
                }
                if(i==10){
                    str2 = str[10];
                    begin_time_month = Integer.parseInt(str2);
                }
                if(i==11){
                    str2 = str[11];
                    begin_time_day = Integer.parseInt(str2);
                }
                if(i==12){
                    str2 = str[12];
                    delivery_time_year = Integer.parseInt(str2);
                }
                if(i==13){
                    str2 = str[13];
                    delivery_time_month = Integer.parseInt(str2);
                }
                if(i==14){
                    str2 = str[14];
                    delivery_time_day = Integer.parseInt(str2);
                }
                if(i==15){
                    str2 = str[15];
                    express = Boolean.getBoolean(str2);
                }
                if(i==16){
                    str2 = str[16];
                    express_free = Double.parseDouble(str2);
                }
                if(i==17) express_company = str[17];
                if(i==18){
                    str2 = str[18];
                    receipt = Boolean.getBoolean(str2);
                }
                if(i==19){
                    str2 = str[19];
                    receipt_free = Double.parseDouble(str2);
                }
            }
        }
        System.out.println("修改后的数据:");
        System.out.println(id1);
        System.out.println(id2);
    }
    public static void main(String[] args){
        String str[] = {"肖军","徐家兴"};
        boolean bool[] = {false,true};

        Datainput temp = new Datainput();
        try{
            temp.readTxT("/Users/junxiao/Desktop/提取字段.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        temp.confirmTxT(str,bool);
    }
}
