package lazy.fast.code.demo.address;

import lazy.fast.code.core.orm.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 地址信息 - entity
 *
 * @author wendell
 */
@Entity
@Table(name = "address")
@Data(staticConstructor = "of")
@EqualsAndHashCode(callSuper = true)
public class Address extends BaseEntity<Long> {

    /**
     * 城市名称
     */
    @Column(name = "city")
    private String city;

    /**
     * 详细地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 用户信息ID
     */
    @Column(name = "user_id")
    private Long userId;

}
