package com.hand.hap.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

@Component
public class SocketOperate extends Thread {

    private Socket socket;
    public static SocketOperate socketOperate;
    private static Logger logger = LoggerFactory.getLogger(SocketOperate.class);
    public SocketOperate() {
        super();
    }

    public SocketOperate(Socket socket) {
        this.socket = socket;
    }

    public void init() throws Exception {
        socketOperate = this;
    }

    public void run() {
        try {
            InputStream in = socket.getInputStream();
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            byte b[]=new byte[10];
            String string = "";
            while(in.read(b) != -1) {
                System.out.println("原始数据changdu:"+in.read(b));

            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }
}
