/**
 * 
 */
package com.springboot.justbook.searchservice.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.springboot.justbook.searchservice.domain.Cinemas;
import com.springboot.justbook.searchservice.domain.Movies;
import com.springboot.justbook.searchservice.vo.GenericVO;
import com.springboot.justbook.searchservice.vo.MoviesVO;
import com.springboot.justbook.searchservice.vo.CinemasVO;

/**
 * @author M1006601
 *
 */
@Component
public class MoviesMapper {
	
	DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

	public List<GenericVO> mapMoviesELKToMoviesELKVOList(List<Movies> moviesELKList) {
		List<GenericVO> moviesELKVOList = new ArrayList<GenericVO>();
		if(null!= moviesELKList) {
			for(Movies movie: moviesELKList) {
				System.out.println(movie.toString());
				moviesELKVOList.add(mapMoviesELKToMoviesELKVO(movie));
			}
		}
		return moviesELKVOList;
	}

	
	private MoviesVO mapMoviesELKToMoviesELKVO(Movies movie) {
		
		MoviesVO movVO = new MoviesVO();
		movVO.setMovieTitle(movie.getMovieTitle());
		movVO.setMovieCast(movie.getMovieCast());
		movVO.setMovieSynopsis(movie.getMovieSynopsis());
		movVO.setMovieLanguage(movie.getMovieLanguage());
		movVO.setMovieGenre(movie.getMovieGenre());
		if(null!=movie.getMovieReleaseDate()) {
		LocalDate releaseDate = movie.getMovieReleaseDate();
		String formattedReleaseDate = releaseDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		movVO.setMovieReleaseDate(formattedReleaseDate);
		}
		movVO.setMovieId(movie.getMovieId());
		movVO.setMovieImageURL(movie.getMovieImageURL());
		movVO.setIsActive(movie.getMovieActive());
		return movVO;
	}

	public Movies mapMoviesELKVOToMoviesELK(@Valid MoviesVO movie) {
		
		Movies mov= new Movies();
		mov.setMovieTitle(movie.getMovieTitle());
		mov.setMovieCast(movie.getMovieCast());
		mov.setMovieSynopsis(movie.getMovieSynopsis());
		mov.setMovieLanguage(movie.getMovieLanguage());
		mov.setMovieGenre(movie.getMovieGenre());
		mov.setMovieReleaseDate(LocalDate.parse(movie.getMovieReleaseDate(), formatter));
		mov.setMovieImageURL(movie.getMovieImageURL());
		return mov;
	}

	public List<GenericVO> mapMoviesELKToMoviesELKVOList(Movies newMovie) {
		
		List<GenericVO> elkVOList = new ArrayList<GenericVO>();
		elkVOList.add(mapMoviesELKToMoviesELKVO(newMovie));
		return elkVOList;
	}

	public List<GenericVO> mapCinemasToCinemasVOList(List<Cinemas> cinemaList) {
		
		List<GenericVO> cinemasELKVOList = new ArrayList<GenericVO>();
		if(null!= cinemaList) {
			for(Cinemas cinemas: cinemaList) {
				cinemasELKVOList.add(mapCinemasELKToCinemasELKVO(cinemas));
			}
		}
		return cinemasELKVOList;
	}

	public CinemasVO mapCinemasELKToCinemasELKVO(Cinemas cinemas) {

		CinemasVO cinemasVO = new CinemasVO();
		cinemasVO.setCinemasId(cinemas.getCinemasId());
		cinemasVO.setCinemasAddress(cinemas.getCinemasAddress());
		cinemasVO.setCinemasName(cinemas.getCinemasName());
		cinemasVO.setCinemasLocation(cinemas.getCinemasLocation());
		return cinemasVO;

	}

	public List<GenericVO> mapCinemasToCinemaVOList(Cinemas cinemasExisting) {
		
		List<GenericVO> cinemasELKVOList = new ArrayList<GenericVO>();
		cinemasELKVOList.add(mapCinemasELKToCinemasELKVO(cinemasExisting));
		return cinemasELKVOList;
	}

	public Cinemas mapCinemasELKVOToCinemas(@Valid CinemasVO cinema) {
		
		Cinemas cinemas = new Cinemas();
		cinemas.setCinemasId(cinema.getCinemasId());
		cinemas.setCinemasName(cinema.getCinemasName());
		cinemas.setCinemasLocation(cinema.getCinemasLocation());
		cinemas.setCinemasAddress(cinema.getCinemasAddress());
		return cinemas;
	}

}
