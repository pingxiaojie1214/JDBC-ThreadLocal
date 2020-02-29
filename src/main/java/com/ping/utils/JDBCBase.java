package com.ping.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCBase {
    private static String driverName = "oracle.jdbc.driver.OracleDriver";
    private static String url = "jdbc:oracle:thin:@61.155.85.74:152:orcl";
    private static String username = "PXJ";
    private static String password = "1";
    private static Connection conn = null;

    private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();

    static{
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得连接
     * @return
     */
    public static Connection getConn(){
        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 获得连接
     * @return
     */
    public static Connection getConnection(){
        Connection connection = local.get();
        if(connection == null){
            local.set(getConn());
        }
        return local.get();
    }

    /**
     * 开启事务
     */
    public static void startTransaction(){
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务,关闭连接。
     */
    public static void  commit(){
        Connection connection = local.get();
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务，关闭连接。
     */
    public static void rollback(){
        Connection connection = local.get();
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */
    public static void closeConnection(ResultSet rs, PreparedStatement pst, Connection con){
        try {
            if(rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 该方法用来执行insert,update,delete操作
     * @param sql
     * @param args
     * @return
     */
    public static int executeSQL(String sql,Object...args) {
        Connection connection = getConnection();
        PreparedStatement pst = null;
        int rows = 0;
        try{
            pst = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pst.setObject(i + 1, args[i]);
            }
            rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("operate successfully !");
            } else {
                System.out.println("fail!");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("dao层-->executeSQL方法出现错误了");
        } finally {
            closeConnection(null,pst, null);
        }
        return rows;
    }

    public static List<Map<String, Object>> querySQL(String sql, String mapColumn, Object...args){
        Connection connection = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        try{
            pst = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                pst.setObject(i+1, args[i]);
            }
            rs = pst.executeQuery();
            while(rs.next()) {
                if (mapColumn!=null&&!mapColumn.equals("")) {
                    Map<String, Object> m=new HashMap<String, Object>();
                    String[] mapColumns=mapColumn.split(",");
                    for (int i = 0; i < mapColumns.length; i++) {
                        m.put(mapColumns[i], rs.getObject(mapColumns[i]));
                    }
                    list.add(m);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("dao层-->querySQL方法出现错误了");
        } finally {
            closeConnection(rs,pst, null);
        }
        return list;
    }
}
