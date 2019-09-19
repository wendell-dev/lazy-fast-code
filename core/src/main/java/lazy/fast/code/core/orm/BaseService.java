package lazy.fast.code.core.orm;

import java.io.Serializable;
import java.util.List;

/**
 * 基础Service接口
 *
 * @param <T>
 *            不能为空
 * @author wendell
 */
public interface BaseService<T extends BaseEntity> {

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     *            实体对象
     * @return 保存成功记录数
     */
    int save(T entity);

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     *            实体对象
     * @return 删除成功记录数
     */
    int update(T entity);

    /**
     * 根据主键字段进行删除
     *
     * @param id
     *            数据主键
     * @return 删除成功记录数
     */
    int remove(Serializable id);

    /**
     * 根据实体属性作为条件进行删除
     *
     * @param entity
     *            实体对象
     * @return 删除成功记录数
     */
    int remove(T entity);

    /**
     * 根据主键查询一个实体
     *
     * @param id
     *            数据主键
     * @return 实体
     */
    T get(Serializable id);

    /**
     * 根据实体中的属性查询一个实体
     *
     * @param entity
     *            实体对象
     * @return 实体
     */
    T get(T entity);

    /**
     * 根据实体中的属性值查询实体集合
     *
     * @param entity
     *            实体对象
     * @return 实体集合
     */
    List<T> list(T entity);

    /**
     * 根据实体中的属性值查询实体分页集合
     * 
     * @param entity
     *            实体对象
     * @param offset
     *            offset
     * @param limit
     *            limit
     * @return 实体集合
     */
    List<T> listPage(T entity, int offset, int limit);

    /**
     * 根据实体中的属性查询总数
     *
     * @param entity
     *            实体对象
     * @return 总数
     */
    int count(T entity);

}
