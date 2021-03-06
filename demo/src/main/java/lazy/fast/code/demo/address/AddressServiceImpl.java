package lazy.fast.code.demo.address;

import org.springframework.stereotype.Service;

import lazy.fast.code.core.web.orm.BaseRepository;
import lazy.fast.code.core.web.orm.BaseServiceImpl;

/**
 * 地址信息
 *
 * @author wendell
 */
@Service
public class AddressServiceImpl extends BaseServiceImpl<Address> implements AddressService {

    public AddressServiceImpl(BaseRepository<Address> baseRepository) {
        super(baseRepository);
    }

}
