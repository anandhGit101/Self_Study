/**
 * 
 */
package com.springboot.justbook.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.justbook.management.domain.Movie;

/**
 * @author M1006601
 *
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	Movie findMovieByMovieIdAndIsMovieActiveTrue(Long movieId);

	Movie findMovieByMovieTitleAndIsMovieActiveTrue(String movieTitle);

	@Modifying
	@Query(nativeQuery=true, value="Delete * from movie where movie_id=:id")
	void deleteByMovieId(Long id);

	List<Movie> findByIsMovieActiveTrue();

}
