import React, { Component } from "react";
import * as Moment from "moment";
import { Redirect } from "react-router-dom";

const baseMovieUrl = "http://localhost:8007/jbtMovieMgmt";

const ApiData = React.createContext();

export default class Schedule extends Component {
  constructor(props) {
    super(props);
    this.state = { scheduleList: [], apiData: {}, clicked: false };
  }

  fetchUpcoming() {
    console.log("eereeerr", this.props);
    var movieName = this.props.match.params.id;
    console.log("uuuuuuuuu::::::::" + movieName);
    //var movieName = sessionStorage.getItem("movieName");
    fetch(`${baseMovieUrl}/schedules/listByName?movieName=${movieName}`)
      .then(response => response.json())
      .then(data => {
        this.setState({
          scheduleList: data.resultList
        });
      });
  }

  handleClick = sched => {
    console.log("Ikkadaunnanu");

    console.log(JSON.stringify(sched));
    sched = JSON.stringify(sched);
    this.setState({ clicked: true, apiData: sched });
  };

  componentDidMount() {
    this.fetchUpcoming();
  }

  render() {
    //this.state.result.map(schedule => console.log(schedule));
    console.log("eeeeeeeeee" + JSON.stringify(this.state.scheduleList));
    var schedulesAvailable = [];
    Object.keys(this.state.scheduleList).map(key => {
      console.log(this.state.scheduleList[key]);
      schedulesAvailable.push(this.state.scheduleList[key]);
    });

    if (this.state.clicked) {
      console.log(this.state.apiData, "SUrvival");
      return (
        <Redirect
          to={{
            pathname: "/seats",
            state: { referrer: this.state.apiData }
          }}
        />
      );
    }
    //console.log(schedulesAvaiable);
    return (
      <ApiData.Provider value="data">
        <center>
          <div>
            <div className="row">
              {schedulesAvailable.map((sched, i) => (
                <div className="movie-content">
                  <h3 align="left" style={{ paddingLeft: "30px" }}>
                    {sched.cinemasName}
                  </h3>
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
      </ApiData.Provider>
    );
  }
}
