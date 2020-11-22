package com.zsl.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 调用客户端: cmd -> telnet 127.0.0.1 666 -> Ctrl + ] -> send Content
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(666);


        while (true){
            //监听,等待客户端连接
            System.out.println("等待连接...");
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");

            //创建线程与之通信
            cachedThreadPool.execute(()->{
                //
                handler(socket);
            });
        }
    }

    //handler方法与客户端通信
    public static void handler(Socket socket){

        byte[] bytes = new byte[1024];
        //获取输入流
        try {
            System.out.println("线程id: " + Thread.currentThread().getId() + "\n线程名称: " + Thread.currentThread().getName());
            InputStream in = socket.getInputStream();
            //读取客户端发送的数据
            while (in.read(bytes) != -1){
                System.out.println("等待读取...");
                int read = in.read(bytes);
                if(read != -1){
                    System.out.println(new String(bytes,0,read));//输出客户端发送的数据
                }else {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭与客户端的连接");
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
