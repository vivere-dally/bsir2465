package tasks.services;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tasks.model.Task;
import tasks.repository.ArrayTaskList;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static tasks.Utils.d;

@DisplayName("Lab 4. Integration testing. Integrating E, where R = Task")
class TasksServiceTestIT2 {

    private List<Task> tasks = Arrays.asList(
            new Task("a", d(LocalDate.now())),
            new Task("b", d(LocalDate.now().plusDays(1))));

    private ArrayTaskList arrayTaskList;
    private TasksService tasksService;

    @BeforeEach
    void setUp() {
        arrayTaskList = new ArrayTaskList();
        tasksService = new TasksService(arrayTaskList);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void getObservableList(int n) {
        // arrange
        for (int i = 0; i < n; i++) {
            arrayTaskList.add(tasks.get(i));
        }

        // act
        ObservableList<Task> actual = tasksService.getObservableList();

        // assert
        assertEquals(n, actual.size());
        for (int i = 0; i < n; i++) {
            assertEquals(tasks.get(i), actual.get(i));
        }
    }
}
