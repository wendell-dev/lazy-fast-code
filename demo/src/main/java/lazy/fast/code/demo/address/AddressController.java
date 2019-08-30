package lazy.fast.code.demo.address;

import io.swagger.annotations.Api;
import lazy.fast.code.core.orm.BaseController;
import lazy.fast.code.core.orm.BaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 地址信息 - controller
 *
 * @author wendell
 */
@Api(tags = "地址信息")
@RestController
@RequestMapping(value = "/address")
public class AddressController extends BaseController<Address> {

    public AddressController(BaseService<Address> baseService) {
        super(baseService);
    }

    @GetMapping
    public List<Address> listAddress() {
        return this.getService().list(null);
    }

    @PostMapping
    public Address save() {
        Address address = Address.of();
        address.setCity("重庆");
        address.setAddress("沙坪坝");
        address.setUserId(3L);
        this.getService().save(address);
        return address;
    }

}
