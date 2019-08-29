package lazy.fast.code.demo.user;

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
 * @date 2019-08-28 11:44
 */
@Entity
@Table(name = "sys_user")
@Data(staticConstructor = "of")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity<Long> {

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 年龄
     */
    @Column(name = "age")
    private Integer age;

}
