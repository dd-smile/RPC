package vip.ddsmile.Version2.client;

import vip.ddsmile.Version2.pojo.Blog;
import vip.ddsmile.Version2.pojo.User;
import vip.ddsmile.Version2.service.BlogService;
import vip.ddsmile.Version2.service.UserService;

public class RPCClient {
    public static void main(String[] args) {

        RPCClientProxy rpcClientProxy = new RPCClientProxy("127.0.0.1", 9999);
        UserService userService = rpcClientProxy.getProxy(UserService.class);

        User userByUserId = userService.getUserByUserId(10);
        System.out.println("从服务端得到的user为：" + userByUserId);

        User user = User.builder().userName("张三").id(100).sex(true).build();
        Integer integer = userService.insertUserId(user);
        System.out.println("向服务端插入数据："+integer);

        BlogService blogService = rpcClientProxy.getProxy(BlogService.class);
        Blog blogById = blogService.getBlogById(10000);
        System.out.println("从服务端得到的blog为：" + blogById);
    }
}
