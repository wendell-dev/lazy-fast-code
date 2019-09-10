package ${basePackageName}.${moduleName};

import org.springframework.stereotype.Service;

import lazy.fast.code.core.orm.BaseRepository;
import lazy.fast.code.core.orm.BaseServiceImpl;

/**
 * ${classDescription}
 *
 * @author ${author}
 * @date ${.now?string("yyyy-MM-dd HH:mm")}
 */
@Service
public class ${className?cap_first}ServiceImpl extends BaseServiceImpl<${className?cap_first}> implements ${className?cap_first}Service {

    public ${className?cap_first}ServiceImpl(BaseRepository<${className?cap_first}> baseRepository) {
        super(baseRepository);
    }

}
