import http from "../http-common";

class JobDataService {
  getAll() {
    return http.get("/jobs");
  }

  get(id) {
    return http.get(`/jobs/${id}`);
  }

  create(data) {
    return http.post("/jobs", data);
  }

  update(id, data) {
    return http.put(`/jobs/${id}`, data);
  }

//   delete(id) {
//     return http.delete(`/jobs/${id}`);
//   }

//   deleteAll() {
//     return http.delete(`/jobs`);
//   }

//   findByTitle(title) {
//     return http.get(`/jobs?title=${title}`);
//   }
}

const jobDataServiceInstance = new JobDataService();
export default jobDataServiceInstance;
