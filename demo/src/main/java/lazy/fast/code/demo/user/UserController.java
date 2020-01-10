package lazy.fast.code.demo.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lazy.fast.code.core.web.exception.NoContentNotException;
import lazy.fast.code.core.web.exception.NotFoundException;
import lazy.fast.code.core.web.orm.BaseController;
import lazy.fast.code.core.web.orm.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

/**
 * 用户信息
 *
 * @author wendell
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping(value = "/users")
public class UserController extends BaseController<User> {

    private final UserService userService;

    public UserController(BaseService<User> baseService) {
        super(baseService);
        this.userService = (UserService)baseService;
    }

    @ApiOperation(value = "获取用户信息列表")
    @GetMapping
    public List<User> list() {
        List<User> users = this.getBaseService().list(null);
        if (CollectionUtils.isEmpty(users)) {
            throw new NoContentNotException();
        }
        return users;
    }

    @ApiOperation(value = "根据ID获取用户信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")})
    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        User user = this.getBaseService().get(id);
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }

    @ApiOperation(value = "获取用户信息列表 - 使用Repository中的自定义方法")
    @GetMapping("/self")
    public List<User> listUsers() {
        return this.userService.listUsers();
    }

    @ApiOperation(value = "获取用户信息及其地址列表信息列表")
    @GetMapping("/address")
    public List<UserAddressDTO> listUserAddress() {
        return this.userService.listUserAddress();
    }

    @ApiOperation(value = "保存用户信息")
    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        this.getBaseService().save(user);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }

    @ApiOperation(value = "更新用户信息")
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody User user) {
        this.getBaseService().update(user);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "删除用户信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.getBaseService().remove(id);
        return ResponseEntity.noContent().build();
    }

}
