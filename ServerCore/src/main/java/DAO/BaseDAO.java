package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDAO {
    public Connection conn = null;
    public Statement stmt = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    static String setSSL = "false";
    static String ip = "localhost";
    static int port = 3306;
    static String database = "instagram";
    static String encoding = "UTF-8";
    static String loginName = "root";
    static String password = "password";

    public synchronized static Connection getConnection() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("La charge d'entra?nement s'est rompue.");
        }
        try {
            String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s&useSSL=%s&serverTimezone=UTC",
                    ip, port, database, encoding,setSSL);
            return DriverManager.getConnection(url, loginName, password);
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Map<String, Object>> select(String sql, Object[] param){
        Connection con = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        try {
            ps = con.prepareStatement(sql);
            if(param != null){
                for(int i = 1; i <= param.length; i++){
                    ps.setObject(i, param[i-1]);
                }
            }
            rs = ps.executeQuery();
            ResultSetMetaData rm = rs.getMetaData();
            // count est le numero de column
            int count = rm.getColumnCount();
            while(rs.next()){
                Map<String,Object> map = new HashMap<String, Object>();
                for(int i = 1; i <= count; i++){
                    // map(Key,Value)
                    if(rs.getObject(rm.getColumnName(i)) == null){
                        map.put(rm.getColumnName(i).toLowerCase(), "null");
                    }else {
                        map.put(rm.getColumnName(i).toLowerCase(), rs.getObject(rm.getColumnName(i)));
                    }
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close(rs, ps, con);
        }
        return list;
    }

    public boolean updateByParams(String sql, Object param[]) {

        boolean flag = false;
        Connection con = getConnection();
        PreparedStatement ps = null;
        try {
            assert con != null;
            ps = con.prepareStatement(sql);
            if(param != null){
                for(int i = 0; i < param.length; i++){
                    ps.setObject(i+1, param[i]);
                }
            }
            int n = ps.executeUpdate();
            System.out.println("n=" + n);
            if(n > 0)
                flag = true;

        } catch (SQLException e) {

            e.printStackTrace();
        }finally{
            close(null, ps, con);
        }
        return flag;
    }

    public boolean BatchUpdateByParams(String sql, Object param[][]){
        Connection con = getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            if(param != null){
                for(int i = 0; i < param.length; i++){//¶àÌõ¼ÇÂ¼
                    for(int j = 1; j <= param[i].length; j++){//Ã¿Ìõ¼ÇÂ¼ÀïµÄ²ÎÊýÌæ»»
                        ps.setObject(j, param[i][j-1]);
                    }
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            close(null, ps, con);
        }
    }

    public int getLastId(String sql, String sql1, Object[] param) {
        Connection con = getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        int id = 0;
        try {
            ps = con.prepareStatement(sql);
            if(param != null){
                for(int i = 1; i <= param.length; i++){
                    ps.setObject(i, param[i-1]);
                }
            }
            ps.executeUpdate();
            ps1 = con.prepareStatement(sql1);
            rs = ps1.executeQuery();
            if(rs.next())
                id = rs.getInt(1);
            close(ps1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            close(rs, ps, con);
        }
        return id;
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement pst) {
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs, PreparedStatement ps, Connection con) {
        close(rs);
        close(ps);
        try {
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
