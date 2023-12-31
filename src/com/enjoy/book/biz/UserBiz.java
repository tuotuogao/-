package com.enjoy.book.biz;

import com.enjoy.book.bean.User;
import com.enjoy.book.dao.UserDao;

import java.sql.SQLException;

public class UserBiz {
    //构建UserDao的对象
    UserDao userDao = new UserDao();
    public User getUser(String name, String pwd){
        User  user = null;
        try {
            user  = userDao.getUser(name,pwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
    public int modifyPwd(long id,String pwd){
        int count = 0;
        try {
            count = userDao.modifyPwd(id,pwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
}
