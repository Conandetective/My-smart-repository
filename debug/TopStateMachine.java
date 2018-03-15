package ZZZKN;


//顶层模块，未完成
public class TopStateMachine {
    /*
       前端提交一份合同->甲方乙方登录
     */
    static test cb = new test();
    static int exit = 0;
    public static void main(String[] args){
        cb.connection();
        cb.check();

        // 检测异常,终止合同（测试版）
        cb.set("exit","0");
        int temp =0;
        while(true){
            if(temp==5){
                cb.set("exit","1");
            }
            else {
                temp ++;
            }
            String t=cb.get("exit");
            if(t.equals("1")){
                System.out.println(t);
                exit = 1;
                break;
            }

        }
        if(exit == 1){
            return;
        }
    }
}
