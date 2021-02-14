## 我的烂代码JDBC

> 情况是这样的，在项目中使用JDBC去链接HIVE数据库，因为我对数据库连接池一直都使用第三方的（自打上班后就没咋研究过），加上这个项目主要跑一些定时任务，所以写了个JdbcMysqlUtils去提供一个公用的🔗。

```java
   工具类： 
   private static Connection conn = null;

    private JdbcMysqlUtils() {

    }

    public static Connection getConn() throws Exception {

        if (conn != null) {
            return conn;
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://1.2.3.4:3306/dbname?useUnicode=true&characterEncoding=utf8",
                "root", "pwd");
        return conn;
    }
```

```java
调用：
    public static void main(String[] args) throws Exception {
        test1();
        test1();
    }

    public static void test1() {
        try(Connection conn = JdbcMysqlUtils.getConn();
            PreparedStatement ps = conn.prepareStatement("select * from user");) {
            ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```

*bug* : com.mysql.cj.exceptions.ConnectionIsClosedException: No operations allowed after connection closed.

**debug** : 

1. 关闭资源使用了try catch resources ，会自动调用close() 
2. 第二次调用conn.prepareStatement("select * from user"); 报错

**分析** ：

1. 问题出在，第二次获取的conn, 数据库连接资源已经被关闭了。
2. 之前我对于close() 的理解肤浅了， 稍微扒了扒源码，发现并没有将conn对象置null,  应该是通过状态的设置进行了资源关闭，但内存对象还会存在。
3. 工具类使用 conn ！= null 进行判断就不对了，在com.mysql.cj.NativeSession，private boolean isClosed = true;进行判断资源是否关闭。

```java
public static Connection getConn() throws Exception {
  // 多加一层判断
        if (conn != null  && !conn.isClosed()) {
            return conn;
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://120.132.101.152:3306/scenter?useUnicode=true&characterEncoding=utf8",
                "root", "mysql+edata+1");
        return conn;
    }
```



