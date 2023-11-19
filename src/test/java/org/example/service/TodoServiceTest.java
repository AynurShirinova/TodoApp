import org.example.domain.Todo;
import org.example.repository.TodoRepository;
import org.example.service.TodoService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class TodoServiceTest {

    private TodoRepository mockRepository;
    private TodoService todoService;

    @Before
    public void setUp() {
        // Initialize any necessary resources or mock objects
        mockRepository = mock(TodoRepository.class);
        todoService = new TodoService(mockRepository);
    }

    @Test
    public void testAddTask() {
        // Given
        Todo todoToAdd = new Todo();
        todoToAdd.setDescription("test1");

        // When
        todoService.addTask(todoToAdd);

        // Then
        verify(mockRepository).addTask(todoToAdd);
    }

    @Test
    public void testReadTasks() {
        // Given
        List<Todo> dummyTasks = List.of(
                new Todo("Task 1"),
                new Todo("Task 2")
        );

        // Mock the behavior of the repository
        when(mockRepository.readTasks()).thenReturn(dummyTasks);

        // When
        List<Todo> result = todoService.readTasks();

        // Then
        assertEquals(dummyTasks.size(), result.size());
        assertTrue(result.containsAll(dummyTasks));
    }

    // You can write similar tests for deleteTask and updateTask methods.
    // Make sure to mock any dependencies and verify the expected behavior.

    // For example:
    // @Test
    // public void testDeleteTask() {
    //     // Your deleteTask test logic here
    // }

    // @Test
    // public void testUpdateTask() {
    //     // Your updateTask test logic here
    // }
}
