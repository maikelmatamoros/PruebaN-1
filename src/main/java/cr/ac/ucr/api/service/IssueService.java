package cr.ac.ucr.api.service;

import cr.ac.ucr.api.model.Issue;
import cr.ac.ucr.api.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class IssueService {

    @Autowired
    private IssueRepository repository;


    public List<Issue> listAll(int id) { return repository.listIssue(id);
    }

    public Issue save(Issue issue) {
        return repository.save(issue);
    }

    public Issue get(int report_Number) {
        return repository.findById(report_Number).get();
    }

    public void delete(int report_Number) {
        repository.deleteById(report_Number);
    }

    public List<Issue> list() { return repository.findAll();
    }

}
