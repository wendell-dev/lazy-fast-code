package lazy.fast.code.core.web.orm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础Entity
 *
 * <pre>
 *     参考《阿里巴巴Java开发手册》, 表必备三字段
 * </pre>
 *
 * @param <ID>
 *            不能为空 (自定义BaseEntity时没有此限制)
 *
 * @author wendell
 */
@MappedSuperclass
@Setter
@Getter
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    /**
     * 数据主键 - 默认生成策略为自增
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    @ApiModelProperty(value = "数据主键")
    private ID id;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create", updatable = false,
        columnDefinition = " datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    @Column(name = "gmt_modified", insertable = false,
        columnDefinition = " datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtModified;

}
