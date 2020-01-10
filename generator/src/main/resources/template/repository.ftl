package ${basePackageName}.${moduleName};

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import lazy.fast.code.core.web.orm.BaseRepository;

/**
 * ${classDescription}
 *
 * @author ${author}
 * @date ${.now?string("yyyy-MM-dd HH:mm")}
 */
@Repository
@Mapper
public interface ${className?cap_first}Repository extends BaseRepository<${className?cap_first}> {}