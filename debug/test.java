package ZZZKN;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class test {

    public void connection (){
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
    }

    public void check(){
        Jedis jedis = new Jedis("localhost");
        System.out.println("服务正在运行: "+jedis.ping());
    }

    public void set(String key,String value) {
        Jedis jedis = new Jedis("localhost");
        jedis.set(key, value);
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get(key));
    }

    public void setoflist(String Key,String List){
        Jedis jedis = new Jedis("localhost");
        jedis.lpush(Key, List);
    }

    public String get(String Key){
        Jedis jedis = new Jedis("localhost");
        Set<String> keys = jedis.keys(Key);
        Iterator<String> it=keys.iterator() ;
        String value = jedis.get(Key);
        //while(it.hasNext()){
             //key = it.next();
            //System.out.println(key);
//}
        return value;
    }

    public String[] getoflist(String Key,int startnum,int endnum){
        Jedis jedis = new Jedis("localhost");
        List<String> list = jedis.lrange(Key, startnum ,endnum);
        String [] value = new String[8];
        for(int i=0; i<list.size(); i++) {
          //  System.out.println("列表项为: "+list.get(i));
            value[i] = list.get(i);
        }
        return value;
    }

    public static void main(String[] args) {
        test cb = new test();
        cb.connection();
        cb.check();
        cb.set("abc","zkn");
        String t = cb.get("abc");
        System.out.println(t);
       cb.setoflist("xj","0");
       cb.setoflist("xj","1");
        cb.setoflist("xj","2");
      String temp[] = cb.getoflist("xj",0,2);
        for(int i = 0; i < 3; i ++) {
            System.out.println(temp[i]);
        }
    }
}
