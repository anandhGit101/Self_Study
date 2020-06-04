import React from "react";
import { Link } from "react-router-dom";

import imageRoll from "./assets/Film_Roll_658.jpg";
import Header from "./header/Header";
import { connect } from "react-redux";

export default class NavigationBar extends React.Component {
  constructor(props) {
    super(props);
    this.state = { signUpClicked: false };
  }

  render() {
    return (
      <div>
        <div className="header">
          <Header></Header>
        </div>
      </div>
    );
  }
}
