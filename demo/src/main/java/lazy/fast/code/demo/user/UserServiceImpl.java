package lazy.fast.code.demo.user;

import lazy.fast.code.core.orm.BaseRepository;
import lazy.fast.code.core.orm.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息 - service - impl
 *
 * @author wendell
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(BaseRepository<User> baseRepository) {
        super(baseRepository);
        this.userRepository = (UserRepository)baseRepository;
    }

    @Override
    public List<User> selectAll() {
        return this.getRepository().selectAll();
    }

    @Override
    public List<User> listUsers() {
        return this.userRepository.listUsers();
    }

}
