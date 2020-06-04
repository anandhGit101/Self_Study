import React, { Component } from "react";
import axios from "axios";
import Card from "@material-ui/core/Card";
import CardMedia from "@material-ui/core/CardMedia";
import Button from "@material-ui/core/Button";
import { connect } from "react-redux";
import Header from "./header/Header";
import { fetchAllMoviesPosts, fetchAllCinemasPosts } from "./actions";
var movieTitle = "";
const baseMovieUrl = "http://localhost:8899/jbtMovieMgmt";

export class Movie extends Component {
  constructor(props) {
    super(props);
    //this.state = { result: [], movieName: "", isAuthenticated: false };
    //this.handleSchedule = this.handleSchedule.bind(this);
  }
  componentDidMount() {
    if (localStorage.getItem("accessToken")) {
      this.setState({
        isAuthenticated: true,
      });
    }
    /*axios.get(`${baseMovieUrl}/movies/list`).then(data => {
      console.log(data.data.resultList);
      this.setState({ result: data.data.resultList });
    });*/
  }
  /*mapDispatchToProps = dispatch => {
    return {
      onFetchMovies: post => {
        dispatch(fetchAllMoviesPosts(post));
      },
      onFetchCinemas: post => {
        dispatch(fetchAllCinemasPosts(post));
      }
    };
  };*/

  handleSchedule = (value) => {
    movieTitle = value;
    this.props.history.push(`schedule/${movieTitle}`);
  };
  render() {
    console.log(this.props.data, "IN MOVIE PAGE");
    //return this.state.result.map(movies => {
    return (
      <form>
        <div>
          <Header />
          <div className="row">
            {this.props.data.map((movie) => (
              <div className="card col-lg-3">
                <Card>
                  <div>{movie.movieTitle}</div>
                  <CardMedia
                    className="media"
                    component="img"
                    alt={movie.movieTitle}
                    image={movie.imageURL}
                  />
                  {movie.movieGenre} | {movie.movieLanguage}
                </Card>
                <Button
                  id="scheduler"
                  onClick={() => this.handleSchedule(movie.movieTitle)}
                >
                  Schedules
                </Button>
              </div>
            ))}
          </div>
        </div>
      </form>
    );
    //});
  }
}
const mapStateToProps = (state) => ({
  data: state.storePosts.data,
});

/*const mapDispatchToProps = dispatch => {
  return {
    postsMovies: posts => {
      dispatch(fetchMoviesPosts());
    }
  };
};*/

export default connect(mapStateToProps)(Movie);
