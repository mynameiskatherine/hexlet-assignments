package exercise.controller;

import exercise.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;
//    GET /comments — список всех комментариев
//    GET /comments/{id} – просмотр конкретного комментария
//    POST /comments – создание нового комментария. При успешном создании возвращается статус 201
//    PUT /comments/{id} – обновление комментария
//    DELETE /comments/{id} – удаление комментария
    @GetMapping("")
    public List<Comment> index() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment show(@PathVariable long id) {
        var comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            return comment.get();
        } else {
            throw new ResourceNotFoundException("comment with id " + id + " was not found");
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@RequestBody Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable long id, @RequestBody Comment newComment) {
        var comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            var commentToUpdate = comment.get();
            commentToUpdate.setBody(newComment.getBody());
            return newComment;
        } else {
            throw new ResourceNotFoundException("comment with id " + id + " was not found");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        var comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            commentRepository.delete(comment.get());
        } else {
            throw new ResourceNotFoundException("comment with id " + id + " was not found");
        }
    }
}
// END
