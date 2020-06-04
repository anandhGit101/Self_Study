import React, { Component } from "react";

import { Nav, Navbar } from "react-bootstrap";
import "./App.css";
import NavigationBar from "./NavigationBar";
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";
import Login from "./header/Login";
import Movie from "./Movie";
import MovieContainer from "./containers/MovieContainer";
import Cinemas from "./Cinemas";
import CinemasSchedule from "./CinemasSchedule";
import Register from "./header/Register";
import Schedule from "./Schedule";
import Seats from "./Seats";
import TicketSummary from "./TicketSummary";
import OAuth2RedirectHandler from "./OAuth2RedirectHandler";
import { getCurrentUser } from "./AppUtil";
import { ACCESS_TOKEN } from "./constants";
import Alert from "react-s-alert";

import Header from "./header/Header";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      authenticated: false,
      currentUser: null,
      loading: false
    };

    this.loadCurrentlyLoggedInUser = this.loadCurrentlyLoggedInUser.bind(this);
    this.handleLogout = this.handleLogout.bind(this);
  }

  loadCurrentlyLoggedInUser() {
    //console.log(this.state.authenticated, "Current User");
    //console.log(this.props, "Property");
    this.setState({
      loading: true
    });

    getCurrentUser()
      .then(response => {
        console.log(response, "Current User Response");
        this.setState({
          currentUser: response,
          authenticated: true,
          loading: false
        });
      })
      .catch(error => {
        this.setState({
          loading: false
        });
      });
  }

  handleLogout() {
    localStorage.removeItem(ACCESS_TOKEN);
    this.setState({
      authenticated: false,
      currentUser: null
    });
    Alert.success("You're safely logged out!");
  }

  componentDidMount() {
    this.loadCurrentlyLoggedInUser();
  }

  render() {
    return (
      <React.Fragment>
        <Navbar bg="dark" variant="dark" expand="lg">
          <Navbar.Brand>
            <h1>Just Book Tickets.....</h1>
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="mr-auto">
              <Nav.Link href="/Movies">Movies</Nav.Link>
              <Nav.Link href="/Cinemas">Cinemas</Nav.Link>
            </Nav>
            {/* <NavigationBar
              authenticated={this.state.authenticated}
              {...this.state.currentUser}
            /> */}
          </Navbar.Collapse>
        </Navbar>
        <Router>
          <Switch>
            <Route
              exact
              path="/"
              render={props => (
                <NavigationBar
                  authenticated={this.state.authenticated}
                  {...this.state.currentUser}
                />
              )}
            />
            <Route exact path="/Movies" component={MovieContainer} />

            <Route
              exact
              path="/Cinemas"
              render={props => (
                <Cinemas
                  authenticated={this.state.authenticated}
                  {...this.state.currentUser}
                />
              )}
            />
            <Route
              path="/login"
              render={props => (
                <Login
                  authenticated={this.state.authenticated}
                  user={this.state.currentUser}
                />
              )}
            />
            <Route
              path="/register"
              render={props => (
                <Register
                  authenticated={this.state.authenticated}
                  user={this.state.currentUser}
                />
              )}
            />
            <Route
              path="/schedule/:id"
              render={props => (
                <Schedule
                  authenticated={this.state.authenticated}
                  user={this.state.currentUser}
                />
              )}
            />
            <Route
              path="/cinemasschedule/:id"
              render={props => (
                <CinemasSchedule
                  authenticated={this.state.authenticated}
                  user={this.state.currentUser}
                />
              )}
            />
            <Route
              path="/seats"
              render={props => (
                <Seats
                  authenticated={this.state.authenticated}
                  user={this.state.currentUser}
                />
              )}
            />
            <Route
              path="/ticketsummary"
              render={props => (
                <TicketSummary
                  authenticated={this.state.authenticated}
                  user={this.state.currentUser}
                />
              )}
            />
            <Route path="/oauth2/redirect" component={OAuth2RedirectHandler} />
          </Switch>
        </Router>
      </React.Fragment>
    );
  }
}

export default App;
