package ZZZKN;

import java.text.ParseException;

public class Punish {

   static String DeliString = null;
   static String PayString = null;

   /*
        发货
        是否发货，当前日期，约定日期
    */
    public int DelayedDelivery(boolean Ship,int nowyear,int nowmonth,int nowday,int promiseyear,int promisemonth,int promiseday) throws ParseException {
        String date1 = nowyear + "-" + nowmonth + "-" + nowday + " 00:00:00";
        String date2 = promiseyear + "-" + promisemonth + "-" + promiseday + " 00:00:00";
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date nowDate = df.parse(date1);
        java.util.Date endDate = df.parse(date2);
        int i=0;
        long betweenDay = (nowDate.getTime() - endDate.getTime()) / (1000 * 60 * 60 * 24);
        if(Ship==false){
           if (betweenDay<0) {
              DeliString =  "尚未到发货截止日期";
               System.out.println(DeliString);
           }
           else if(betweenDay==0){
               DeliString ="今日为发货截止日期，若仍未发货，违约行为将被记录";
               System.out.println(DeliString);
           }
           else {
               DeliString = "因出租方滞后发货，违约行为被记录，合同终止";
               System.out.println(DeliString);
               i = 1;
           }

        }

        return i;
    }

    /*
       滞后支付
       是否支付，当前日期，约定日期，滞后支付罚金，双方id
     */
    public int DelayedPayment(boolean Pay,int nowyear,int nowmonth,int nowday,int promiseyear,int promisemonth,int promiseday,double dpfee,String id1,String id2) throws ParseException {
        String date1 = nowyear + "-" + nowmonth + "-" + nowday + " 00:00:00";
        String date2 = promiseyear + "-" + promisemonth + "-" + promiseday + " 00:00:00";
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date nowDate = df.parse(date1);
        java.util.Date endDate = df.parse(date2);
        int i=0;
        long betweenDay = (nowDate.getTime() - endDate.getTime()) / (1000 * 60 * 60 * 24);
        if(Pay==false){
            if (betweenDay<0) {
                System.out.println("尚未到支付截止日期");
            }
            else if(betweenDay==0){
                System.out.println("今日为支付截止日期，若仍未支付，赔偿机制将自动处理");
            }
            else if((betweenDay == 1)||(betweenDay == 2)){
                System.out.println("租入方滞后支付，请租入方尽快查看账户余额，当日充值有效");
                i = 1;
            }
            else  {
                System.out.println("因租入方滞后支付，依照合约，"+date1+" "+id2+"向"+id1+"赔偿"+dpfee+"元，合约解除");
                i = 2;
            }
        }

        return i;
    }

    /*
        滞后归还
        是否归还，当前日期，约定日期
     */
    public int DelayedReturn(boolean return1,int nowyear,int nowmonth,int nowday,int promiseyear,int promisemonth,int promiseday) throws ParseException{
        String date1 = nowyear + "-" + nowmonth + "-" + nowday + " 00:00:00";
        String date2 = promiseyear + "-" + promisemonth + "-" + promiseday + " 00:00:00";
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date nowDate = df.parse(date1);
        java.util.Date endDate = df.parse(date2);
        int i=0;
        long betweenDay = (nowDate.getTime() - endDate.getTime()) / (1000 * 60 * 60 * 24);
        if(return1==false){
            if (betweenDay<0) {
                System.out.println("尚未到归还截止日期");
            }
            else if(betweenDay==0){
                System.out.println("今日为归还截止日期，若仍未归还，违约行为将被记录");
            }
            else  {
                System.out.println("因租入方滞后归还，违约行为被记录，合约终止");
                i = 2;
            }
        }

        return i;
    }

     /*
        损坏惩罚
        是否损坏
      */
    public int DamagePunish(boolean damage) throws ParseException{
        if(damage){
            System.out.println("因租入方使物品损坏，合约终止");
            return 1;
        }
        return 0;
    }

    /*
        实物不符赔偿
        是否不符
     */
    public int NotConfirmPunish(boolean notconfirm) throws ParseException{
        if(notconfirm){
            System.out.println("因出租物品与实物不符，合约终止");
            return 1;
        }
        return 0;
    }
}
