package lazy.fast.code.demo.user;

import lazy.fast.code.demo.address.Address;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户及地址信息
 *
 * @author wendell
 */
@Data(staticConstructor = "of")
public class UserAddressDTO implements Serializable {

    /**
     * 用户信息
     */
    private User user;

    /**
     * 用户地址列表信息
     */
    private List<Address> addressList;

}
