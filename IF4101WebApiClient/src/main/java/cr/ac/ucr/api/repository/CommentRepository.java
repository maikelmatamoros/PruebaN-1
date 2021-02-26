package cr.ac.ucr.api.repository;


import cr.ac.ucr.api.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query(value = "{ call GetCommentByReport(:r_id)}", nativeQuery = true)
    List<Comment> getCommentByReport(@Param("r_id") Integer r_id);
}
