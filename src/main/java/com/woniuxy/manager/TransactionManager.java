package com.woniuxy.manager;

import com.woniuxy.Utils.ConnectionUtils;

import java.sql.SQLException;

public class TransactionManager {
    private ConnectionUtils connectionUtils;
    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }
    //开启事务
    public void beginTransaction(){
        try {
            connectionUtils.getThredLocalConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //提交事务
    public void commit(){
        try {
            connectionUtils.getThredLocalConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //回滚事务
    public void rollback(){
        try {
            connectionUtils.getThredLocalConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //释放资源
    public void close(){
        try {
            //将连接还回连接池
            connectionUtils.getThredLocalConnection().close();
            //将线程上的连接移除
            connectionUtils.unbindThreadLocalConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
