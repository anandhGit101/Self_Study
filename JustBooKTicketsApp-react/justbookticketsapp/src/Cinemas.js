import React, { Component } from "react";
import axios from "axios";
import Card from "@material-ui/core/Card";
import CardMedia from "@material-ui/core/CardMedia";
import Button from "@material-ui/core/Button";
import Header from "./header/Header";
import { Dropdown, DropdownButton } from "react-bootstrap";

const baseUrl = "http://localhost:8899/jbtMovieMgmt";
export default class Cinemas extends Component {
  constructor(props) {
    super(props);
    this.state = {
      result: [],
      cinemasName: "",
      isAuthenticated: false,
      title: "Choose your locations",
      cinemasLocations: [],
      cinemasListBasedLocation: []
    };
    this.handleSchedule = this.handleSchedule.bind(this);
  }

  componentDidMount() {
    if (localStorage.getItem("accessToken")) {
      this.setState({
        isAuthenticated: true
      });
    }
    this.getAvailableLocations();
  }

  getAvailableLocations() {
    axios.get(`${baseUrl}/cinemas`).then(data => {
      var cinemasList = data.data.resultList;
      var locations = cinemasList.map(cinemas => cinemas.cinemasLocation);

      locations = Array.from(new Set(locations));
      console.log(locations);
      this.setState({
        result: cinemasList,
        cinemasLocations: locations
      });
    });
  }

  _onSelect(eventKey, event) {
    this.setState({ title: eventKey });
    //Call the api to load the cinemas details below.

    this.loadCinemas(eventKey);
  }

  loadCinemas(selectedCity) {
    console.log("selected city", selectedCity);
    var selectedCity = selectedCity;
    axios
      .get(`${baseUrl}/cinemas/loc?cinemasLocation=${selectedCity}`)
      .then(data => {
        console.log("cinemasListBasedLocation", data.data.resultList);
        this.setState({ cinemasListBasedLocation: data.data.resultList });
      });
  }

  handleSchedule = value => {
    var cinemasId = value;
    console.log(cinemasId);
    this.props.history.push(`cinemasschedule/${cinemasId}`);
  };

  render() {
    var availableLocations = this.state.cinemasLocations;
    return (
      <form>
        <div>
          <Header
            {...this.props}
            {...this.props.currentUser}
            authenticated={this.state.isAuthenticated}
          />
          <div className="select-box--container">
            <DropdownButton
              key={this.state.title}
              onSelect={this._onSelect.bind(this)}
              id="dropdown-basic-button"
              title={this.state.title}
            >
              {availableLocations &&
                availableLocations.map(locationName => (
                  <Dropdown.Item
                    key={locationName}
                    value={this.state.title}
                    eventKey={locationName}
                  >
                    {locationName}
                  </Dropdown.Item>
                ))}
            </DropdownButton>
          </div>
          <div className="row">
            {this.state.cinemasListBasedLocation.map(theatre => (
              <div className="cinemas-content">
                <h3 align="left" style={{ paddingLeft: "30px" }}></h3>
                {theatre.cinemasName}
                <br />
                {theatre.cinemasAddress}
                <Button
                  className="btn-primary"
                  id="scheduler"
                  onClick={() => this.handleSchedule(theatre.cinemasId)}
                >
                  Schedules
                </Button>
              </div>
            ))}
          </div>
        </div>
      </form>
    );
  }
}
