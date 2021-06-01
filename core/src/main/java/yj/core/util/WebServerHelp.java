package yj.core.util;

public class WebServerHelp {

    private String username;
    private String password;

    private String HanaDRIVER;
    private String HanaUrl;
    private String HanaUserName;
    private String HanaPass;
    private String mandt;

    private String mesOraDriver;
    private String mesOraUrl;
    private String mesOraUserName;
    private String mesOraPass;

    private String mesDbuser;
    private String mesDbpassword;
    private String mesDbUrl;



    public WebServerHelp(){
        //dev
        this.username = "HAPUSER";
        this.password = "Yjsap123@CQ";

        //dev
        this.HanaDRIVER = "com.sap.db.jdbc.Driver";
        this.HanaUrl ="jdbc:sap://192.168.3.20:35015?reconnect=true";
        this.HanaUserName = "FINEREPORT";
        this.HanaPass = "Finereport159";
        this.mandt = "300";

        //dev
        this.mesOraDriver = "oracle.jdbc.OracleDriver";
        this.mesOraUrl = "jdbc:oracle:thin:@192.168.0.114:1521:orclyj";
        this.mesOraUserName = "mes_query_usr";
        this.mesOraPass = "mesapp12345";

        this.mesDbUrl = "jdbc:sqlserver://192.168.4.41:1433;databaseName=tn_iot_yz;integratedSecurity=false;";
        this.mesDbuser = "sa";
        this.mesDbpassword = "Pinknet8";



//        //prd
//          this.username = "HAPUSER";
//          this.password = "YJhap201707@CQ";
//        //prd
//
//        this.HanaDRIVER = "com.sap.db.jdbc.Driver";
//        this.HanaUrl ="jdbc:sap://192.168.3.11:30015?reconnect=true";
//        this.HanaUserName = "SAPABAP1";
//        this.HanaPass = "Handhand0";
//        this.mandt = "800";
//
//
//        this.mesOraDriver = "oracle.jdbc.OracleDriver";
//        this.mesOraUrl = "jdbc:oracle:thin:@192.168.4.37:1521:orclyj";
//        this.mesOraPass = "mesapp12345";
//        this.mesOraUserName = "mes_query_usr";
//
//        this.mesDbUrl = "jdbc:sqlserver://192.168.4.80:1433;databaseName=tn_iot;integratedSecurity=false;";
//        this.mesDbuser = "sa";
//        this.mesDbpassword = "c1!";


    }

    public String getMesDbuser() {
        return mesDbuser;
    }

    public void setMesDbuser(String mesDbuser) {
        this.mesDbuser = mesDbuser;
    }

    public String getMesDbpassword() {
        return mesDbpassword;
    }

    public void setMesDbpassword(String mesDbpassword) {
        this.mesDbpassword = mesDbpassword;
    }

    public String getMesDbUrl() {
        return mesDbUrl;
    }

    public void setMesDbUrl(String mesDbUrl) {
        this.mesDbUrl = mesDbUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getHanaDRIVER() {
        return HanaDRIVER;
    }

    public void setHanaDRIVER(String hanaDRIVER) {
        HanaDRIVER = hanaDRIVER;
    }

    public String getHanaUrl() {
        return HanaUrl;
    }

    public void setHanaUrl(String hanaUrl) {
        HanaUrl = hanaUrl;
    }

    public String getHanaUserName() {
        return HanaUserName;
    }

    public void setHanaUserName(String hanaUserName) {
        HanaUserName = hanaUserName;
    }

    public String getHanaPass() {
        return HanaPass;
    }

    public void setHanaPass(String hanaPass) {
        HanaPass = hanaPass;
    }

    public String getMesOraDriver() {
        return mesOraDriver;
    }

    public void setMesOraDriver(String mesOraDriver) {
        this.mesOraDriver = mesOraDriver;
    }

    public String getMesOraUrl() {
        return mesOraUrl;
    }

    public void setMesOraUrl(String mesOraUrl) {
        this.mesOraUrl = mesOraUrl;
    }

    public String getMesOraUserName() {
        return mesOraUserName;
    }

    public void setMesOraUserName(String mesOraUserName) {
        this.mesOraUserName = mesOraUserName;
    }

    public String getMesOraPass() {
        return mesOraPass;
    }

    public void setMesOraPass(String mesOraPass) {
        this.mesOraPass = mesOraPass;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }
}
