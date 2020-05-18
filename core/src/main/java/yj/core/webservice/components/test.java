package yj.core.webservice.components;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SchemeSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLSocketFactory;
import java.util.*;

/**
 * Created by TFR on 2017/6/14.
 */
abstract class HTTPSClient extends DefaultHttpClient {
    protected SSLSocketFactory socketFactory;

    public HTTPSClient init() throws Exception {
        this.prepareCertificate();
        this.regist();

        return this;
    }

    public abstract void prepareCertificate() throws Exception;

    protected void regist() {
        ClientConnectionManager ccm = this.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", 443, (SchemeSocketFactory) socketFactory));
    }
}

class HTTPSClientUtil {

    private static final String DEFAULT_CHARSET = "UTF-8";
    public static String doPost(HTTPSClient httpsClient, String url, Map<String, String> paramHeader,
                                Map<String, String> paramBody) throws Exception {
        return doPost(httpsClient, url, paramHeader, paramBody, DEFAULT_CHARSET);
    }

    public static String doPost(HTTPSClient httpsClient, String url, Map<String, String> paramHeader,
                                Map<String, String> paramBody, String charset) throws Exception {
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        setHeader(httpPost, paramHeader);
        setBody(httpPost, paramBody, charset);
        HttpResponse response = httpsClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, charset);
            }
        }
        return result;
    }

    public static String doGet(HTTPSClient httpsClient, String url, Map<String, String> paramHeader,
                               Map<String, String> paramBody) throws Exception {
        return doGet(httpsClient, url, paramHeader, paramBody, DEFAULT_CHARSET);
    }

    public static String doGet(HTTPSClient httpsClient, String url, Map<String, String> paramHeader,
                               Map<String, String> paramBody, String charset) throws Exception {

        String result = null;
        HttpGet httpGet = new HttpGet(url);
        setHeader(httpGet, paramHeader);

        HttpResponse response = httpsClient.execute(httpGet);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, charset);
            }
        }

        return result;
    }

    private static void setHeader(HttpRequestBase request, Map<String, String> paramHeader) {
        // 设置Header
        if (paramHeader != null) {
            Set<String> keySet = paramHeader.keySet();
            for (String key : keySet) {
                request.addHeader(key, paramHeader.get(key));
            }
        }
    }

    private static void setBody(HttpPost httpPost, Map<String, String> paramBody, String charset) throws Exception {
        // 设置参数
        if (paramBody != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Set<String> keySet = paramBody.keySet();
            for (String key : keySet) {
                list.add(new BasicNameValuePair(key, paramBody.get(key)));
            }

            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
        }
    }
}
public class test {
    public static void main(String[] args) throws Exception {
        HTTPSClient httpsClient = new HTTPSClient() {
            @Override
            public void prepareCertificate() throws Exception {

            }
        };

        //httpsClient = new HTTPSCertifiedClient().init();

        String url = "http://127.0.0.1/zollerDbservice/Tool";
        //String url = "https://1.2.6.2:8011/xxx/api/getHealth";

        Map<String, String> paramHeader = new HashMap<>();
        //paramHeader.put("Content-Type", "application/json");
        paramHeader.put("Accept", "application/xml");
        Map<String, String> paramBody = new HashMap<>();
        paramBody.put("client_id", "ankur.tandon.ap@xxx.com");
        paramBody.put("client_secret", "P@ssword_1");
        //String result = HTTPSClientUtil.doPost(httpsClient, url, paramHeader, paramBody);

        String result = HTTPSClientUtil.doGet(httpsClient, url, null, null);

        System.out.println("hello");
    }
}
