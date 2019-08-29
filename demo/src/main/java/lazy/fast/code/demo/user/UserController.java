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
 * @date 2019-08-28 12:00
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
        return this.userService.listUsers();
    }

    @GetMapping("/all")
    public List<User> selectAll() {
        return this.userService.selectAll();
    }

    @PostMapping
    public User save() {
        User user = User.of();
        user.setId(3L);
        user.setName("张三");
        this.getService().save(user);
        return user;
    }

    @PutMapping
    public User update() {
        User user = User.of();
        user.setId(3L);
        user.setName("张三1");
        this.getService().update(user);
        return user;
    }

    @DeleteMapping
    public void delete() {
        User user = User.of();
        user.setId(3L);
        user.setName("张三");
        this.getService().remove(user);
    }

}
