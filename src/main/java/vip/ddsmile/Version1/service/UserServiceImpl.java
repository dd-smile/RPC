package vip.ddsmile.Version1.service;



import vip.ddsmile.pojo.User;

import java.util.Random;
import java.util.UUID;

/**
 * 服务端实现类
 */
public class UserServiceImpl implements UserService {

    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("客户端查询了"+id+"的用户");
        // 模拟从数据库中取用户的行为
        Random random = new Random();
        User user = User.builder().userName(UUID.randomUUID().toString())
                .id(id)
                .sex(random.nextBoolean()).build();
        return user;
    }

    @Override
    public Integer insertUserId(User user) {
        //模拟插入一个用户
        System.out.println("插入数据成功" + user);
        return 1;
    }


}
