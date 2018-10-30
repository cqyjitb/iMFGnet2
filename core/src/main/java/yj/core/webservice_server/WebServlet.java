package yj.core.webservice_server;

import jdk.nashorn.internal.codegen.CompilerConstants;
import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

import javax.servlet.ServletConfig;

/**
 * Created by 917110140 on 2018/9/6.
 */
public class WebServlet extends CXFNonSpringServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void loadBus(ServletConfig sc) {
        super.loadBus(sc);
        Bus bus = getBus();
        BusFactory.setDefaultBus(bus);

        HelloWorldServerImp helloWorld = new HelloWorldServerImp();//实现类

       ServerFactoryBean serverFactoryBean2 = new ServerFactoryBean(); //server工厂
        serverFactoryBean2.setServiceClass(IHelloWorldServer.class);// 接口类
        serverFactoryBean2.setAddress("/helloWorld"); //服务请求路径
        serverFactoryBean2.setServiceBean(helloWorld);
        serverFactoryBean2.create();

        //发布的箱号查询及同步接口
        ServerFactoryBean serverFactoryBean = new ServerFactoryBean(); //server工厂
        QueryXhcardImp queryXhcardImp = new QueryXhcardImp();
        serverFactoryBean.setServiceClass(IQueryXhcard.class);// 接口类
        serverFactoryBean.setAddress("/QueryXhcard"); //服务请求路径
        serverFactoryBean.setServiceBean(queryXhcardImp);
        serverFactoryBean.create();

        //发布的migo接口
        ServerFactoryBean serverFactoryBean1 = new ServerFactoryBean(); //server工厂
        CallMigoImpl callMigoImp = new CallMigoImpl();
        serverFactoryBean1.setServiceClass(ICallMigo.class);
        serverFactoryBean1.setAddress("/CallMigo"); //服务请求路径
        serverFactoryBean1.setServiceBean(callMigoImp);
        serverFactoryBean1.create();

        //发布的migo接口
        ServerFactoryBean serverFactoryBean3 = new ServerFactoryBean(); //server工厂
        JjbgImpl jjbgimp = new JjbgImpl();
        serverFactoryBean3.setServiceClass(IJjbg.class);
        serverFactoryBean3.setAddress("/Jjbg"); //服务请求路径
        serverFactoryBean3.setServiceBean(jjbgimp);
        serverFactoryBean3.create();

        //发布的创建托盘码接口
        ServerFactoryBean serverFactoryBean4 = new ServerFactoryBean(); //server工厂
        CreateTpImpl createTp = new CreateTpImpl();
        serverFactoryBean4.setServiceClass(ICreateTp.class);
        serverFactoryBean4.setAddress("/CreateTp"); //服务请求路径
        serverFactoryBean4.setServiceBean(createTp);
        serverFactoryBean4.create();

        //发布的机加上线围堵接口
        ServerFactoryBean serverFactoryBean5 = new ServerFactoryBean(); //server工厂
        WeiduImpl weidu = new WeiduImpl();
        serverFactoryBean5.setServiceClass(IWeidu.class);
        serverFactoryBean5.setAddress("/Weidu"); //服务请求路径
        serverFactoryBean5.setServiceBean(weidu);
        serverFactoryBean5.create();

        //发布的查询托盘状态接口
        ServerFactoryBean serverFactoryBean6 = new ServerFactoryBean(); //server工厂
        ReadTpImpl readTp = new ReadTpImpl();
        serverFactoryBean6.setServiceClass(IReadTp.class);
        serverFactoryBean6.setAddress("/ReadTp"); //服务请求路径
        serverFactoryBean6.setServiceBean(readTp);
        serverFactoryBean6.create();

        //发布的查询托盘状态接口
        ServerFactoryBean serverFactoryBean7 = new ServerFactoryBean(); //server工厂
        SyncXhcardImpl syncXhcard = new SyncXhcardImpl();
        serverFactoryBean7.setServiceClass(IsyncXhcard.class);
        serverFactoryBean7.setAddress("/SyncXhcard"); //服务请求路径
        serverFactoryBean7.setServiceBean(syncXhcard);
        serverFactoryBean7.create();
    }
}
