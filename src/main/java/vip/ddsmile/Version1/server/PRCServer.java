package vip.ddsmile.Version1.server;

import vip.ddsmile.Version1.pojo.RPCRequest;
import vip.ddsmile.Version1.pojo.RPCResponse;
import vip.ddsmile.Version1.service.UserServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 */
public class PRCServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        try {
            //1.在本机的9999端口监听, 等待连接
            ServerSocket serverSocket =  new ServerSocket(9999);
            System.out.println("服务端启动了....");
            //2.当没有客户端连接9999端口时, 程序会阻塞, 等待连接
            // BIO的方式监听Socket
            while (true){
                Socket socket = serverSocket.accept();
                //开启一个线程处理
                new Thread(()->{
                    try {
                        //得到socket关联的输出流对象
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        //得到socket关联的输入流对象
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        //读取客户端传过来的request
                        RPCRequest request = (RPCRequest) ois.readObject();
                        //反射调用对应方法
                        Method method = userService.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
                        Object invoke = method.invoke(userService, request.getParams());
                        //封装,写入response对象
                        oos.writeObject(RPCResponse.success(invoke));
                        oos.flush();//刷新
                    } catch (IOException | ClassNotFoundException | NoSuchMethodException e) {
                        e.printStackTrace();
                        System.out.println("IO读取数据错误");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        System.out.println("IO读取数据错误");
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                        System.out.println("IO读取数据错误");
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }
    }
}

