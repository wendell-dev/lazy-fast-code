package lazy.fast.code.core.orm;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * Mybatis通用Mapper基础接口
 *
 * @param <T>
 *            不能为空
 * @author wendell
 */
@RegisterMapper
public interface BaseRepository<T extends BaseEntity> extends Mapper<T>, InsertListMapper<T> {}
