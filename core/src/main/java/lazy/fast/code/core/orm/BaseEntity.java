package lazy.fast.code.core.orm;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 基础Entity
 *
 * @author wendell
 */
@MappedSuperclass
@Setter
@Getter
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    /**
     * 主键ID
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    protected ID id;

}
