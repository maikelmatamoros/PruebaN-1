package cr.ac.ucr.api.service;

import cr.ac.ucr.api.model.Report;
import cr.ac.ucr.api.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReportService {

    @Autowired
    private ReportRepository repository;

    public Report getReportData(int report_Number) {
        return repository.getReportData(report_Number);
    }

}
