package cr.ac.ucr.api.controller;

import cr.ac.ucr.api.model.Issue;
import cr.ac.ucr.api.model.IssueDTO;
import cr.ac.ucr.api.restClient.IssueRestClient;
import cr.ac.ucr.api.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api/issue")
public class IssueController {
    @Autowired
    private IssueService service;

    @GetMapping("/issues2/{id}")
    public List<Issue> list(@PathVariable Integer id) {

        return service.listAll(id);
    }

    @GetMapping("/issues/{report_Number}")
    public ResponseEntity<Issue> get(@PathVariable Integer report_Number) {
        try {
            Issue issue = service.get(report_Number);
            return new ResponseEntity<Issue>(issue, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Issue>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Issue> add(@RequestBody Issue issue) {
        Issue issueInserted = null;
        try {
            Date now = new Date();
            issue.setCreation_Date(now);
            issue.setModify_Date(now);
            issue.setRegister_Timestamp(now);
            issue.setStatus('I');
            issue.setState(true);
            issue.setModified_By(issue.getCreated_By());

            issueInserted = service.save(issue);
            IssueRestClient restClient = new IssueRestClient();
            restClient.callPostIssueAPI(issueInserted);

            return new ResponseEntity<Issue>(issueInserted, HttpStatus.OK);
        } catch (Exception e) {
            if(issueInserted!= null && issueInserted.getReport_Number()!=0){
                service.delete(issueInserted.getReport_Number());
            }
            return new ResponseEntity<Issue>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/")
    public ResponseEntity<Issue> update(@RequestBody Issue issue) {
        try {
            service.save(issue);
            return new ResponseEntity<Issue>(issue, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Issue>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{report_Number}")
    public void delete(@PathVariable Integer report_Number) {
        service.delete(report_Number);
    }

    @PutMapping("/updateStatus/{report_Number}")
    public ResponseEntity<Issue> updateStatus(@PathVariable int report_Number, @RequestBody String status) {
        try {
            Issue issue = service.get(report_Number);
            issue.setStatus(status.toCharArray()[1]);
            service.save(issue);
            return new ResponseEntity<Issue>(issue, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Issue>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateSupporter/{report_Number}")
    public ResponseEntity<Issue> updateSupporter(@PathVariable int report_Number, @RequestBody int supporter) {
        try {
            Issue issue = service.get(report_Number);
            issue.setSupporter_Assigned(supporter);
            service.save(issue);
            return new ResponseEntity<Issue>(issue, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Issue>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/issues")
    public List<Issue> list() {
        return service.list();
    }

}
