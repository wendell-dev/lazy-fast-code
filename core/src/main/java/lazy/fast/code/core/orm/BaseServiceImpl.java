package lazy.fast.code.core.orm;

import lazy.fast.code.core.exception.SystemException;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.mapper.util.Assert;

import java.io.Serializable;
import java.util.List;

/**
 * 基础Service接口实现类
 *
 * @param <T>
 *            不能为空
 * @author wendell
 */
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    private BaseRepository<T> baseRepository;

    public BaseServiceImpl(BaseRepository<T> baseRepository) {
        if (null == baseRepository) {
            throw new SystemException("base-repository must be not null");
        }
        this.baseRepository = baseRepository;
    }

    /**
     * 获取Repository操作对象
     * 
     * @return Repository操作对象
     */
    protected BaseRepository<T> getBaseRepository() {
        return baseRepository;
    }

    @Override
    public int save(T entity) {
        return this.getBaseRepository().insertSelective(entity);
    }

    @Override
    public int update(T entity) {
        Assert.notNull(entity.getId(), "id must be not null");
        return this.getBaseRepository().updateByPrimaryKeySelective(entity);
    }

    @Override
    public int remove(Serializable id) {
        Assert.notNull(id, "id must be not null");
        return this.getBaseRepository().deleteByPrimaryKey(id);
    }

    @Override
    public int remove(T entity) {
        return this.getBaseRepository().delete(entity);
    }

    @Override
    public T get(Serializable id) {
        return this.getBaseRepository().selectByPrimaryKey(id);
    }

    @Override
    public T get(T entity) {
        return this.getBaseRepository().selectOne(entity);
    }

    @Override
    public List<T> list(T entity) {
        return this.getBaseRepository().select(entity);
    }

    @Override
    public List<T> listPage(T entity, int offset, int limit) {
        return this.getBaseRepository().selectByRowBounds(entity, new RowBounds(offset, limit));
    }

    @Override
    public int count(T entity) {
        return this.getBaseRepository().selectCount(entity);
    }

}
