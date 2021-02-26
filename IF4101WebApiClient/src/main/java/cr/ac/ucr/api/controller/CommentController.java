package cr.ac.ucr.api.controller;


import cr.ac.ucr.api.model.Comment;
import cr.ac.ucr.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api/comment")
public class CommentController {

    @Autowired
    private CommentService service;

    @GetMapping("/comments/{id}")
    public List<Comment> list(@PathVariable Integer id) {

        return service.listAll(id);
    }

    @PostMapping("/add")
    public Comment add(@RequestBody Comment comment) {

        Date now = new Date();
        comment.setComment_Timestamp(now);
        comment.setCreation_Date(now);
        comment.setState(true);
        service.save(comment);
        return comment;

    }


    @PutMapping("/update")
    public ResponseEntity<Comment> update(@RequestBody Comment comment) {
        try {
            Comment aux = service.get(comment.getComment_Id());
            aux.setModified_By(comment.getModified_By());
            aux.setDescription(comment.getDescription());
            aux.setModify_Date(new Date());
            service.save(aux);
            return new ResponseEntity<Comment>(comment, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        Comment aux = service.get(id);
        aux.setState(false);
        aux.setModify_Date(new Date());
        service.save(aux);
    }

}
