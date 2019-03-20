package yj.core.HanaCon;

import java.sql.*;

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


    public void select(String sql) throws Exception {
        Connection con = this.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        this.processResult(rs);
        this.closeConnection(con, pstmt);

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

    private void processResult(ResultSet rs) throws Exception {
        if (rs.next()) {
            ResultSetMetaData rsmd = rs.getMetaData();
            int colNum = rsmd.getColumnCount();
            for (int i = 1; i <= colNum; i++) {
                if (i == 1) {
                    System.out.print(rsmd.getColumnName(i));
                } else {
                    System.out.print("\t" + rsmd.getColumnName(i));
                }

            }
            System.out.print("\n");
            System.out.println("———————–");
            do {
                for (int i = 1; i <= colNum; i++) {
                    if (i == 1) {
                        System.out.print(rs.getString(i));
                    } else {
                        System.out.print("\t"
                                + (rs.getString(i) == null ? "null" : rs
                                .getString(i).trim()));
                    }

                }
                System.out.print("\n");
            } while (rs.next());
        } else {
            System.out.println("query not result.");
        }

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
