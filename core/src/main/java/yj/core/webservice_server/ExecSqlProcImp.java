package yj.core.webservice_server;

import yj.core.util.WebServerHelp;
import yj.core.webservice_server.dto.ExecProcReturn;

import javax.jws.WebService;
import java.sql.*;

@WebService
public class ExecSqlProcImp implements IExecSqlProc {
    private String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";//SQL数据库引擎
    @Override
    public ExecProcReturn callProc(String guid,String sql) {
        ExecProcReturn res = new ExecProcReturn();
        WebServerHelp serverHelp = new WebServerHelp();
        Connection connection=null;
        try{
            Class.forName(driverName);
            connection=DriverManager.getConnection(serverHelp.getMesDbUrl(),serverHelp.getMesDbuser(),serverHelp.getMesDbpassword());
        }catch (Exception e){
            e.printStackTrace();
            res.setCode("-1");
            res.setMsg(e.getMessage());
            return res;
        }
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(sql);
        }catch (SQLException e) {
            res.setMsg(e.getMessage());
            res.setCode("-1");
            return res;
        }

        try {

                String querySql = "select * from YJ_PROC_EXEC_LOG where EXEC_GUID = '"+guid+"'";
                ResultSet queryRs = stmt.executeQuery(querySql);
                int rows = 0;

                while (queryRs.next()){
                        res.setMsg(queryRs.getString("EXEC_MSG"));
                        res.setCode(queryRs.getString("RETURN_VALUE"));
                        rows++;
                }
                if(rows == 0){
                    res.setMsg("id:"+guid+",数据库中异常记录不存在");
                    res.setCode("-1");
                }
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
            res.setCode("0");
            res.setMsg(e.getMessage());
            return res;
        }
        return res;
    }
}
