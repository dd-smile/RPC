package vip.ddsmile.Version1.service;

import vip.ddsmile.pojo.User;

/**
 * 定义客户端需要调用, 服务端需要提供的接口
 */
public interface UserService {
    /**
     * 客户端通过这个接口调用服务端的实现类
     * @param id 用户id
     * @return 用户
     */
    User getUserByUserId(Integer id);

    // 更新:
    // 给这个服务增加一个功能 服务端接受request请求，并调用request中的对应的方法

    /**
     * 插入一个用户
     * @param user 用户
     * @return 返回成功的行数
     */
    Integer insertUserId(User user);
}
