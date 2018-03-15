package ZZZKN;
import java.util.*;
import java.text.*;


public class OrdinaryConsumer { //普通花费类

    /**
     * @param args
     * */
   static String ExtraString = null;
   static String  DepositeString = null;
   /*static String beginDate = null;
   static String nowDate = null;
   static String endDate = null;
   static int betweenDay = 0;
   static int sumDay = 0;*/
   static double sumrent = 0;
   static String RentString = null;
   static double newrentfee = 0;
   static boolean renewsuccess = false;


    public void Addfees (Boolean Extra,String S,double Extracost,String t,String id1,String id2){ //附加费用消费
        if(Extra == true) {
            ExtraString = t+","+id1+ "因"+S+"转账给"+id2+Extracost+"元"; //构建附加费用信息字符串
            System.out.println(ExtraString);
        }
    }

    /*押金支付类提示信息，变量分别为：
    - 押金支付方式（0 无押金，1 有押金且为百分比型，2 有押金且为value型（proportation有某个初值，若为1则用百分比覆盖它））、数目、百分比、时间（年月日）、双方id
     */
    public void Depositfee (int depositway,double value,double proportion,String t,String id1,String id2) { //押金
        double depositcost;
        switch(depositway) {
            case 0:
                DepositeString = "无押金";
                break;
            case 1:
                depositcost = value * proportion * 0.01;
                DepositeString = t+","+id1+ "因支付押金转账给"+id2+depositcost+"元";
                break;
            case 2:
                depositcost = value;
                DepositeString = t+","+id1+ "因支付押金转账给"+id2+depositcost+"元";
                break;
        }
        System.out.println(DepositeString);
    }

    /*租金提示类信息
    - 租金付费方式（0 一次性付清rentfee，1  日付rentfee元，2 月付（需要顶层模块按月更新参数信息）rentfee元）、合同开始日期、现在的日期、合同结束日期（对于月付需要按月传入新的“截止日期”）、租金费用、租赁方id1,出租方id2
     */
    public void Rentcalculation(int rentway,int beginyear,int beginmonth,int beginday,int nowyear,int nowmonth,int nowday,int endyear,int endmonth,int endday,double rentfee,String id1,String id2) throws ParseException { //租金
        boolean continue_ =(nowyear <= endyear)?true:(nowmonth <= endmonth)?true:(nowday <= endday)?true:false;
        String date1 = beginyear + "-" + beginmonth + "-" + beginday + " 00:00:00";
        String date2 = nowyear + "-" + nowmonth + "-" + nowday + " 24:00:00";
        String date3 = endyear + "-" + endmonth + "-" + endday + " 24:00:00";
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date beginDate = df.parse(date1);
        java.util.Date nowDate = df.parse(date2);
        java.util.Date endDate = df.parse(date3);
        long betweenDay = (nowDate.getTime() - beginDate.getTime()) / (1000 * 60 * 60 * 24);
        long sumDay =  (endDate.getTime() - beginDate.getTime()) / (1000 * 60 * 60 * 24);
        System.out.println("开始日期：" + beginDate);
        System.out.println("当前日期：" + nowDate);
        System.out.println("截止日期：" + endDate);
        System.out.println("间隔天数：" + betweenDay);
        if(continue_) {
            switch(rentway) {
                case 0:
                    if((nowyear == beginyear)&&(nowmonth == beginmonth)&&(nowday == beginday)){
                        RentString = date2 + ","+id1+"因一次性付清租赁费向"+id2+"转账"+ rentfee+"元";
                        System.out.println(RentString);
                    }
                    else {
                        RentString = id1 + "已于"+date1+"向"+id2+"一次性付清租赁费" +rentfee+"元，此次无需缴费";
                        System.out.println(RentString);
                    }
                    break;
                case 1:
                    RentString = date2 + ","+id1+"因日付租赁费向"+id2+"转账"+ rentfee+"元";
                    System.out.println(RentString);
                    break;
                case 2:
                    double rentfeeutiltoday = 0;
                    rentfeeutiltoday = rentfee * betweenDay;
                    sumrent = rentfee * sumDay;
                    RentString = id1+date3 + "需向"+id2+"支付"+sumrent+"元，共"+sumDay+"天，截止到今日已租用"+betweenDay+"天，需付"+rentfeeutiltoday+"元";
                    System.out.println(RentString);
                    break;
            }

        }
    }

    /*续租
    - 合同是否允许续租的bool值、续租开始日期、续租结束日期、旧租金、续租百分比
     */
    public static void Renewcalculation(boolean canrenew,int newbeginyear,int newbeginmonth,int newbeginday,int newendyear,int newendmonth,int newendday,double oldrentfee,double percentage) throws ParseException { //续费计算
        if(canrenew) {
            String newdate1 = newbeginyear + "-" + newbeginmonth + "-" + newbeginday + " 00:00:00";
            String newdate3 = newendyear + "-" + newendmonth + "-" + newendday + " 24:00:00";
            newrentfee = oldrentfee * percentage * 0.01;
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date begindate = df.parse(newdate1);
            java.util.Date enddate = df.parse(newdate3);
            long betweenday = (enddate.getTime() - begindate.getTime()) / (1000 * 60 * 60 * 24);
            RentString = "续费成功，续费时间段为"+ newdate1 +"至"+newdate3+"共"+betweenday+"天，租金由"+oldrentfee+"变为"+newrentfee+"，为原租金的百分之"+percentage;
            renewsuccess = true;
            System.out.println(RentString);
        }
        else {
            RentString = "续费失败，本租赁合同不支持续费";
            renewsuccess = false;
            System.out.println(RentString);
        }

    }

    public static void main(String[] args) throws ParseException {
        Boolean Extra = true;
        String S = "对方要求买衣服";
        double Extracost = 1000;
        String t ="2018-2-11";
        String id1 ="徐家兴";
        String id2 ="张凯宁";
        OrdinaryConsumer temp = new OrdinaryConsumer();
        temp.Addfees(Extra,S,Extracost,t,id1,id2);
        temp.Depositfee(1,1000,5,"2018-2-12","黄霁昀","肖军");
        temp.Rentcalculation(2,2017,1,10,2017,1,10,2017,1,10,500.38,"张凯宁","肖军");
        temp.Renewcalculation(true,2019,1,21,2019,10,10,500.38,70);
    }

}

