package lazy.fast.code.demo.user;

import lazy.fast.code.core.orm.BaseService;

import java.util.List;

/**
 * 用户信息
 *
 * @author wendell
 */
public interface UserService extends BaseService<User> {

    /**
     * 查询用户信息列表 - 使用Repository中的自定义方法
     *
     * @return 用户信息列表
     */
    List<User> listUsers();

    /**
     * 查询用户及其地址列表信息
     * 
     * @return 用户及其地址列表信息
     */
    List<UserAddressDTO> listUserAddress();

}
