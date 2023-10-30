package com.enjoy.book.dao;

import com.enjoy.book.bean.Member;
import com.enjoy.book.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MemberDao {

    QueryRunner runner = new QueryRunner();

    public int add(String name, String pwd, long typeId, double balance, String tel, String idNumber) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "insert into member(`name`, pwd, typeId, balance, regdate, tel, idNumber) " +
                "values(?, ?, ?, ?, CURRENT_DATE, ?, ?)";
        int update = runner.update(connection, sql, name, pwd, typeId, balance, tel, idNumber);
        DBHelper.close(connection);
        return update;
    }

    public int modifyBalance(long memberId, double amount) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "update member set balance = balance + ? where id = ?";
        int update = runner.update(connection, sql, amount, memberId);
        DBHelper.close(connection);
        return update;
    }

    public int modifyBalance(String idNumber, double amount) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "update member set balance = balance + ? where idNumber = ?";
        int update = runner.update(connection, sql, amount, idNumber);
        DBHelper.close(connection);
        return update;
    }

    public int modify(long id, String name, String pwd, long typeId, double balance, String tel, String idNumber) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "update member set `name` = ?, pwd = ?, typeId = ?, balance = ?, tel = ?, idNumber = ? " +
                "where id = ?";
        int update = runner.update(connection, sql, name, pwd, typeId, balance, tel, idNumber, id);
        DBHelper.close(connection);
        return update;
    }

    public boolean exits(long id) {
        return false;
    }

    public List<Member> getAll() throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "select id, `name`, pwd, typeId, balance, regdate, tel, idNumber from member";
        List<Member> query = runner.query(connection, sql, new BeanListHandler<>(Member.class));
        DBHelper.close(connection);
        return query;
    }

    public Member getById(long id) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "select id, `name`, pwd, typeId, balance, regdate, tel, idNumber from member where id = ?";
        Member query = runner.query(connection, sql, new BeanHandler<>(Member.class), id);
        DBHelper.close(connection);
        return query;
    }

    public Member getByIdNumber(String idNumber) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "select id, `name`, pwd, typeId, balance, regdate, tel, idNumber from member where idNumber = ?";
        Member query = runner.query(connection, sql, new BeanHandler<>(Member.class), idNumber);
        DBHelper.close(connection);
        return query;
    }

    public int remove(long id) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "delete from member where id = ?";
        int update = runner.update(connection, sql, id);
        DBHelper.close(connection);
        return update;
    }
}
