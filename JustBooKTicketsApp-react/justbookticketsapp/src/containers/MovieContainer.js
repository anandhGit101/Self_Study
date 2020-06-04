import React, { Component } from "react";
import Movie from "../Movie";
import { connect } from "react-redux";
import { fetchMoviesPosts } from "../actions";

export class MovieContainer extends Component {
  constructor(props) {
    super(props);
  }
  handleSchedule(value) {
    var movieTitle = value;
    this.props.history.push(`schedule/${movieTitle}`);
  }
  render() {
    console.log("store in value", this.props.data);
    return (
      <div>
        <div>
          <Movie
            // {...this.props}
            handleSchedule={this.handleSchedule.bind(this)}
          />
        </div>
      </div>
    );
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

export default connect(mapStateToProps)(MovieContainer);
