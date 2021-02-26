package cr.ac.ucr.api.repository;


import cr.ac.ucr.api.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    @Query(value = "{call GetReportData (:r_id)}", nativeQuery = true)
    Report getReportData(@Param("r_id") int report_Number);
}
