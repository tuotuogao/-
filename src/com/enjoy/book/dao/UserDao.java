package com.enjoy.book.dao;

import com.enjoy.book.bean.User;
import com.enjoy.book.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

//用户表的数据操作对象
public class UserDao {

    QueryRunner runner = new QueryRunner();

    public User getUser(String name, String pwd) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "select * from user where name = ? AND pwd = ?";
        User query = runner.query(connection, sql, new BeanHandler<>(User.class), name, pwd);
        DBHelper.close(connection);
        return query;
    }

    public int modifyPwd(long id, String pwd) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "update user set pwd = ? where id = ?";
        int update = runner.update(connection, sql, pwd, id);
        DBHelper.close(connection);
        return update;
    }
}
