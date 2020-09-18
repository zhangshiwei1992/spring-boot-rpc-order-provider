package com.zsw.springbootorderprovider.rpc;

/**
 * rpc 代理发布服务端
 *
 * @author zhangshiwei
 * @since 2020年9月10日 下午10:01:30
 */
//public class RpcProxyServer {
//
//    private ExecutorService executorService = Executors.newFixedThreadPool(5);
//
//    public void publisher(Object service, int port) {
//        System.out.println("发布 " + service.getClass().getName() + " 到 " + port + " 端口");
//
//        ServerSocket serverSocket = null;
//        try {
//            // 创建port端口的服务端
//            serverSocket = new ServerSocket(port);
//
//            while (true) {
//                // 监听客户端请求
//                Socket socket = serverSocket.accept();
//
//                System.out.println("另外一个线程处理,此监听客户端请求的线程就不用阻塞在这里了");
//                executorService.execute(new ProcessorHandler(socket, service));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (null != serverSocket) {
//                try {
//                    serverSocket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//}
