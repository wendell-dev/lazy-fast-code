package lazy.fast.code.demo.user;

import io.swagger.annotations.Api;
import lazy.fast.code.core.orm.BaseController;
import lazy.fast.code.core.orm.BaseService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户信息 - controller
 *
 * @author wendell
 */
@Api(tags = "用户")
@RestController
@RequestMapping(value = "/users")
public class UserController extends BaseController<User> {

    private final UserService userService;

    public UserController(BaseService<User> baseService) {
        super(baseService);
        this.userService = (UserService)baseService;
    }

    @GetMapping
    public List<User> listUsers() {
        return super.getService().list(null);
    }

    @GetMapping("/self")
    public List<User> listUsersBySelf() {
        return this.userService.listUsers();
    }

    @GetMapping("/address")
    public List<UserAddressDTO> listUserAddress() {
        return this.userService.listUserAddress();
    }

    @PostMapping
    public User save() {
        User user = User.of();
        user.setId(1L);
        user.setName("张三");
        super.getService().save(user);
        return user;
    }

    @PutMapping
    public User update() {
        User user = User.of();
        user.setId(1L);
        user.setName("张三1");
        super.getService().update(user);
        return user;
    }

    @DeleteMapping
    public void delete() {
        User user = User.of();
        user.setId(1L);
        user.setName("张三");
        super.getService().remove(user);
    }

}
