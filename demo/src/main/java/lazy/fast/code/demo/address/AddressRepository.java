package lazy.fast.code.demo.address;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import lazy.fast.code.core.orm.BaseRepository;

/**
 * 地址信息 - repository
 *
 * @author wendell
 */
@Repository
@Mapper
public interface AddressRepository extends BaseRepository<Address> {}
