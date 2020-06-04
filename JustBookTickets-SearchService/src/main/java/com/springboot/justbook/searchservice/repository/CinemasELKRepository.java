/**
 * 
 */
package com.springboot.justbook.searchservice.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.springboot.justbook.searchservice.domain.Cinemas;

/**
 * @author M1006601
 *
 */
@Repository
public interface CinemasELKRepository extends ElasticsearchRepository<Cinemas, Long> {

	Cinemas findCinemasByCinemasId(Long cinemasId);

	Cinemas findCinemasByCinemasName(String cinemasName);

	List<Cinemas> findCinemasByCinemasLocation(String cinemasLocation);
	
}
