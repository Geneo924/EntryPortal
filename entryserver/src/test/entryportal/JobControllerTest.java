

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.projects.entryportal.model.Job;
import com.projects.entryportal.repository.JobRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

class JobControllerTest {

    @InjectMocks
    private JobController jobController;

    @Mock
    private JobRepository jobRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllJobsTest() {

        Job job1 = new Job("posting1", "company1", "description1", true);
        Job job2 = new Job("posting2", "company2", "description2", true);
        List<Job> jobList = new ArrayList<>();
        jobList.add(job1);
        jobList.add(job2);

        Mockito.when(jobRepository.findAll()).thenReturn(jobList);


        ResponseEntity<List<Job>> response = jobController.getAllJobs(null);


        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).hasSize(2).contains(job1, job2);
    }

    @Test
    void getJobByIdTest() {

        Job job = new Job("posting", "company", "description", true);
        Mockito.when(jobRepository.findById("1")).thenReturn(Optional.of(job));


        ResponseEntity<Job> response = jobController.getJobById("1");


        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isEqualTo(job);
    }

    @Test
    void postJobTest() {

        Job job = new Job("posting", "company", "description", true);
        Mockito.when(jobRepository.save(ArgumentMatchers.any(Job.class))).thenReturn(job);


        ResponseEntity<Job> response = jobController.postJob(job);


        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(response.getBody()).isEqualTo(job);
    }

    @Test
    void updatedJobTest() {

        Job job = new Job("posting", "company", "description", true);
        Job updatedJob = new Job("updatedPosting", "updatedCompany", "updatedDescription", false);

        Mockito.when(jobRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(job));
        Mockito.when(jobRepository.save(ArgumentMatchers.any(Job.class))).thenReturn(updatedJob);


        ResponseEntity<Job> response = jobController.updatedJob("1", updatedJob);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().getPosting()).isEqualTo(updatedJob.getPosting());
        Assertions.assertThat(response.getBody().getCompany()).isEqualTo(updatedJob.getCompany());
        Assertions.assertThat(response.getBody().getDescription()).isEqualTo(updatedJob.getDescription());
        Assertions.assertThat(response.getBody().isActive()).isEqualTo(updatedJob.isActive());
    }

    @Test
    void handleValidationExceptionTest() {

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, null);


        ResponseEntity<String> response = jobController.handleValidationException(ex);


        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}