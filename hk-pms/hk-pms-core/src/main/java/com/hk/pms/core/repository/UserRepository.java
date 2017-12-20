package com.hk.pms.core.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;

import com.hk.core.repository.StringRepository;
import com.hk.pms.core.domain.User;
import com.hk.pms.core.repository.custom.CustomUserRepository;

/**
 * 
 * @author huangkai
 * @date 2017年9月27日下午2:22:58
 */
public interface UserRepository extends StringRepository<User>, CustomUserRepository {

	/**
	 * JPQL 语法查询，查询字段必须与属性字段相同，不是与数据库表字段
	 * 
	 * @param loginName
	 * @return
	 */
	@Query(value = "SELECT u  FROM User u WHERE u.userName = ?1 OR u.phone = ?1 OR u.email = ?1")
	Optional<User> findUniqueByLoginName(String loginName);

	/**
	 * 使用 Stream 作为返回值类型必须有事物支持
	 * 
	 * @param type
	 * @return
	 */
	Stream<User> findByUserType(Integer type);

	/**
	 *
	 * 使用 List 作为返回值类型可没有事物支持
	 * 
	 * @param orgId
	 * @return
	 */
	List<User> findByOrgId(String orgId);

	/**
	 * 
	 * @param loginName
	 * @return
	 */
	@Query(value = "SELECT count(*)  FROM User u WHERE u.userName = ?1 OR u.phone = ?1 OR u.email = ?1")
	boolean existsByLoginName(String loginName);
}
