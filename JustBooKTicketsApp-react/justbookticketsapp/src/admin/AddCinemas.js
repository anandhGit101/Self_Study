import React from "react";
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { Redirect } from "react-router-dom";

export default class AddCinemas extends React.Component {
  constructor(props) {
    console.log(props, "In AddMovie Page");
    super(props);

    this.state = {
      cinemas: {
        cinemasName: "",
        cinemasLocation: "",
        cinemasAddress: ""
      }
    };
  }

  handleChange(event) {
    const { name, value } = event.target;
    const { cinemas } = this.state;
    this.setState({
      cinemas: {
        ...cinemas,
        [name]: value
      }
    });
  }

  handleSubmit(e) {
    e.preventDefault();

    const addCinemasRequest = Object.assign({}, this.state.cinemas);
    addCinemas(addCinemasRequest)
      .then(response => {
        Alert.success("Cinemas added successfully.!");
        this.props.history.push("/addCinemas");
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
            <label htmlFor="cinemasName">Cinemas Title: </label>
            <input
              type="text"
              name="cinemasName"
              value={this.state.cinemasName}
              onChange={this.handleChange}
            />
          </div>
          <div>
            <label htmlFor="cinemasAddress">Cinemas Address: </label>
            <input
              type="text"
              name="cinemasAddress"
              value={this.state.cinemasAddress}
              onChange={this.handleChange}
            />
          </div>
          <div>
            <label htmlFor="cinemasLocation">Cinemas Location: </label>
            <input
              type="text"
              name="cinemasLocation"
              value={this.state.cinemasLocation}
              onChange={this.handleChange}
            />
          </div>
        </Container>
      </form>
    );
  }
}
