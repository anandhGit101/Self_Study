import React, { Component } from "react";
import color from "@material-ui/core/colors/red";
import * as Moment from "moment";
import { Redirect } from "react-router-dom";

const baseMovieUrl = "http://localhost:8007/jbtMovieMgmt";

export default class CinemasSchedule extends Component {
  constructor(props) {
    super(props);
    this.state = { scheduleList: [], clicked: false };
  }

  fetchUpcoming() {
    var cinemasId = this.props.match.params.id;
    console.log("cinemasId::::::::" + cinemasId);
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    fetch(`${baseMovieUrl}/schedules/listbycinemasid?cinemasId=${cinemasId}`, {
      method: "GET",
      headers: myHeaders
    })
      .then(response => {
        if (response.ok) {
          response.clone().json();
          return response.text();
        }
      })
      .then(data => {
        // console.log(data);
        const finalResult = JSON.parse(data).resultList;
        console.log(finalResult);
        this.setState({ scheduleList: finalResult });
      });
  }
  componentDidMount() {
    this.fetchUpcoming();
  }

  handleClick = sched => {
    console.log(JSON.stringify(sched));
    sched = JSON.stringify(sched);
    this.setState({ clicked: true, apiData: sched });
  };

  render() {
    var schedulesAvailable = [];
    Object.keys(this.state.scheduleList).map(key => {
      console.log("Value in the list", this.state.scheduleList[key]);
      schedulesAvailable.push(this.state.scheduleList[key]);
    });

    if (this.state.clicked) {
      console.log(this.state.apiData, "SUrvival from cinemas");
      return (
        <Redirect
          to={{
            pathname: "/seats",
            state: { referrer: this.state.apiData }
          }}
        />
      );
    }
    return (
      <center>
        <div>
          <div className="rowCinemas">
            {schedulesAvailable.map((sched, i) => (
              <div className="movie-content">
                <h2 align="left" style={{ paddingLeft: "30px" }}>
                  {sched.movieTitle} ({sched.movieLanguage})
                </h2>
                <h3 align="left" style={{ paddingLeft: "30px" }}>
                  {sched.movieGenre} Released on:{" "}
                  {Moment(sched.movieReleaseDate).format("DD-MM-YYYY")}
                </h3>
                <h2
                  align="left"
                  style={{ paddingLeft: "10px", color: "#b920e8" }}
                >
                  {sched.movieCast}
                </h2>
                <h6 align="left" style={{ paddingLeft: "30px" }}>
                  {sched.showDate}
                </h6>
                {sched.showTimingsList.map(timings => (
                  <button
                    className="button-timing"
                    onClick={() =>
                      this.handleClick({
                        cinemasId: sched.cinemasId,
                        movieId: sched.movieId,
                        showDate: Moment(sched.showDate).format("YYYY-MM-DD"),
                        showTimings: timings,
                        cinemasName: sched.cinemasName,
                        movieName: sched.movieTitle
                      })
                    }
                  >
                    {timings}
                  </button>
                ))}
              </div>
            ))}
          </div>
        </div>
      </center>
    );
  }
}
