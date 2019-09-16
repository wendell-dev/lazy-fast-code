package lazy.fast.code.demo.user;

import lazy.fast.code.core.exception.NoContentNotException;
import lazy.fast.code.core.orm.BaseRepository;
import lazy.fast.code.core.orm.BaseServiceImpl;
import lazy.fast.code.demo.address.Address;
import lazy.fast.code.demo.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息
 *
 * @author wendell
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private final UserRepository userRepository;
    private final AddressService addressService;

    @Autowired
    public UserServiceImpl(BaseRepository<User> baseRepository, AddressService addressService) {
        super(baseRepository);
        this.userRepository = (UserRepository)baseRepository;
        this.addressService = addressService;
    }

    @Override
    public List<User> listUsers() {
        List<User> users = this.userRepository.listUsers();
        if (CollectionUtils.isEmpty(users)) {
            throw new NoContentNotException();
        }
        return users;
    }

    @Override
    public List<UserAddressDTO> listUserAddress() {
        List<User> users = this.list(null);
        if (CollectionUtils.isEmpty(users)) {
            throw new NoContentNotException();
        }
        List<UserAddressDTO> res = new ArrayList<>(users.size());
        users.forEach(o -> {
            UserAddressDTO dto = UserAddressDTO.of();
            dto.setUser(o);
            Address address = Address.of();
            address.setUserId(o.getId());
            dto.setAddressList(this.addressService.list(address));
            res.add(dto);
        });
        return res;
    }

}
