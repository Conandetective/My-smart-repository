package ZZZKN;

import java.util.*;
import java.text.*;

public class CurrencyOperations { //货币操作类

    static String RefundString = null;

    /*
        押金退回信息提示之租用方

      - 当前日期、结束日期、押金数目、合同编号
     */
    public void Depositrefund(int nowyear,int nowmonth,int nowday,int endyear,int endmonth,int endday,double depositcost,String ContractNumber) throws ParseException { //押金退还类
        String date1 = nowyear + "-" + nowmonth + "-" + nowday + " 00:00:00";
        String date2 = endyear + "-" + endmonth + "-" + endday + " 00:00:00";
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date nowDate = df.parse(date1);
        java.util.Date endDate = df.parse(date2);
        long betweenDay = (nowDate.getTime() - endDate.getTime()) / (1000 * 60 * 60 * 24);
        if(betweenDay <= 0){
            RefundString = "合约编号为"+ContractNumber+"，押金"+depositcost+"将于合约结束后的第二天退回到您的账户，合约结束时间为"+date2;
            System.out.println(RefundString);
        }
        else if(betweenDay == 1) {
            RefundString = "合约编号为"+ContractNumber+"，押金"+depositcost+"今日退回到您的账户，请注意查收";
            System.out.println(RefundString);
        }
        else if(betweenDay > 1) {
            RefundString = "合约编号为"+ContractNumber+"，押金"+depositcost+"已于"+date2 +"的第二天退回到您的账户，请注意查收";
            System.out.println(RefundString);
        }
    }

    /*
       押金退回信息提示之出租方

      - 当前日期、结束日期、押金数目、合同编号、租用方id
     */
    public void Depositrefund(int nowyear,int nowmonth,int nowday,int endyear,int endmonth,int endday,double depositcost,String ContractNumber,String id1) throws ParseException { //押金退还类
        String date1 = nowyear + "-" + nowmonth + "-" + nowday + " 00:00:00";
        String date2 = endyear + "-" + endmonth + "-" + endday + " 00:00:00";
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date nowDate = df.parse(date1);
        java.util.Date endDate = df.parse(date2);
        long betweenDay = (nowDate.getTime() - endDate.getTime()) / (1000 * 60 * 60 * 24);
        if(betweenDay <= 0){
            RefundString = "合约编号为"+ContractNumber+"，押金"+depositcost+"将于合约结束后的第二天退回到对方账户"+id1+"，合约结束时间为"+date2;
            System.out.println(RefundString);
        }
        else if(betweenDay == 1) {
            RefundString = "合约编号为"+ContractNumber+"，押金"+depositcost+"今日退回到对方账户"+id1;
            System.out.println(RefundString);
        }
        else if(betweenDay > 1) {
            RefundString = "合约编号为"+ContractNumber+"，押金"+depositcost+"已于"+date2 +"的第二天退回到对方账户"+id1;
            System.out.println(RefundString);
        }
    }
    public static void main(String[] args) throws ParseException {
        CurrencyOperations CO = new CurrencyOperations();
        CO.Depositrefund(2017,1,20,2017,1,19,100.897,"521","zkn980516");
    }


}
