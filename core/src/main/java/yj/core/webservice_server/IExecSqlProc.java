package yj.core.webservice_server;

import yj.core.webservice_server.dto.ExecProcReturn;

public interface IExecSqlProc {
    ExecProcReturn callProc(String guid,String sql);
}
