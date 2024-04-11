package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Task;
import exercise.repository.TaskRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(path = "")
    public List<Task> index() {
        return taskRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Task show(@PathVariable long id) {

        var task =  taskRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));

        return task;
    }

    // BEGIN
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task) {
        taskRepository.save(task);
        return task;
    }

    @PutMapping(path = "/{id}")
    public Task update(@PathVariable long id, @RequestBody Task taskNew) {
        var taskOld = taskRepository.findById(id);
        if (taskOld.isPresent()) {
            var task = taskOld.get();
            task.setTitle(taskNew.getTitle());
            task.setDescription(taskNew.getDescription());
            return taskNew;
        } else {
            throw new ResourceNotFoundException("no task found");
        }
    }
    // END

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        taskRepository.deleteById(id);
    }
}
