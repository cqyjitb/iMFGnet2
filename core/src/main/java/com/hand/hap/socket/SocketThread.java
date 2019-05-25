package com.hand.hap.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  socket 线程类
 */
public class SocketThread extends Thread {

    private ServerSocket serverSocket = null;
    private static Logger logger = LoggerFactory.getLogger(SocketThread.class);
    public SocketThread(ServerSocket serverScoket) {
        try {
            if (null == serverSocket) {
                //InetAddress address=InetAddress.getByName("192.168.0.4");
                //System.out.println("服务器ip:"+address);
                //this.serverSocket = new ServerSocket(6002,5,address);
                this.serverSocket = new ServerSocket(8081);
                System.out.println("socket start");
                logger.debug("***************socket start***************");
            }
        } catch (Exception e) {
            logger.error("SocketThread创建socket服务出错");
            logger.error(e.getMessage(),e);
        }
    }

    public void run() {
        while (!this.isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();

                if (null != socket && !socket.isClosed()) {
                    // 处理接受的数据
                    new SocketOperate(socket).start();
                }
                socket.setSoTimeout(0);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void closeSocketServer() {
        try {
            if (null != serverSocket && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage(),e);

        }
    }

}
