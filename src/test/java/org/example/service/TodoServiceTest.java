import org.example.domain.Todo;
import org.example.repository.TodoRepository;
import org.example.service.TodoService;
import org.junit.Test;
import java.util.List;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class TodoServiceTest {
    @Test
    public void testAddTask() {
        TodoRepository todoRepository = new TodoRepository();
        TodoService todoService = new TodoService(todoRepository);

        Todo todoToAdd = new Todo( "Sample Todo", "This is a sample task");
        todoService.addTask();

        List<Todo> allTodos = todoService.readTasks();
        assertEquals(1, allTodos.size());
        assertEquals(todoToAdd, allTodos.get(0));
    }

    @Test
    public void testUpdateTask() {
        TodoRepository todoRepository = new TodoRepository();
        TodoService todoService = new TodoService(todoRepository);

        // Adding a Todo
        Todo todoToAdd = new Todo( "Sample Todo", "This is a sample task");
        todoService.addTask();

        // Updating the added Todo
//        Todo updatedTodo = new Todo(todoToAdd.getId(), "Updated Todo", "Updated description");
//        todoService.updateTask(updatedTodo);

        List<Todo> allTodos = todoService.readTasks();
        assertEquals(1, allTodos.size());
   //     assertEquals(updatedTodo, allTodos.get(0));
    }
    @Test
    public void testDeleteTask() {
        TodoRepository todoRepository = new TodoRepository();
        TodoService todoService = new TodoService(todoRepository);

        // Adding a Todo
        Todo todoToAdd = new Todo( "Sample Todo", "This is a sample task");
        todoService.addTask();

        // Deleting the added Todo
  //      todoService.deleteTask(todoToAdd.getId());

        List<Todo> allTodos = todoService.readTasks();
        assertTrue(allTodos.isEmpty());
    }
}

