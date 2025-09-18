package TodoApp.controller;

import TodoApp.modele.Todo;
import TodoApp.repository.TodoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:5500")

public class TodoController {

    private final TodoRepository repository;

    public TodoController(TodoRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Todo> getAllTodos(){
        return repository.findAll();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo){
        return repository.save(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todo){
        Todo existing = repository.findById(id).orElseThrow();
        existing.setTitle(todo.getTitle());
        existing.setCompleted(todo.isCompleted());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id){
        repository.deleteById(id);
    }

}
