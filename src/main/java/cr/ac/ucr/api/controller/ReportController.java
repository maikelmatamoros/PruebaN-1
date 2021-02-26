package cr.ac.ucr.api.controller;
import cr.ac.ucr.api.model.Report;
import cr.ac.ucr.api.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api/report")
public class ReportController {
    @Autowired
    private ReportService service;

    @GetMapping("/getReportData/{report_Number}")
    public ResponseEntity<Report> getReportData(@PathVariable Integer report_Number){
        try {
            Report report = service.getReportData(report_Number);

            return new ResponseEntity<Report>(report, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Report>(HttpStatus.NOT_FOUND);
        }
    }

}
