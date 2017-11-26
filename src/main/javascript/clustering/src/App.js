import React, { Component } from 'react';
import Request from 'superagent';
import './App.css';

import ReactHTMLConverter from 'react-html-converter';

const apiRoot = 'http://localhost:8080/';

class Test extends React.Component {
  render() {
      return <div>{this.props.text}</div>;
  }
}

const converter = new ReactHTMLConverter();
converter.registerComponent('test', Test);

class App extends Component {

  state = {
    content: "To get started, select information to cluster from the menu above, then select the cluster type you wish to display.",
    k: 4
  };

  handleClick = (path) => {
    Request
      .get(apiRoot+path)
      .end((err, res) => {
        if (!err) {
          this.setState({
            content: res.text
          });
        }
      });
  }

  kmeans = (path) => {
    this.handleClick(`${path}?k=${this.state.k}`);
  }

  handleChange = (e) => {
    this.setState({k:e.target.value});
  }

  render() {

    return (
      <div className="App">
        <header className="App-header">
          <div className="menu">
            <label htmlFor="kNum">Select k number:</label>
            <select value={this.state.k} id="kNum" onChange={this.handleChange}>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
            </select>
            <a className="menu-item" onClick={() => this.kmeans('blogs/kmeans')}>Blogs: K-Means</a>
            <a className="menu-item" onClick={() => this.kmeans('wiki/kmeans')}>Wikipedia: K-Means</a>
            <a className="menu-item" onClick={() => this.handleClick('blogs/hierarchy')}>Blogs: Hierarchy</a>
            <a className="menu-item" onClick={() => this.handleClick('wiki/hierarchy')}>Wikipedia: Hierarchy</a>
          </div>
        </header>
        <div className="App-intro">
          {converter.convert(this.state.content)}
        </div>
      </div>
    );
  }
}

export default App;