import React from "react";
import Alert from "react-s-alert";
import { Link } from "react-router-dom";
import { signup } from "../AppUtil";

export default class Register extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      user: {
        name: "",
        userName: "",
        password: "",
        email: "",
        phoneNo: "",
        address: ""
      }
    };

    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleInputChange(event) {
    const { name, value } = event.target;
    const { user } = this.state;
    this.setState({
      user: {
        ...user,
        [name]: value
      }
    });
  }

  handleSubmit(event) {
    console.log(this.props, "The registration in process....", this.state.user);
    const signUpRequest = Object.assign({}, this.state.user);

    signup(signUpRequest)
      .then(response => {
        Alert.success(
          "You're successfully registered. Please login to continue!"
        );
        this.props.history.push("/login");
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
      <form onSubmit={this.handleSubmit} className="container">
        <div className="form-item">
          <label htmlFor="Name">Name: </label>

          <input
            type="text"
            name="name"
            value={this.state.user.name}
            onChange={this.handleInputChange}
            required
          />
          {!this.state.user.name && (
            <div className="help-block">Name is required</div>
          )}
        </div>
        <div className="form-item">
          <label htmlFor="userName">Username: </label>
          <input
            type="text"
            name="userName"
            value={this.state.user.userName}
            onChange={this.handleInputChange}
            required
          />
          {!this.state.user.userName && (
            <div className="help-block">UserName is required</div>
          )}
        </div>
        <div className="form-item">
          <label htmlFor="password">Password: </label>
          <input
            type="password"
            name="password"
            value={this.state.user.password}
            onChange={this.handleInputChange}
            required
          />
          {!this.state.user.password && (
            <div className="help-block">Password is required</div>
          )}
        </div>
        <div className="form-item">
          <label htmlFor="email">Email Address: </label>
          <input
            type="text"
            name="email"
            value={this.state.user.email}
            onChange={this.handleInputChange}
            required
          />
          {!this.state.user.email && (
            <div className="help-block">
              Email Address is required, enter a valid address.
            </div>
          )}
        </div>
        <div className="form-item">
          <label htmlFor="phoneNo">Contact No: </label>
          <input
            type="phonenum"
            name="phoneNo"
            size="15"
            maxLength="10"
            value={this.state.user.phoneNo}
            onChange={this.handleInputChange}
            required
          />
          {!this.state.user.phoneNo && (
            <div className="help-block">
              Contact Number is required to get the ticket.
            </div>
          )}
        </div>
        <div className="form-item">
          <label htmlFor="address">Contact Address: </label>
          <input
            type="text"
            name="address"
            value={this.state.user.address}
            onChange={this.handleInputChange}
            required
          />
          {!this.state.user.address && (
            <div className="help-block">
              Address is required, enter a valid address.
            </div>
          )}
        </div>

        <div className="form-item">
          <button className="btn btn-block btn-primary" style={{ width: 150 }}>
            Register
          </button>
          <Link to="/" className="btn btn-link">
            Cancel
          </Link>
        </div>
      </form>
    );
  }
}
