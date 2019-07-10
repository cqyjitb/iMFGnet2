package com.hand.hap.intergration.ws.interceptor;

import com.hand.hap.intergration.beans.HapInvokeInfo;
import com.hand.hap.intergration.beans.HapinterfaceBound;
import com.hand.hap.intergration.dto.HapInterfaceInbound;
import com.hand.hap.intergration.dto.HapInterfaceOutbound;
import com.hand.hap.intergration.util.HapInvokeLogUtils;
import com.hand.hap.message.IMessagePublisher;
import com.hand.hap.message.components.InvokeLogManager;
import org.apache.cxf.common.injection.NoJSR250Annotations;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.transport.http.Address;
import org.apache.cxf.transport.http.HTTPConduit;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * @author xiangyu.qi@hand-china.com on 2016/12/2.
 */
@NoJSR250Annotations
public class HapInvokeOutInterceptor extends AbstractPhaseInterceptor<Message> {

    @Autowired
    private IMessagePublisher messagePublisher;

    public HapInvokeOutInterceptor() {
        super(Phase.PRE_STREAM);
    }

    public static long DEFAULT_THRESHOLD = 1024 * 1024 * 10;

    @Override
    public void handleMessage(Message message) throws Fault {
        HapInterfaceInbound inbound = (HapInterfaceInbound) message.getExchange().get(HapInvokeInfo.INVOKE_INFO_INBOUND);
        if (inbound != null) {
            inboundInvoke(inbound, message);
        } else {
            outboundInvoke(message);
        }
    }

    @Override
    public void handleFault(Message message) {
        HapInvokeLogUtils.processCxfHandleFault(message,messagePublisher);
    }

    /**
     * 出站请求
     * @param message
     */
    protected void outboundInvoke(Message message) {
        HapInterfaceOutbound outbound = new HapInterfaceOutbound();
        message.getExchange().put(HapInvokeInfo.INVOKE_INFO_OUTBOUND,outbound);
        outbound.setRequestTime(new Date());
        String url;
        Object address = message.get(HTTPConduit.KEY_HTTP_CONNECTION_ADDRESS);
        if (address instanceof Address) {
            url = ((Address) address).getString();
        } else {
            url = "";
        }
        outbound.setInterfaceUrl(url);
        // 获得serviceName
        Endpoint ep = message.getExchange().getEndpoint();
        EndpointInfo endpoint = ep.getEndpointInfo();
        String serviceName = endpoint.getService().getName().getLocalPart();
        outbound.setInterfaceName(serviceName);
        getXmlContent(message);
    }

    /**
     * 入站请求
     * @param inbound 入站请求记录对象
     * @param message
     */
    protected void inboundInvoke(HapInterfaceInbound inbound, Message message) {

        inbound.setResponseContent(getXmlContent(message));
        inbound.setResponseTime(System.currentTimeMillis() - inbound.getRequestTime().getTime());
        inbound.setRequestStatus(HapInvokeInfo.REQUEST_SUCESS);
        messagePublisher.message(InvokeLogManager.CHANNEL_INBOUND,new HapinterfaceBound(inbound));
    }

    protected String getXmlContent(Message message) {
        String content = "";
        try {
            OutputStream os = message.getContent(OutputStream.class);

            CachedOutputStream cs = new CachedOutputStream(DEFAULT_THRESHOLD);
            message.setContent(OutputStream.class, cs);
            message.getInterceptorChain().doIntercept(message);
            CachedOutputStream csnew = (CachedOutputStream)message.getContent(OutputStream.class);
            InputStream in = csnew.getInputStream();

            String xml = IOUtils.toString(in);

            // 这里对xml做处理，处理完后同理，写回流中
            content = xml;

            HapInterfaceOutbound invokeOut = (HapInterfaceOutbound) message.getExchange().get(HapInvokeInfo.INVOKE_INFO_OUTBOUND);
            if (invokeOut != null) {
                invokeOut.setRequestParameter(content);
            }
            IOUtils.copy(new ByteArrayInputStream(xml.getBytes()), os);

            cs.flush();
            cs.close();
            os.flush();
            message.setContent(OutputStream.class, os);
            os.close();

        } catch (Exception e) {
            throw new Fault(e);
        }
        return content;
    }

}
