import React, { Component } from "react";
import axios from "axios";
import { QRCode } from "react-qrcode-logo";

import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import successImage from "./assets/filmypass.jpg";
import { ACCESS_TOKEN } from "./constants";
import Alert from "react-s-alert";
import {
  Card,
  CardImg,
  CardText,
  CardBody,
  CardTitle,
  CardSubtitle
} from "reactstrap";

const baseBookingUrl = "http://localhost:8899/jbtBookingMgmt";
export default class TickerSummary extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      alert: false,
      response: {}
    };
  }

  handleLogout() {
    localStorage.removeItem(ACCESS_TOKEN);
    this.setState({
      authenticated: false,
      currentUser: null
    });
    Alert.success("You're safely logged out!");
  }

  handleBooking = bookingInfo => {
    console.log(bookingInfo);
    bookingInfo.seatsSelected = bookingInfo.seatsSelected.toString();
    console.log(bookingInfo, "Array");
    axios({
      method: "post",
      url: `${baseBookingUrl}/booktickets`,
      data: JSON.stringify(bookingInfo),
      headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*"
      }
    }).then(response => {
      if (response !== null) {
        console.log(response, "ticketSummary");
        this.setState({ alert: true, response: response });
        //  this.bookingSuccess(response);
      }
    });
  };

  // bookingSuccess = response => {
  //   return (

  //   );
  // };
  // handleContinue() {
  //   return (
  //     <Redirect
  //       to={{
  //         pathname: "/"
  //       }}
  //     />
  //   );
  // }
  render() {
    this.bookingDetils = this.props.location.state.refer;
    console.log(this.bookingDetils, "Summary");
    var summaryDetails = this.bookingDetils;
    var internetCharges =
      (Math.round(31.65 * summaryDetails.seatsSelected.length) * 100) / 100;
    var total =
      Math.round((summaryDetails.totalAmount + internetCharges) * 100) / 100;
    var userName = "";
    var phoneNo = "";
    if (this.state.currentUser != null) {
      userName = this.state.currentUser.name;
      phoneNo = this.state.currentUser.contactNo;
    } else if (this.props.user != null) {
      userName = this.props.user.name;
      phoneNo = this.props.user.contactNo;
    } else if (this.props.name != null) {
      userName = this.props.name;
      phoneNo = this.props.contactNo;
    }
    return (
      <div>
        <div className="app-options">
          <nav className="app-nav">
            {this.props.authenticated ? (
              <ul>
                <li>{userName}</li>
                <li>
                  <button onClick={() => this.handleLogout()}>Logout</button>
                </li>
              </ul>
            ) : (
              ""
            )}
          </nav>
        </div>
        <div className="bg">
          <h4>
            <table>
              <tbody>
                <tr className="info">
                  <td>Theatre Details: </td>
                  <td>{summaryDetails.cinemasName} </td>
                </tr>
                <tr className="info">
                  <td>Movie Details: </td>
                  <td>{summaryDetails.movieName} </td>
                </tr>
                <tr className="info">
                  <td>Seat Category: </td>
                  <td>{summaryDetails.seatsCategory} </td>
                </tr>
                <tr className="info">
                  <td>Seats Selected:</td>
                  <td>
                    {summaryDetails.seatsSelected.map((e, k) => {
                      return (
                        <React.Fragment>
                          {e}
                          {k !== summaryDetails.seatsSelected.length - 1
                            ? ","
                            : ""}
                        </React.Fragment>
                      );
                    })}
                  </td>
                </tr>

                <tr className="info">
                  <td>Date: </td>
                  <td>{summaryDetails.showDate} </td>
                </tr>
                <tr className="info">
                  <td>Timings: </td>
                  <td>{summaryDetails.showTimings} </td>
                </tr>
                <tr className="info">
                  <td> Internet Charges: </td>
                  <td>₹{internetCharges}</td>
                </tr>

                <tr className="info">
                  <td> Total to be Paid: </td>
                  <td>₹{total}</td>
                </tr>
              </tbody>
            </table>
          </h4>
          <div>
            <QRCode className="QR" value={JSON.stringify(summaryDetails)} />
            <button
              className="checkOut"
              onClick={() =>
                this.handleBooking({
                  showDate: summaryDetails.showDate,
                  showTimings: summaryDetails.showTimings,
                  showCinemasName: summaryDetails.cinemasName,
                  showMovieName: summaryDetails.movieName,
                  seatsSelected: summaryDetails.seatsSelected,
                  seatCategory: summaryDetails.seatsCategory,
                  totalAmount: total,
                  userName: userName,
                  userPhoneNumber: phoneNo
                })
              }
            >
              Proceed to Checkout....
            </button>
            {this.state.alert ? (
              <Dialog
                open={this.state.alert}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
              >
                <DialogContent>
                  <DialogContentText id="alert-dialog-description">
                    <Card>
                      <CardBody>
                        <CardTitle>*****--Success--*****</CardTitle>
                        <CardSubtitle>
                          Your ticket booked successfully...
                        </CardSubtitle>
                        <CardImg
                          top
                          width="100%"
                          src={successImage}
                          alt="Card image cap"
                        />
                        <CardText>
                          Your Movie ticket for the movie{" "}
                          {this.state.response.data.resultList[0].showMovieName}{" "}
                          is booked with seat(s){" "}
                          {this.state.response.data.resultList[0].seatsSelected}{" "}
                          and booking number::{" "}
                          {this.state.response.data.resultList[0].bookingNumber}
                          . Kindly pay{" "}
                          {this.state.response.data.resultList[0].totalPrice} at
                          the Counter.
                        </CardText>
                      </CardBody>
                    </Card>
                  </DialogContentText>
                </DialogContent>
                <DialogActions>
                  <a href="/Movies">close</a>
                </DialogActions>
              </Dialog>
            ) : (
              ""
            )}
          </div>
        </div>
      </div>
    );
  }
}
