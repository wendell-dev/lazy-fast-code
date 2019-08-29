package lazy.fast.code.demo.user;

import lazy.fast.code.core.orm.BaseService;

import java.util.List;

/**
 * 用户信息 - service
 *
 * @author wendell
 */
public interface UserService extends BaseService<User> {

    List<User> selectAll();

    List<User> listUsers();

}
