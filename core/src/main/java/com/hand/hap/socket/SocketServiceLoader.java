package com.hand.hap.socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *  socket service 与tomcat一起启动
 *  @author     917110140
 */
public class SocketServiceLoader implements ServletContextListener {
    // socket server 线程
    private SocketThread socketThread;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if (null != socketThread && !socketThread.isInterrupted()) {
            socketThread.closeSocketServer();
            socketThread.interrupt();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (null == socketThread) {
            // 新建线程类
            socketThread = new SocketThread(null);
            // 启动线程
            socketThread.start();
        }
    }
}
