package lazy.fast.code.demo.user;

import lazy.fast.code.core.exception.NoContentNotException;
import lazy.fast.code.core.orm.BaseRepository;
import lazy.fast.code.core.orm.BaseServiceImpl;
import lazy.fast.code.demo.address.Address;
import lazy.fast.code.demo.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息 - service - impl
 *
 * @author wendell
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public UserServiceImpl(BaseRepository<User> baseRepository, AddressRepository addressRepository) {
        super(baseRepository);
        this.userRepository = (UserRepository)baseRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<User> listUsers() {
        return this.userRepository.listUsers();
    }

    @Override
    public List<UserAddressDTO> listUserAddress() {
        List<User> users = super.list(null);
        if (CollectionUtils.isEmpty(users)) {
            throw new NoContentNotException();
        }
        List<UserAddressDTO> res = new ArrayList<>(users.size());
        users.forEach(o -> {
            UserAddressDTO dto = UserAddressDTO.of();
            dto.setUser(o);
            Address address = Address.of();
            address.setUserId(o.getId());
            List<Address> addressList = this.addressRepository.select(address);
            dto.setAddressList(addressList);
            res.add(dto);
        });
        return res;
    }

}