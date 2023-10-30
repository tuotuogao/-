package com.enjoy.book.dao;

import com.enjoy.book.bean.MemberType;
import com.enjoy.book.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MemberTypeDao {

    QueryRunner runner = new QueryRunner();

    public MemberType getById(long typeId) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "select * from membertype where id = ?";
        MemberType memberType = runner.query(connection, sql, new BeanHandler<>(MemberType.class), typeId);
        DBHelper.close(connection);
        return memberType;
    }

    public List<MemberType> getAll() throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "select * from membertype";
        List<MemberType> query = runner.query(connection, sql, new BeanListHandler<>(MemberType.class));
        DBHelper.close(connection);
        return query;
    }
}
