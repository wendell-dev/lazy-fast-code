package lazy.fast.code.demo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lazy.fast.code.core.orm.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户信息 - entity
 *
 * @author wendell
 */
@Entity
@Table(name = "sys_user")
@Data(staticConstructor = "of")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "User", description = "用户信息 - entity")
public class User extends BaseEntity<Long> {

    /**
     * 姓名
     */
    @Column(name = "name")
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 年龄
     */
    @Column(name = "age")
    @ApiModelProperty(value = "年龄")
    private Integer age;

}
