package com.woniuxy.Utils;

import javax.sql.DataSource;
import java.sql.Connection;

public class ConnectionUtils {
    private static ThreadLocal<Connection>tl=new ThreadLocal<Connection>();
    //数据源
    private DataSource dataSource;
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public Connection getThredLocalConnection(){
        try {
            //业务层需要数据库连接时，先从线程中取
            Connection conn = tl.get();
            //如果取到的连接为null，从连接池中获取一个。
            if (conn == null) {
                conn = dataSource.getConnection();
                //将获取的连接绑定到线程上
                tl.set(conn);
            }
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void unbindThreadLocalConnection(){
        //JDBC操作完成需要释放资源时，将线程上的连接移除
        tl.remove();
    }
}
