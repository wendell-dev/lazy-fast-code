package lazy.fast.code.demo.core;

import io.swagger.annotations.ApiModelProperty;
import lazy.fast.code.core.orm.SnowFlakeGenId;
import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 自定义的BaseEntity
 *
 * @author wendell
 */
@MappedSuperclass
@Setter
@Getter
public abstract class BaseEntity extends lazy.fast.code.core.orm.BaseEntity {

    /**
     * 数据主键 - 自定义生成策略
     */
    @Id
    @KeySql(genId = SnowFlakeGenId.class)
    @Column(name = "id", nullable = false, updatable = false)
    @ApiModelProperty(value = "数据主键")
    private String id;

}
