package cr.ac.ucr.api.repository;

import cr.ac.ucr.api.model.Issue;
import cr.ac.ucr.api.model.ServiceT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceTRepository extends JpaRepository<ServiceT, Integer> {

    @Query(value = "{ call Get_Services_By_Client(:c_id)}", nativeQuery = true)
    List<ServiceT> getServicesByClient(@Param("c_id") int id);

}
