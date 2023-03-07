import React, { Component } from "react";
import JobDataService from "../services/job.service";
import { Link } from "react-router-dom";

export default class JobList extends Component {
  constructor(props) {
    super(props);
    this.onChangeSearchPosting = this.onChangeSearchPosting.bind(this);
    this.retrieveJobs = this.retrieveJobs.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActiveJob = this.setActiveJob.bind(this);
    this.removeAllJobs = this.removeAllJobs.bind(this);
    this.searchPosting = this.searchPosting.bind(this);

    this.state = {
      Jobs: [],
      currentJob: null,
      currentIndex: -1,
      searchPosting: ""
    };
  }

  componentDidMount() {
    this.retrieveJobs();
  }

  onChangeSearchPosting(e) {
    const searchPosting = e.target.value;

    this.setState({
      searchPosting: searchPosting
    });
  }

  retrieveJobs() {
    JobDataService.getAll()
      .then(response => {
        this.setState({
          Jobs: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  refreshList() {
    this.retrieveJobs();
    this.setState({
      currentJob: null,
      currentIndex: -1
    });
  }

  setActiveJob(Job, index) {
    this.setState({
      currentJob: Job,
      currentIndex: index
    });
  }

  removeAllJobs() {
    JobDataService.deleteAll()
      .then(response => {
        console.log(response.data);
        this.refreshList();
      })
      .catch(e => {
        console.log(e);
      });
  }

  searchPosting() {
    JobDataService.findByPosting(this.state.searchPosting)
      .then(response => {
        this.setState({
          Jobs: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  
  render() {
    const { searchPosting, Jobs, currentJob, currentIndex } = this.state;

    return (
      <div className="list row">
        <div className="col-md-8">
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Search by Posting"
              value={searchPosting}
              onChange={this.onChangeSearchPosting}
            />
            <div className="input-group-append">
              <button
                className="btn btn-outline-secondary"
                type="button"
                onClick={this.searchPosting}
              >
                Search
              </button>
            </div>
          </div>
        </div>
        <div className="col-md-6">
          <h4>Jobs List</h4>

          <ul className="list-group">
            {Jobs &&
              Jobs.map((Job, index) => (
                <li
                  className={
                    "list-group-item " +
                    (index === currentIndex ? "active" : "")
                  }
                  onClick={() => this.setActiveJob(Job, index)}
                  key={index}
                >
                  {Job.Posting}
                </li>
              ))}
          </ul>

          <button
            className="m-3 btn btn-sm btn-danger"
            onClick={this.removeAllJobs}
          >
            Remove All
          </button>
        </div>
        <div className="col-md-6">
          {currentJob ? (
            <div>
              <h4>Job</h4>
              <div>
                <label>
                  <strong>Posting:</strong>
                </label>{" "}
                {currentJob.Posting}
              </div>
              <div>
                <label>
                  <strong>Description:</strong>
                </label>{" "}
                {currentJob.description}
              </div>
              <div>
                <label>
                  <strong>Status:</strong>
                </label>{" "}
                {currentJob.published ? "Published" : "Pending"}
              </div>

              <Link
                to={"/Jobs/" + currentJob.id}
                className="badge badge-warning"
              >
                Edit
              </Link>
            </div>
          ) : (
            <div>
              <br />
              <p>Please click on a Job...</p>
            </div>
          )}
        </div>
      </div>
    );
  }
}
