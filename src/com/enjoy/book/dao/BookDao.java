package com.enjoy.book.dao;

import com.enjoy.book.bean.Book;
import com.enjoy.book.util.DBHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookDao {

    QueryRunner runner = new QueryRunner();

    public List<Book> getBooksByTypeId(long typeId) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "select * from book where typeId = ?";
        List<Book> books = runner.query(connection, sql, new BeanListHandler<>(Book.class), typeId);
        DBHelper.close(connection);
        return books;
    }

    public int add(long typeId, String name, double price, String desc, String pic, String publish, String author, long stock, String address) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "insert into book(typeId, `name`, price, `desc`, pic, publish, author, stock, address) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int count = runner.update(connection, sql, typeId, name, price, desc, pic, price, author, stock, address);
        DBHelper.close(connection);
        return count;
    }

    public int modify(long id, long typeId, String name, double price, String desc, String pic, String publish, String author, long stock, String address) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "update book set typeId = ?, name = ?, price = ?, desc = ?, pic = ?, publish = ?, author = ?, stock = ?, address = ? where id = ?";
        int update = runner.update(connection, sql, typeId, name, price, desc, pic, publish, author, stock, address, id);
        DBHelper.close(connection);
        return update;
    }

    public int remove(long id) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "delete from book where id = ?";
        int update = runner.update(connection, sql, id);
        DBHelper.close(connection);
        return update;
    }

    public Book getById(long id) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "select * from book where id = ?";
        Book book = runner.query(connection, sql, new BeanHandler<>(Book.class), id);
        DBHelper.close(connection);
        return book;
    }

    public List<Book> getByPage(int pageIndex, int pageSize) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "select * from book limit ?,?";
        int offset = (pageIndex - 1) * pageSize;
        List<Book> books = runner.query(connection, sql, new BeanListHandler<>(Book.class), offset, pageSize);
        DBHelper.close(connection);
        return books;
    }

    public Book getByName(String bookName) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "select * from book where name = ?";
        Book book = runner.query(connection, sql, new BeanHandler<>(Book.class), bookName);
        DBHelper.close(connection);
        return book;
    }

    public int getCount() throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "select count(id) from book";
        Number data = runner.query(connection, sql, new ScalarHandler<>());
        DBHelper.close(connection);
        return data.intValue();
    }

    public int modify(long bookId, int count) throws SQLException {
        Connection connection = DBHelper.getConnection();
        String sql = "update book set stock = stock + ? where id = ?";
        int update = runner.update(connection, sql, count, bookId);
        DBHelper.close(connection);
        return update;
    }
}
