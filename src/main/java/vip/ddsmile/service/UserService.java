package vip.ddsmile.service;

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
}
