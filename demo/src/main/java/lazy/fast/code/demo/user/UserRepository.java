package lazy.fast.code.demo.user;

import lazy.fast.code.core.orm.BaseRepository;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户信息
 *
 * @author wendell
 */
@Repository
@Mapper
public interface UserRepository extends BaseRepository<User> {

    /**
     * 查询用户信息列表
     * 
     * @return 用户信息列表
     */
    @Select("select * from sys_user")
    List<User> listUsers();

}
