package yj.core.OracleConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OracleConn {
    private static final String dbUrl="jdbc:oracle:thin:@192.168.94.93:1521:orclyj";
    private static final String dbUserName="mes_query_usr"; // 用户名
    private static final String dbPassword="mesapp12345";
    private static final String jdbcName="oracle.jdbc.OracleDriver";
    //        String dbUrl="jdbc:oracle:thin:@192.168.4.37:1521:orclyj"; // 数据库连接地址 生产环境
    //        String dbUserName="mes_query_usr"; // 用户名
    //        String dbPassword="mesapp12345"; // 密码
    //        String jdbcName="oracle.jdbc.OracleDriver";

    public OracleConn(){

    }
    public static void main(String[] args) {

        OracleConn oracleConn = new OracleConn();
        String sql = "select * from MES_M264L01.WIP_MAIN_DATA";
        try {
            oracleConn.select(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(jdbcName);
            conn = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
            System.out.println(conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public List select(String sql) throws Exception {
        Connection con = this.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        list = this.processResult(rs);
        this.closeConnection(con, pstmt);

        return list;
    }

    private List<Map<String, Object>> processResult(ResultSet rs) throws Exception {

        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        while (rs.next()){
            Map<String,Object> rowData = new HashMap<String,Object>();
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));

            }
            list.add(rowData);
        }
        return list;
    }

    private void closeConnection(Connection con, Statement stmt)  throws Exception{

        if (stmt != null) {
            stmt.close();
        }
        if (con != null) {
            con.close();
        }

    }
}
