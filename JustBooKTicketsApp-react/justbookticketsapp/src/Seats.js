import React, { Component } from "react";
import Chip from "@material-ui/core/Chip";
import FaceIcon from "@material-ui/icons/Face";
import axios from "axios";
import screenImg from "./assets/screen.jpg";
import { Redirect } from "react-router-dom";
import Header from "./header/Header";
import Alert from "react-s-alert";

const baseMovieUrl = "http://localhost:8007/jbtMovieMgmt";

export default class Seats extends Component {
  constructor(props) {
    console.log(props, "***Props***");
    super(props);
    this.state = {
      menuItems1: [],
      menuItems: [],
      seats: [],
      selectedSeats: [],
      scheduleId: "",
      bookedSeats: [],
      total: 0.0,
      clicked: false,
      seatsCategory: ""
    };
    this.scheduleDetails = this.props.location.state.referrer;
  }

  // static contextType = ApiData;

  fetchSeatsTotal = seatsOccupiedArray => {
    console.log(seatsOccupiedArray, "Pre-Occupied Seats");
    fetch(`${baseMovieUrl}/seats/list`)
      .then(response => response.json())
      .then(data => {
        console.log(data.resultList);
        this.setState({
          seats: data.resultList
        });
        var seatsList = data.resultList;
        var menuItems = [];
        var menuItems1 = [];
        for (var i = 0; i < seatsList.length; i++) {
          menuItems.push(seatsList[i]);
          if (i === 29) break;
        }
        for (i = 30; i < seatsList.length; i++) {
          menuItems1.push(seatsList[i]);
        }

        this.setState({ menuItems1, menuItems });
        //this.setState({ seatsOccupiedArray });
        console.log(menuItems1, menuItems);
      });
  };

  getScheduleId = () => {
    return axios({
      method: "post",
      url: `${baseMovieUrl}/schedules/showdetails`,
      data: this.scheduleDetails,
      headers: {
        "Content-Type": "application/json"
      }
    }).then(response => {
      return response.data;
    });
  };
  existingBookedSeats = () => {
    var seatsOccupiedVal = [];
    this.getScheduleId().then(result1 => {
      if (result1 !== 0) {
        axios({
          method: "get",
          url: `${baseMovieUrl}/seatsschedule/booked?scheduleId=${result1}`
        }).then(response => {
          console.log(response, "response");
          if (
            response != null &&
            response.data.resultList[0].seatsOccupied != null
          ) {
            seatsOccupiedVal.push(
              response.data.resultList[0].seatsOccupied.split(",")
            );

            console.log(seatsOccupiedVal, "if");
          }

          this.setState({
            scheduleId: result1,
            seatsOccupiedArray: seatsOccupiedVal
          });
          this.fetchSeatsTotal(seatsOccupiedVal);
        });
        // console.log(seatsOccupiedVal, "occupied seats");
      }
    });
    //console.log("Inside existingBookedSeats Step 1", schedId);

    // console.log(this.state.scheduleId, "Inside existingBookedSeats");
  };

  componentDidMount() {
    if (!this.props.authenticated) {
      console.log(this.props.authenticated, "lll");
      return (
        /*<Redirect
          to={{
            pathname: "/login",
            state: { from: this.props.location }
          }}
        />*/
        this.props.history.push("/login")
      );
    } else {
      this.existingBookedSeats();
    }
    //if()
  }
  componentDidUpdate() {
    if (this.state.scheduleId) {
      console.log("hello");
    }
  }
  handleClick = seat => {
    var tot = this.state.total;
    var arr = this.state.selectedSeats;
    if (arr.indexOf(seat.seatNumber) === -1) {
      console.log(seat.seatNumber);
      console.log(parseFloat(seat.unitPrice));
      this.setState({
        selectedSeats: this.state.selectedSeats.concat(seat.seatNumber),
        seatsCategory: seat.seatType
      });
      alert("You clicked the Chip." + seat.seatNumber + "HHHH" + seat.seatType);
      this.total = tot + seat.unitPrice;
      this.total = Math.round(this.total * 100) / 100;
      console.log("total", this.total);
    }

    this.setState({ total: this.total });
  };

  handleDelete = seat => {
    var tot = this.state.total;
    alert("You clicked the delete part of Chip.");
    var array = this.state.selectedSeats;
    var index = array.indexOf(seat.seatNumber);
    if (index !== -1) {
      array.splice(index, 1);
      console.log(array, "arr dle");
      this.setState({ selectedSeats: array });
      this.total = tot - seat.unitPrice;
      this.total = Math.round(this.total * 100) / 100;
      this.setState({ total: this.total });
    }
  };

  checkDisable = (seat, seatArr) => {
    var disable = false;
    if (seatArr[0] != null && seatArr[0].length > 0) {
      var index = seatArr[0].indexOf(seat.seatNumber);
      if (index !== -1) {
        disable = true;
      }
    }
    return disable;
  };

  viewSummary = bookingDetails => {
    this.setState({
      clicked: true,
      bookingsummary: bookingDetails
    });
  };
  render() {
    var bookingDetails = {};

    bookingDetails = JSON.parse(this.props.location.state.referrer);
    //console.log(this.props && this.props.location.state.referrer, "properties");

    if (this.state.clicked) {
      return (
        <Redirect
          to={{
            pathname: "/ticketsummary",
            state: { refer: this.state.bookingsummary }
          }}
        />
      );
    }
    return (
      <div>
        <div className="headerButton">
          <Header {...this.props} {...this.props} />
        </div>
        <div>
          <div className="header-container">
            <h2>
              <sub className="sub-details">Movie:</sub>
              {bookingDetails.movieName}
              <sub className="sub-details">Theatre:</sub>
              {bookingDetails.cinemasName}
            </h2>
            <h4>
              <sub className="sub-details">Date:</sub>
              {bookingDetails.showDate}
              <sub className="sub-details">Time:</sub>
              {bookingDetails.showTimings}
            </h4>
          </div>

          <div className="row">
            <div className="movie-category">
              First Class - Rs. 183.65 <sub>(Inclusive of all taxes)</sub>
            </div>

            <div className="seat-premium-content" style={{ marginTop: "30px" }}>
              {this.state.menuItems.map((seat, key) => {
                var isDisable = this.checkDisable(
                  seat,
                  this.state.seatsOccupiedArray
                );
                console.log(isDisable);
                return (
                  <div
                    className="col-sm-1"
                    style={{ marginBottom: "20px", marginTop: "10px" }}
                  >
                    <Chip
                      title={seat.unitPrice}
                      id="seatChip"
                      variant="outlined"
                      size="small"
                      color="default"
                      onClick={() => this.handleClick(seat)}
                      icon={<FaceIcon />}
                      disabled={isDisable}
                      label={seat.seatNumber}
                      onDelete={() => this.handleDelete(seat)}
                    />
                  </div>
                );
              })}
            </div>
            <div className="movie-category">
              Budget Class - Rs. 75.65 <sub>(Inclusive of all taxes)</sub>
            </div>

            <div
              className="seat-content"
              style={{ marginTop: "30px", marginBottom: "40px" }}
            >
              {this.state.menuItems1.map((seat, key) => {
                var isDisable = this.checkDisable(
                  seat,
                  this.state.seatsOccupiedArray
                );
                return (
                  <div
                    className="col-sm-1"
                    style={{ marginBottom: "20px", marginTop: "10px" }}
                  >
                    <Chip
                      id="seatChip"
                      title={seat.unitPrice}
                      variant="outlined"
                      size="small"
                      color="default"
                      onClick={() => this.handleClick(seat)}
                      icon={<FaceIcon />}
                      label={seat.seatNumber}
                      disabled={isDisable}
                      onDelete={() => this.handleDelete(seat)}
                    />
                  </div>
                );
              })}
            </div>
          </div>
          <div className="screenThisWay">
            <img alt="Screen" src={screenImg} />
            <br />
            All Heads This Way...
            <button
              className="payment"
              style={{ backgroundColor: "coral" }}
              onClick={() => {
                if (this.state.selectedSeats.length !== 0) {
                  this.viewSummary({
                    showDate: bookingDetails.showDate,
                    showTimings: bookingDetails.showTimings,
                    cinemasName: bookingDetails.cinemasName,
                    movieName: bookingDetails.movieName,
                    seatsSelected: this.state.selectedSeats,
                    seatsCategory: this.state.seatsCategory,
                    totalAmount: this.state.total
                  });
                } else {
                  alert("Select a seat to proceed with booking");
                }
              }}
            >
              Pay ₹ {this.state.total}
            </button>
          </div>
          <div></div>
        </div>
      </div>
    );
  }
}
