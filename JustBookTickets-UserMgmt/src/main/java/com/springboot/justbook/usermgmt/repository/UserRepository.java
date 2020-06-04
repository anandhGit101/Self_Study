/**
 * 
 */
package com.springboot.justbook.usermgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.justbook.usermgmt.domain.User;

/**
 * @author M1006601
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserName(String userName);

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

}
