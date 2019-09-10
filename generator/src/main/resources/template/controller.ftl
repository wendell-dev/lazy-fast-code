package ${basePackageName}.${moduleName};

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lazy.fast.code.core.orm.BaseController;
import lazy.fast.code.core.orm.BaseService;
import org.springframework.http.ResponseEntity;
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
 * ${classDescription}
 *
 * @author ${author}
 * @date ${.now?string("yyyy-MM-dd HH:mm")}
 */
@Api(tags = "${classDescription}")
@RestController
@RequestMapping(value = "${requestMapping}")
public class ${className?cap_first}Controller extends BaseController<${className?cap_first}> {

    public ${className?cap_first}Controller(BaseService<${className?cap_first}> baseService) {
        super(baseService);
    }

    @ApiOperation(value = "获取${classDescription}列表")
    @GetMapping
    public List<${className?cap_first}> list(${className?cap_first} ${className?uncap_first}) {
        return this.getBaseService().list(${className?uncap_first});
    }

    @ApiOperation(value = "根据数据主键获取${classDescription}")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "数据主键", required = true, paramType = "path")})
    @GetMapping("/{id}")
    public ${className?cap_first} get(@PathVariable String id) {
        return this.getBaseService().get(id);
    }

    @ApiOperation(value = "保存${classDescription}")
    @PostMapping
    public ResponseEntity<${className?cap_first}> save(@RequestBody ${className?cap_first} ${className?uncap_first}) {
        this.getBaseService().save(${className?uncap_first});
        return ResponseEntity.created(URI.create("${requestMapping}/" + ${className?uncap_first}.getId())).body(${className?uncap_first});
    }

    @ApiOperation(value = "更新${classDescription}")
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody ${className?cap_first} ${className?uncap_first}) {
        this.getBaseService().update(${className?uncap_first});
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "删除${classDescription}")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "数据主键", required = true, paramType = "path")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        this.getBaseService().remove(id);
        return ResponseEntity.noContent().build();
    }

}