package com.zsw.springbootorderprovider.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.alibaba.fastjson.JSON;
import com.zsw.example.RpcRequestDto;
import com.zsw.springbootorderprovider.rpc.annotation.Mediator;

/**
 * 发布操作类ProcessorHandler
 *
 * @author zhangshiwei
 * @since 2020年9月10日 下午11:01:11
 */
public class ProcessorHandler implements Runnable {

    /**
     * socket连接, 要发布的实例服务
     */
    private Socket socket;
    //    private Object service;

    //    public ProcessorHandler(Socket socket, Object service) {
    //        this.socket = socket;
    //        this.service = service;
    //    }

    public ProcessorHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            //  反序列化读取请求数据对象dto
            RpcRequestDto requestDto = (RpcRequestDto) objectInputStream.readObject();
            System.out.println("服务端 ProcessorHandler run requestDto : " + JSON.toJSON(requestDto));

            // 通过 路由 调用服务
            Mediator mediator = Mediator.getInstance();
            Object result = mediator.processor(requestDto);

            // 通过反射调用方法:
            //            Object result = invoke(requestDto);

            System.out.println("序列化写入 响应结果 : " + JSON.toJSON(result));
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != objectInputStream) {
                    objectInputStream.close();
                }
                if (null != objectOutputStream) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 通过反射调用方法
    //    private Object invoke(RpcRequestDto requestDto) {
    //        Object result = null;
    //        try {
    //            // 1. 手动加载class类
    //            Class clazz = Class.forName(requestDto.getClassName());
    //            // 2. 根据方法和参数类型 获取方法
    //            Method method = clazz.getMethod(requestDto.getMethodName(), requestDto.getType());
    //            // 3. 传入参数, 反射调用发布服务类
    //            result = method.invoke(service, requestDto.getArgs());
    //            System.out.println("服务端 ProcessorHandler run result: " + result);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //        return result;
    //    }

}
