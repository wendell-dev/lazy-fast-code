package lazy.fast.code.core.orm;

import lazy.fast.code.core.exception.SystemException;

/**
 * 基础Controller
 *
 * @param <T>
 *            不能为空
 * @author wendell
 */
public abstract class BaseController<T extends BaseEntity> {

    private BaseService<T> baseService;

    public BaseController(BaseService<T> baseService) {
        if (null == baseService) {
            throw new SystemException("base-service must be not null");
        }
        this.baseService = baseService;
    }

    /**
     * 获取Service操作对象
     *
     * @return Service操作对象
     */
    protected BaseService<T> getBaseService() {
        return baseService;
    }

}
