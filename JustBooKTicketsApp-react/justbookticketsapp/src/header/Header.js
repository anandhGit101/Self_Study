import React from "react";
import { Link } from "react-router-dom";
import { ACCESS_TOKEN } from "../constants";
import Alert from "react-s-alert";
import { getCurrentUser } from "../AppUtil";

export default class Header extends React.Component {
  constructor(props) {
    super(props);
    //console.log(props, "Header level details");
    this.state = {
      currentUser: null,
      loading: false
    };

    this.loadCurrentlyLoggedInUser = this.loadCurrentlyLoggedInUser.bind(this);
    this.handleLogout = this.handleLogout.bind(this);
  }

  loadCurrentlyLoggedInUser() {
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
  componentDidMount() {
    this.loadCurrentlyLoggedInUser();
  }
  handleLogout() {
    localStorage.removeItem("accessToken");
    this.setState({
      authenticated: false,
      currentUser: null
    });
    Alert.success("You're safely logged out!");
  }

  render() {
    var userName = "";
    if (this.state.currentUser != null) {
      userName = this.state.currentUser.name;
    } else if (this.props.user != null) {
      userName = this.props.user.name;
    } else if (this.props.name != null) {
      userName = this.props.name;
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
              <ul>
                <Link to="/register">
                  <button className="btn-sm btn btn-primary">Sign Up</button>
                </Link>
                <Link to="/login">
                  <button className="btn-sm btn btn-secondary">Login</button>
                </Link>
              </ul>
            )}
          </nav>
        </div>
      </div>
    );
  }
}
