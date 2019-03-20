package yj.core.OracleConn;

import java.sql.*;

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

    public ResultSet select(String sql) throws Exception {
        Connection con = this.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
//        this.processResult(rs);
//        this.closeConnection(con, pstmt);
        return rs;
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
