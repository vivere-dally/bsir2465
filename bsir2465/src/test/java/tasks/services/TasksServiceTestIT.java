package tasks.services;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tasks.model.Task;
import tasks.repository.ArrayTaskList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Lab 4. Integration testing. Integrating R, where R = ArrayTaskList")
@ExtendWith(MockitoExtension.class)
class TasksServiceTestIT {

    @Mock
    private Task t1;

    @Mock
    private Task t2;

    private List<Task> tasks;

    private ArrayTaskList arrayTaskList;
    private TasksService tasksService;

    @BeforeEach
    void setUp() {
        arrayTaskList = new ArrayTaskList();
        tasksService = new TasksService(arrayTaskList);

        tasks = new ArrayList<>();
        tasks.add(t1);
        tasks.add(t2);
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
