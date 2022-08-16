package vip.ddsmile.Version1.client;

import vip.ddsmile.Version1.service.UserService;
import vip.ddsmile.pojo.User;


/**
 * 客户端
 */
public class RPCClient {
    public static void main(String[] args) {
        System.out.println("启动客户端...");

        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 9999);
        UserService proxy = clientProxy.getProxy(UserService.class);

        // 服务的方法1
        User userByUserId = proxy.getUserByUserId(10);
        System.out.println("从服务端得到的user为：" + userByUserId);
        // 服务的方法2
        User user = User.builder().userName("张三").id(100).sex(true).build();
        Integer integer = proxy.insertUserId(user);
        System.out.println("向服务端插入数据："+integer);
    }
}
