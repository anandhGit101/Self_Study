/**
 * 
 */
package com.springboot.justbook.searchservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.springboot.justbook.searchservice.domain.Movies;

/**
 * @author M1006601
 *
 */
@Repository
public interface MoviesELKRepository extends ElasticsearchRepository<Movies, Long> {
	
	Movies findMovieByMovieIdAndMovieActiveTrue(Long movieId);
	
	Movies findMovieByMovieId(Long movieId);

	Movies findMovieByMovieTitle(String movieTitle);

	List<Movies> findAllMoviesByMovieActiveTrue();

}
