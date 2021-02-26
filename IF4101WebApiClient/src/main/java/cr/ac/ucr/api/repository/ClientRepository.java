package cr.ac.ucr.api.repository;

import cr.ac.ucr.api.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Procedure(name = "Client.insertClientServices")
    void insertClientServices(@Param("c_id") int c_id, @Param("s_id") int s_id);

    @Query(value = "{ call loadByName(:email)}", nativeQuery = true)
    Client loadByName(@Param("email") String email);



}
