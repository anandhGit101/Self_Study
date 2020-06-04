import React from "react";
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { Redirect } from "react-router-dom";
import fbLogo from "../assets/fb-logo.png";
import { login } from "../AppUtil.js";
import { NavigationBar } from "../NavigationBar";
import googleLogo from "../assets/google-logo.png";
import githubLogo from "../assets/github-logo.png";
import loginImg from "../assets/movie-ticketing.jpg";
import {
  GOOGLE_AUTH_URL,
  FACEBOOK_AUTH_URL,
  GITHUB_AUTH_URL,
  ACCESS_TOKEN,
  API_BASE_URL
} from "../constants";
import Alert from "react-s-alert";

class Login extends React.Component {
  constructor(props) {
    console.log(props, "In Page");
    super(props);

    //this.props.logout();

    this.state = {
      email: "",
      password: ""
    };

    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentDidMount() {
    // If the OAuth2 login encounters an error, the user is redirected to the /login page with an error.
    // Here we display the error and then remove the error query parameter from the location.
    console.log(this.props.location, "A Props");
    if (this.props.location.state && this.props.location.state.error) {
      setTimeout(() => {
        Alert.error(this.props.location.state.error, {
          timeout: 5000
        });
        this.props.history.replace({
          pathname: this.props.location.pathname,
          state: {}
        });
      }, 100);
    }
  }

  render() {
    console.log(this.props, "In Login Page");
    if (this.props.authenticated) {
      return (
        <Redirect
          to={{
            pathname: "/Movies",
            state: { from: this.props.location }
          }}
        />
      );
    }

    return (
      <div>
        <a className="btn btn-block social-btn google" href={GOOGLE_AUTH_URL}>
          <img src={googleLogo} alt="Google" /> Log in with Google
        </a>
        <a
          className="btn btn-block social-btn facebook"
          href={FACEBOOK_AUTH_URL}
        >
          <img src={fbLogo} alt="Facebook" /> Log in with Facebook
        </a>
        {/* <a className="btn btn-block social-btn github" href={GITHUB_AUTH_URL}>
            <img src={githubLogo} alt="Github" /> Log in with Github
          </a> */}
        <form onSubmit={this.handleSubmit} className="container">
          <div>
            <img src={loginImg} alt="login" />
          </div>
          <div className="form-item">
            <label htmlFor="email">Email: </label>
            <input
              type="text"
              name="email"
              value={this.state.email}
              onChange={this.handleInputChange}
              required
            />
            {!this.state.email && (
              <div className="help-block">Email is required</div>
            )}
          </div>
          <div className="form-item">
            <label htmlFor="password">Password: </label>
            <input
              type="password"
              name="password"
              value={this.state.password}
              onChange={this.handleInputChange}
              required
            />
            {!this.state.password && (
              <div className="help-block">Password is required</div>
            )}
            <div className="form-item">
              <button
                type="submit"
                className="btn btn-block btn-primary"
                style={{ width: 150 }}
              >
                Login
              </button>
              <Link to={"/register"}>Register</Link>
              <Link to="/NavigationBar" className="btn btn-link">
                Cancel
              </Link>
            </div>
          </div>
        </form>
      </div>
    );
  }

  handleInputChange(e) {
    const target = e.target;
    const inputName = target.name;
    const inputValue = target.value;

    this.setState({
      [inputName]: inputValue
    });
  }

  handleSubmit(e) {
    e.preventDefault();
    const loginRequest = Object.assign({}, this.state);
    console.log("Oops!", this.state);
    console.log("login", loginRequest);

    console.log(this.props.location, "From");
    console.log(this.props.location.pathname, "pathname");

    login(loginRequest)
      .then(response => {
        localStorage.setItem("accessToken", response.accessToken);
        Alert.success("You're successfully logged in!");
        this.props.history.push("/");
      })
      .catch(error => {
        Alert.error(
          (error && error.message) ||
            "Oops! Something went wrong. Please try again!"
        );
      });
  }
}
export default Login;
