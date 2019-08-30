package lazy.fast.code.demo.address;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "Address", description = "地址信息 - entity")
public class Address extends BaseEntity<Long> {

    /**
     * 城市名称
     */
    @Column(name = "city")
    @ApiModelProperty(value = "城市名称")
    private String city;

    /**
     * 详细地址
     */
    @Column(name = "address")
    @ApiModelProperty(value = "详细地址")
    private String address;

    /**
     * 用户信息ID
     */
    @Column(name = "user_id")
    @ApiModelProperty(value = "用户信息ID")
    private Long userId;

}
