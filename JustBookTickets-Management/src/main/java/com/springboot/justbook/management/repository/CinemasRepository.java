/**
 * 
 */
package com.springboot.justbook.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.justbook.management.domain.Cinemas;

/**
 * @author M1006601
 *
 */
@Repository
@Transactional
public interface CinemasRepository extends JpaRepository<Cinemas, Long>{

	List<Cinemas> findAllByCinemasLocation(String cinemasLocation);

	@Query(nativeQuery=true, value="Select * from cinemas where cinemas_name=:cinemasName")
	List<Cinemas> findByCinemasName(String cinemasName);

	List<Cinemas> findByCinemasLocation(String cinemasLocation);

	Cinemas findCinemasByCinemasId(Long cinemasId);

	Cinemas findCinemasByCinemasName(String cinemasName);

	@Modifying
	@Query(nativeQuery=true, value="Delete * from cinemas where cinemas_id=:id")
	void deleteByCinemasId(Long id);

}
