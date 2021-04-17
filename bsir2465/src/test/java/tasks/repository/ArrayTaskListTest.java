package tasks.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tasks.model.Task;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ArrayTaskListTest {

    @Mock
    private Task t;

    private ArrayTaskList arrayTaskList;

    @BeforeEach
    void setUp() {
        arrayTaskList = new ArrayTaskList();
    }

    @Test
    void add() {
        // arrange & act
        arrayTaskList.add(t);

        // assert
        assertEquals(1, arrayTaskList.size());
    }

    @Test
    void remove() {
        // arrange
        arrayTaskList.add(t);

        // act
        arrayTaskList.remove(t);

        // assert
        assertEquals(0, arrayTaskList.size());
    }
}