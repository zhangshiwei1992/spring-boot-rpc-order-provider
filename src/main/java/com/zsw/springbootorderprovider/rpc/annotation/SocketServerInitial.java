package com.zsw.springbootorderprovider.rpc.annotation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.zsw.springbootorderprovider.rpc.ProcessorHandler;

/**
 * spring容器启动完成之后会触发ContextRefreshedEvent事件, 在这里创建服务端连接并监听客户端请求,如果有客户端连接请求就进行处理
 *
 * @author zhangshiwei
 * @since 2020年9月15日 下午10:01:43
 */
@Component // 交给spring管理
public class SocketServerInitial implements ApplicationListener<ContextRefreshedEvent> {

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("SocketServerInitial onApplicationEvent - spring容器启动完成之后会触发ContextRefreshedEvent事件");

        ServerSocket serverSocket = null;
        try {
            // 创建port端口的服务端
            serverSocket = new ServerSocket(1234);

            while (true) {
                // 监听客户端请求
                Socket socket = serverSocket.accept();

                System.out.println("另外一个线程处理,此监听客户端请求的线程就不用阻塞在这里了");
                executorService.execute(new ProcessorHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != serverSocket) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
