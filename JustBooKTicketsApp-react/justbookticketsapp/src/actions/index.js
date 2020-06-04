import { ADD_MOVIE_POST, FETCH_MOVIES_POST } from "./types";
import axios from "axios";

const apiMoviesUrl = "http://localhost:8899/jbtMovieMgmt";
const apiCinemasUrl = "http://localhost:8899/jbtMovieMgmt/cinemas";

export const fetchMoviesPosts = posts => {
  console.log("POSTS in actions:", posts);
  return {
    type: FETCH_MOVIES_POST,
    posts: posts
  };
};

export const fetchAllMoviesPosts = () => {
  return dispatch => {
    return axios
      .get(`${apiMoviesUrl}/movies/list`)
      .then(response => {
        dispatch(fetchMoviesPosts(response.data.resultList));
      })
      .catch(error => {
        throw error;
      });
  };
};

export const createPost = ({ title, body }) => {
  return dispatch => {
    return axios
      .post(`${apiMoviesUrl}/movie`, { title, body })
      .then(response => {
        dispatch(createPostSuccess(response.data));
      })
      .catch(error => {
        throw error;
      });
  };
};

export const createPostSuccess = data => {
  return {
    type: ADD_MOVIE_POST,
    payload: {
      _id: data._id,
      title: data.title,
      body: data.body
    }
  };
};

/*export const fetchCinemasPosts = postsCinemas => {
  return {
    type: FETCH_CINEMAS_POST,
    postsCinemas
  };
};

export const fetchAllCinemasPosts = () => {
  return dispatch => {
    return axios
      .get(apiCinemasUrl)
      .then(response => {
        dispatch(fetchCinemasPosts(response.data.resultList));
      })
      .catch(error => {
        throw error;
      });
  };
};*/
