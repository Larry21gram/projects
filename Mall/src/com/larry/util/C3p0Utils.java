package com.larry.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3p0Utils {
//    建立数据源
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

//    获得连接
    public static Connection getConn(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    关闭连接
    public static void close(Connection conn, PreparedStatement pstate, ResultSet rs) throws SQLException {
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw  e ;
            }
        }

        if(pstate!=null){
            pstate.close();
        }

        if(rs!=null){
            rs.close();
        }
    }

}
