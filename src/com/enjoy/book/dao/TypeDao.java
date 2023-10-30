package com.enjoy.book.dao;

import com.enjoy.book.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import com.enjoy.book.bean.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
public class TypeDao {

    QueryRunner runner = new QueryRunner();

    public int add(String name, long parentId) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "insert into `type` values(null, ?, ?)";
        int update = runner.update(connection, sql, name, parentId);
        DBHelper.close(connection);
        return update;
    }

    public List<Type> getAll() throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "select id, name, parentId from `type` ";
        List<Type> types = runner.query(connection, sql, new BeanListHandler<>(Type.class));
        DBHelper.close(connection);
        return types;
    }

    public Type getById(long id) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "select id, name, parentId from type where id = ?";
        Type query = runner.query(connection, sql, new BeanHandler<>(Type.class), id);
        DBHelper.close(connection);
        return query;
    }

    public int modify(long id, String name, long parentId) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "update type set name = ?, parentId = ? where id = ?";
        int update = runner.update(connection, sql, name, parentId, id);
        DBHelper.close(connection);
        return update;
    }

    public int remove(long id) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "delete from `type` where id = ?";
        int update = runner.update(connection, sql, id);
        DBHelper.close(connection);
        return update;
    }
}
