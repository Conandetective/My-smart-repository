package ZZZKN;

/*
    内部计时函数，5s一天自动计时，输入为合同开始年月日、是否开始计时
 */
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;

public class TimeTest {

    public static int i = 0;
    public static void timer(final int year,final int month,final int day,boolean judgement) {
        Timer timer2 = new Timer();
        if (judgement) {
            timer2.schedule(new TimerTask() {
                public void run() {
                    i = i + 1;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    String str = String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(day);
                    Date date = sdf.parse(str, new ParsePosition(0));
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(date);
                    calendar2.add(Calendar.DATE, i);
                    Date date1 = calendar2.getTime();
                    String out = sdf.format(date1);
                    System.out.println(out);
                }
            }, 0, 5000);
        }
    }

    public static void main(String[] args){
        TimeTest temp = new TimeTest();
        temp.timer(2017,05,30,true);
    }
}
