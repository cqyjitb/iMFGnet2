package yj.core.TaskJob;

import yj.core.werbserver_crhd.components.SyncCrhdWebserviceUtil;

public class SyncCrhdJob {
    public SyncCrhdJob(){
        SyncCrhdWebserviceUtil sync = new SyncCrhdWebserviceUtil();
        sync.receiveConfirmation();
    }
}
