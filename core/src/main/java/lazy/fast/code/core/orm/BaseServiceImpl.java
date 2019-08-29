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
    protected BaseRepository<T> getRepository() {
        return baseRepository;
    }

    @Override
    public int save(T record) {
        return this.getRepository().insertSelective(record);
    }

    @Override
    public int update(T record) {
        Assert.notNull(record.getId(), "primary-key must be not null");
        return this.getRepository().updateByPrimaryKeySelective(record);
    }

    @Override
    public int remove(Serializable key) {
        Assert.notNull(key, "primary-key must be not null");
        return this.getRepository().deleteByPrimaryKey(key);
    }

    @Override
    public int remove(T record) {
        return this.getRepository().delete(record);
    }

    @Override
    public T get(Serializable key) {
        return this.getRepository().selectByPrimaryKey(key);
    }

    @Override
    public T get(T record) {
        return this.getRepository().selectOne(record);
    }

    @Override
    public List<T> list(T record) {
        return this.getRepository().select(record);
    }

    @Override
    public List<T> listPage(T record, int offset, int limit) {
        return this.getRepository().selectByRowBounds(record, new RowBounds(offset, limit));
    }

    @Override
    public int count(T record) {
        return this.getRepository().selectCount(record);
    }

}
