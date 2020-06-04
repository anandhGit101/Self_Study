import React from "react";
import { Link } from "react-router-dom";
import Container from "@material-ui/core/Container";

export default class AddMovie extends React.Component {
  constructor(props) {
    console.log(props, "In AddMovie Page");
    super(props);

    this.state = {
      movie:{
        movieTitle="",
        movieLanguage="",
        movieCast="",
        movieGenre="",
        movieImg="",
        movieSynopsis="",
        movieActive:false
      }
    }
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    const { name, value } = event.target;
    const { movie } = this.state;
    this.setState({
      movie: {
        ...movie,
        movieActive: true,
        [name]: value
      }
    });
  }

  handleSubmit(e) {
    e.preventDefault();

    const addMovieRequest = Object.assign({}, this.state.movie);
    addMovie(addMovieRequest)
      .then(response => {
        Alert.success(
          "Movie added successfully.!"
        );
        this.props.history.push("/addMovie");
      })
      .catch(error => {
        Alert.error(
          (error && error.message) ||
            "Oops! Something went wrong. Please try again!"
        );
      });
  }
  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <Container fixed>
          <div>
            <label htmlFor="movieTitle">Movie Title: </label>
            <input
              type="text"
              name="movieTitle"
              value={this.state.movieTitle}
              onChange={this.handleChange}
            />
          </div>
          <div>
            <label htmlFor="movieLanguage">Movie Language: </label>
            <input
              type="text"
              name="movieLanguage"
              value={this.state.movieLanguage}
              onChange={this.handleChange}
            />
          </div>
          <div>
            <label htmlFor="movieGenre">Movie Genre: </label>
            <input
              type="text"
              name="movieGenre"
              value={this.state.movieGenre}
              onChange={this.handleChange}
            />
          </div>
          <div>
            <label htmlFor="movieCast">Movie Cast: </label>
            <input
              type="text"
              name="movieCast"
              value={this.state.movieCast}
              placeholder="Enter Casts with comma separated"
              onChange={this.handleChange}
            />
          </div>
          <div>
            <label htmlFor="movieSynopsis">Movie Synopsis: </label>
            <input
              type="text"
              name="movieSynopsis"
              value={this.state.movieSynopsis}
              placeholder="Enter short synopsis here"
              onChange={this.handleChange}
            />
          </div>
          <div>
            <label htmlFor="movieImg">Movie Image: </label>
            <input
              type="text"
              name="movieImg"
              value={this.state.movieImg}
              placeholder="Enter short synopsis here"
              onChange={this.handleChange}
            />
          </div>
          <button type="submit" className="btn btn-block btn-primary">
            Add Movie
          </button>
          <button className="btn btn-block btn-primary">Clear</button>
          <Link to="/" className="btn btn-link">
            Cancel
          </Link>
        </Container>
      </form>
    );
  }
}
