package yj.core.HanaCon;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HanaCon {
    private static final String DRIVER = "com.sap.db.jdbc.Driver";
    //    private static final String URL = "jdbc:sap://192.168.3.20:36015?reconnect=true";
    private static final String URL = "jdbc:sap://192.168.3.20:35015?reconnect=true";
    //    private static final String USERNAME = "FINEREPORT";prd
//    private static final String PASSWORD = "Finereport3333";
    private static final String USERNAME = "FINEREPORT";
    private static final String PASSWORD = "Finereport159";
    public HanaCon(){

    }

    public static void main(String[] args) {
        HanaCon hanaCon = new HanaCon();
        String sql = "select * from "+ "SAPABAP1"+ "."+"MARA";

        try {
            hanaCon.select(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println(conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
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
