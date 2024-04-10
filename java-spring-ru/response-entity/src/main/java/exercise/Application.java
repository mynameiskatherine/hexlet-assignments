package exercise;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    ResponseEntity<List<Post>> index() {
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(posts);
    }

    @GetMapping("/posts/{id}")
    ResponseEntity<Post> show(@PathVariable String id) {
        return ResponseEntity.of(posts.stream().filter(p -> p.getId().equals(id)).findFirst());
    }

    @PostMapping("/posts")
    ResponseEntity<Post> create(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(post);
    }

    @PutMapping("/posts/{id}")
    ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post newPost) {
        Optional<Post> potentialPost = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (potentialPost.isPresent()) {
            Post post = potentialPost.get();
            post.setId(newPost.getId());
            post.setTitle(newPost.getTitle());
            post.setBody(newPost.getBody());
            return ResponseEntity.ok()
                    .body(newPost);
        } else {
            return ResponseEntity.status(204)
                    .body(newPost);
        }
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
