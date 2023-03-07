import React, { Component } from "react";
import JobDataService from "../services/job.service";

export default class AddJob extends Component {
  constructor(props) {
    super(props);
    this.onChangePosting= this.onChangePosting.bind(this);
    this.onChangeCompany= this.onChangeCompany.bind(this);
    this.onChangeDescription = this.onChangeDescription.bind(this);
    this.saveJob = this.saveJob.bind(this);
    this.newJob = this.newJob.bind(this);

    this.state = {
      id: null,
      posting: "",
      company: "",
      description: "", 
      active: false,
    };
  }

  onChangePosting(e) {
    this.setState({
      posting: e.target.value
    });
  }

  onChangeCompany(e) {
    this.setState({
      company: e.target.value
    });
  }

  onChangeDescription(e) {
    this.setState({
      description: e.target.value
    });
  }

  saveJob() {
    var data = {
        posting: this.state.posting,
        company: this.state.company,
        description: this.state.description,
    };

    JobDataService.create(data)
      .then(response => {
        this.setState({
          id: response.data.id,
          posting: response.data.posting,
          company: response.data.company,
          description: response.data.description,
          active : true
        
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  newJob() {
    this.setState({
        id: null,
        posting: "",
        company: "",
        description: "", 
        active: false,
    });
  }

  render() {
    return (
        <div className="submit-form">
          {this.state.submitted ? (
            <div>
              <h4>You submitted successfully!</h4>
              <button className="btn btn-success" onClick={this.newJob}>
                Add
              </button>
            </div>
          ) : (
            <div>
              <div className="form-group">
                <label htmlFor="posting">Posting</label>
                <input
                  type="text"
                  className="form-control"
                  id="posting"
                  required
                  value={this.state.posting}
                  onChange={this.onChangePosting}
                  name="posting"
                />
              </div>
              <div className="form-group">
                <label htmlFor="company">Company</label>
                <input
                  type="text"
                  className="form-control"
                  id="company"
                  required
                  value={this.state.company}
                  onChange={this.onChangeCompany}
                  name="company"
                />
              </div>
              <div className="form-group">
                <label htmlFor="description">Description</label>
                <input
                  type="text"
                  className="form-control"
                  id="description"
                  required
                  value={this.state.description}
                  onChange={this.onChangeDescription}
                  name="description"
                />
              </div>
              <button onClick={this.saveJob} className="btn btn-success">
                Submit
              </button>
            </div>
          )}
        </div>
      );
    }
  }
