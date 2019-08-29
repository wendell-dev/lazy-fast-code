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
     * @param record
     * @return
     */
    int save(T record);

    /**
     * 根据主键更新属性不为null的值
     *
     * @param record
     * @return
     */
    int update(T record);

    /**
     * 根据主键字段进行删除
     *
     * @param key
     * @return
     */
    int remove(Serializable key);

    /**
     * 根据实体属性作为条件进行删除
     *
     * @param record
     * @return
     */
    int remove(T record);

    /**
     * 根据主键字段进行查询
     *
     * @param key
     * @return
     */
    T get(Serializable key);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常
     *
     * @param record
     * @return
     */
    T get(T record);

    /**
     * 根据实体中的属性值进行查询
     *
     * @param record
     * @return
     */
    List<T> list(T record);

    /**
     * 根据实体属性和RowBounds进行分页查询
     * 
     * @param record
     * @param offset
     * @param limit
     * @return
     */
    List<T> listPage(T record, int offset, int limit);

    /**
     * 根据实体中的属性查询总数
     *
     * @param record
     * @return
     */
    int count(T record);

}
