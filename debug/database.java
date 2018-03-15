package ZZZKN;


//简易数据库，字段可以后期添加，前端查询操作可以直接访问数据库查询，不用联系后台，后台实时更新数据库信息

import java.sql.*;

import static java.lang.Class.*;

public class database {
    static Connection c = null;
    static Statement stmt = null;
    // static boolean Regist; //注册确认信号
    // static boolean Login; //登录确认信号
    static int Num;  //注册
    static String Id;
    static String Password;
    static int Creditvalue;
    static String Account;
    static double Money;

   /* public static void Createtable(boolean regist,int num,String id,String password,int creditvalue,String account,double monney) {
        Regist = regist ;
        Num = num;
        Id = id;
        Password = password;
        Creditvalue = creditvalue;
        Account = account;
        Monney = monney;
    }*/


    /*
         连接数据库
     */
    public static void DatabaseConnectivity() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:test.db");
        System.out.println("Opened database successfully");

    }


    /*
        创建信息表格
     */
    public static void CreateSheet() {
        try {
            forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE COMPANY " +
                    "(NUM  INT PRIMARY KEY   NOT NULL," +
                    " ID           TEXT    NOT NULL, " +
                    "PASSWORD     TEXT     NOT NULL," +
                    "CREDITVALUE  INT    NOT NULL," +
                    "ACCOUNT      TEXT    NOT NULL," +
                    "MONEY       REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    /*
            插入（新增用户）
        - 用户编号、用户名、密码、信用值（初始量化为0，完成一单交易增加一个值）、支付账户、账户余额
     */
    public static void Insert(int num, String id, String password, int creditvalue, String account, double money) {
        try {
            forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO COMPANY (NUM,ID,PASSWORD,CREDITVALUE,ACCOUNT,MONEY) " +
                    "VALUES (" + num + ",'" + id + "','" + password + "'," + creditvalue + ",'" + account + "'," + money + ");";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    /*
        登录函数
        - 输入用户名、密码，返回是否登录成功的int值
     */
    public static int Login(String id, String password) {
        int t = 0;
        int flag = 0;
        try {
            forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");
            while (rs.next()) {
                String t1 = rs.getString(2);
                if (id.equals(t1)) {
                    flag = 1;
                    String t2 = rs.getString(3);
                    if (password.equals(t2)) {
                        System.out.println("登陆成功！");
                        t = 1;
                        break;
                    } else {
                        System.out.println("登陆失败，密码错误！");
                        t = 0;
                        break;
                    }
                } else {
                    t = 0;
                }
            }
            if (flag != 1) {
                System.out.println("登录失败，查无此用户！");
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return t;
    }

    /*
    是否授权，也即合同部署
     */
    public static void authorized(String id, boolean authorize) {
        if (authorize) {
            System.out.println(id + "授权成功！信息将被读取");
        }
    }

    /*
        信息获取
      -返回全套信息
     */
    public static void getinfo(String id) {
        int flag = 0;
        try {
            forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");
            while (rs.next()) {
                String t1 = rs.getString(2);
                if (id.equals(t1)) {
                    flag = 1;
                    System.out.println("查询成功！");
                    Num = rs.getInt(1);
                    Id = rs.getString(2);
                    Password = rs.getString(3);
                    Creditvalue = rs.getInt(4);
                    Account = rs.getString(5);
                    Money = rs.getDouble(6);
                    break;
                }
            }
            if (flag == 0) {
                System.out.println("查询失败，查无此用户！");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    /*
        信用值和货币值浮动函数
      - 调整（0 修改账户金额、1 修改信用值）、用户编号、调整方向（0 增加、 1 减少）、原数目、浮动数目
     */
    public static void update(int adjust, int num, int op, double old, double amount) {
        String sql = null;
        try {
            forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            switch (adjust) {
                case 0: //修改账户金额
                    if (op == 0) { //充值
                        double temp = old + amount;
                        sql = "UPDATE COMPANY set MONEY= " + temp + " where NUM=" + num;
                        System.out.println("账户充值" + amount + "元,当前金额为" + temp + "元");
                    } else if (op == 1) { //转出
                        double temp = old - amount;
                        sql = "UPDATE COMPANY set MONEY= " + temp + " where NUM=" + num;
                        System.out.println("账户转出" + amount + "元,当前金额为" + temp + "元");
                    }
                    break;
                case 1://信用值变动
                    if (op == 0) { //信用值增加
                        double temp = old + amount;
                        sql = "UPDATE COMPANY set MONEY= " + temp + " where NUM=" + num;
                        System.out.println("信用值增加" + amount + "，当前信用值为" + temp);
                    } else if (op == 1) { //信用值减少
                        double temp = old - amount;
                        sql = "UPDATE COMPANY set MONEY= " + temp + " where NUM=" + num;
                        System.out.println("信用值减少" + amount + "，当前信用值为" + temp);
                    }
                    break;
            }
            stmt.executeUpdate(sql);
            c.commit();

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("货币量及信用值数据更新成功！Operation done successfully");
    }

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        database cb = new database();
        cb.DatabaseConnectivity();
        cb.CreateSheet();
        cb.Insert(1, "zkn", "zkn980516", 100, "16061002", 666.66);
        cb.Login("zkn", "zkn980516");
        cb.authorized("zkn", true);
       // cb.getinfo("zkn");
        cb.update(0, 1, 0, 666.66, 100);
    }
}

