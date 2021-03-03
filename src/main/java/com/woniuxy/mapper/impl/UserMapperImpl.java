package com.woniuxy.mapper.impl;

import com.woniuxy.Utils.ConnectionUtils;
import com.woniuxy.mapper.UserMapper;
import com.woniuxy.model.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserMapperImpl implements UserMapper {
    //用于进行JDBC操作的对象，该对象将在容器中配置，由spring容器注入
    private QueryRunner query;
    public void setQuery(QueryRunner query) {
        this.query = query;
    }

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    @Override
    public User findByUser(String username) {
        try {
            //结果集为集合（多个），使用BeanListHandler封装
            List<User> accounts = query.query(connectionUtils.getThredLocalConnection(),
                    "select * from t_account where userName=?",
                    new BeanListHandler<User>(User.class), username);
            if (accounts.isEmpty()) {
                return null;
            }
            if (accounts.size() > 1) {
                throw new RuntimeException("结果集不唯一，数据有问题");
            }
            return accounts.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
