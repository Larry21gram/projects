package com.larry.user.dao;

import com.larry.user.domain.User;
import com.larry.user.service.exception.ChangeFailedException;
import com.larry.util.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {
    private QueryRunner qr = new QueryRunner();
    private Connection connection;

//    依据用户名查询
    public User queryByUsername(String username){
        String sql = "SELECT * FROM tb_user WHERE username=?";
        connection = C3p0Utils.getConn();
        User userDB = null;
        try {
            userDB = qr.query(connection, sql, new BeanHandler<User>(User.class), username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            C3p0Utils.close(connection,null,null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDB;
    }

    public int add(User userForm) {
        String sql = "INSERT INTO tb_user VALUES (?,?,?,?,?,?)";
        connection = C3p0Utils.getConn();
        int insert = -1;
        try {
            insert = qr.update(connection,
                    sql,
                    userForm.getUid(),
                    userForm.getUsername(),
                    userForm.getPassword(),
                    userForm.getEmail(),
                    userForm.getCode(),
                    userForm.getState());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            C3p0Utils.close(connection,null,null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insert;
    }

    public int changeByCode(String code) throws ChangeFailedException {
        String sql = "UPDATE tb_user SET state=1 WHERE code=?";
        Connection connection = this.connection = C3p0Utils.getConn();
        try {
            int change = qr.update(connection, sql, code);
            return change;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            C3p0Utils.close(connection,null,null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new ChangeFailedException();
    }
}
