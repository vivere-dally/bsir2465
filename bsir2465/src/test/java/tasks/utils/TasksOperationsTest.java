package tasks.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tasks.model.Task;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Lab 3")
class TasksOperationsTest {

    @Mock
    private ArrayList<Task> tasksList;

    private TasksOperations tasksOperations;

    @BeforeEach
    void setUp() {
        tasksOperations = new TasksOperations(tasksList);
    }

    private Date d(LocalDate localDate) {
        return Date.valueOf(localDate);
    }

    @Test
    void F02_TC01() {
        // arrange
        when(tasksList.size()).thenReturn(0);

        // act & assert
        assertNull(tasksOperations.incoming(d(LocalDate.now()), d(LocalDate.now().plusDays(1))));
    }

    @Test
    void F02_TC02() {
        // arrange
        Task t = new Task("a", d(LocalDate.now()), d(LocalDate.now().plusDays(1)), 1);
        t.setActive(true);
        Iterable<Task> expected = Arrays.asList(t, t);

        when(tasksList.size()).thenReturn(2);
        when(tasksList.get(0)).thenReturn(t);
        when(tasksList.get(1)).thenReturn(t);

        // act
        Iterable<Task> actual = tasksOperations.incoming(d(LocalDate.now()), d(LocalDate.now().plusDays(2)));

        // assert
        assertIterableEquals(expected, actual);
    }

    /**
     * invalid
     */
    @Test
    void F02_TC03() {
        // arrange
        Task t = new Task("a", d(LocalDate.now()), d(LocalDate.now().plusDays(1)), 1);
        t.setActive(true);

        when(tasksList.size()).thenReturn(1);
        when(tasksList.get(0)).thenReturn(t);

        // act & assert
        assertNull(tasksOperations.incoming(d(LocalDate.now()), d(LocalDate.now().minusDays(1))));
    }

    @Test
    void F02_TC04() {
        // arrange
        Task t = new Task("a", d(LocalDate.now()), d(LocalDate.now().plusDays(7)), 259200);
        t.setActive(true);

        when(tasksList.size()).thenReturn(1);
        when(tasksList.get(0)).thenReturn(t);

        // act & assert
        assertNull(tasksOperations.incoming(d(LocalDate.now().plusDays(1)), d(LocalDate.now().plusDays(2))));
    }

    @Test
    void F02_TC05() {
        // arrange
        Task t = new Task("a", d(LocalDate.now()), d(LocalDate.now().plusDays(5)), 172800);
        t.setActive(true);
        Iterable<Task> expected = Collections.singletonList(t);

        when(tasksList.size()).thenReturn(1);
        when(tasksList.get(0)).thenReturn(t);

        // act
        Iterable<Task> actual = tasksOperations.incoming(d(LocalDate.now()), d(LocalDate.now().plusDays(2)));

        // assert
        assertIterableEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {255})
    void F02_TC06(int n) {
        // arrange
        when(tasksList.size()).thenReturn(n);
        List<Task> expected = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Task t = new Task("a" + i, d(LocalDate.now()), d(LocalDate.now().plusDays(1)), 1);
            t.setActive(true);
            expected.add(t);
            when(tasksList.get(i)).thenReturn(t);
        }

        // act
        Iterable<Task> actual = tasksOperations.incoming(d(LocalDate.now()), d(LocalDate.now().plusDays(2)));

        // assert
        assertIterableEquals(expected, actual);
    }
}
