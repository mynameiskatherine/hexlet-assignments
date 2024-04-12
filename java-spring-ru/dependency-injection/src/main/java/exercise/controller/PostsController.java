package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("")
    public List<Post> index() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post show(@PathVariable long id) {
        var post = postRepository.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new ResourceNotFoundException("Post with id " + id + " not found");
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        postRepository.save(post);
        return post;
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable long id, @RequestBody Post newPost) {
        var post = postRepository.findById(id);
        if (post.isPresent()) {
            var postToUpdate = post.get();
            postToUpdate.setTitle(newPost.getTitle());
            postToUpdate.setBody(newPost.getBody());
            return newPost;
        } else {
            throw new ResourceNotFoundException("Post with id " + id + " not found");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        var post = postRepository.findById(id);
        if (post.isPresent()) {
            postRepository.delete(post.get());
            commentRepository.deleteByPostId(id);
        } else {
            throw new ResourceNotFoundException("Post with id " + id + " not found");
        }
    }
}
// END
