package vip.ddsmile.client;

import vip.ddsmile.pojo.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * 客户端
 */
public class RPCClient {
    public static void main(String[] args) {
        System.out.println("启动客户端....");
        try {
            //1.连接服务端(ip,端口)
            Socket socket = new Socket("127.0.0.1",9999);
            //得到socket关联的输出流对象
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //得到socket关联的输入流对象
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //传给服务器id
            oos.writeInt(new Random().nextInt());
            oos.flush();//刷新
            //接收服务端查询出的数据
            User user = (User) ois.readObject();
            System.out.println("服务端返回的User:" + user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("客户端启动失败");
        }
    }
}
