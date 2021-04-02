package tasks.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tasks.repository.ArrayTaskList;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Lab 2")
class TasksServiceTest {
    private static final String descriptionErrorMessage = "Description must have a length between 3 and 255";
    private static final String dateErrorMessage = "Start Date must be before End Date";

    @Mock
    private ArrayTaskList arrayTaskList;

    private TasksService tasksService;

    private Date getDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    @BeforeEach
    void setUp() {
        tasksService = new TasksService(arrayTaskList);
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaa", "aaaa"})
    void addTask_valid_BVA_DescriptionLength_lowerValidLimit(String description) {
        // arrange
        Date startDate = getDate(LocalDate.now()), endDate = getDate(LocalDate.now().plusDays(1));

        // act & assert
        assertDoesNotThrow(() -> {
            tasksService.addTask(description, startDate, endDate, 1, true);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {254, 255})
    void addTask_valid_BVA_DescriptionLength_upperValidLimit(int descriptionLength) {
        // arrange
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < descriptionLength; i++) {
            stringBuilder.append('a');
        }
        String description = stringBuilder.toString();
        Date startDate = getDate(LocalDate.now()), endDate = getDate(LocalDate.now().plusDays(1));

        // act & assert
        assertDoesNotThrow(() -> {
            tasksService.addTask(description, startDate, endDate, 1, true);
        });
    }

    @Test()
    void addTask_nonvalid_BVA_DescriptionLength_upperBound() {
        // arrange
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            stringBuilder.append('a');
        }
        String description = stringBuilder.toString();
        Date startDate = getDate(LocalDate.now()), endDate = getDate(LocalDate.now().plusDays(1));

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tasksService.addTask(description, startDate, endDate, 1, true);
        });

        // assert
        assertTrue(exception.getMessage().contains(descriptionErrorMessage));
    }

    @Test
    void addTask_nonvalid_BVA_DescriptionLength_lowerBound() {
        // arrange
        String description = "aa";
        Date startDate = getDate(LocalDate.now()), endDate = getDate(LocalDate.now().plusDays(1));

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tasksService.addTask(description, startDate, endDate, 1, true);
        });

        // assert
        assertTrue(exception.getMessage().contains(descriptionErrorMessage));
    }

    @Test
    void addTask_valid_ECP_DescriptionLength() {
        // arrange
        String description = "hello world";
        Date startDate = getDate(LocalDate.now()), endDate = getDate(LocalDate.now().plusDays(1));

        // act & assert
        assertDoesNotThrow(() -> {
            tasksService.addTask(description, startDate, endDate, 1, true);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 300})
    void addTask_nonvalid_ECP_DescriptionLength(int descriptionLength) {
        // arrange
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < descriptionLength; i++) {
            stringBuilder.append('a');
        }
        String description = stringBuilder.toString();
        Date startDate = getDate(LocalDate.now()), endDate = getDate(LocalDate.now().plusDays(1));

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tasksService.addTask(description, startDate, endDate, 1, true);
        });

        // assert
        assertTrue(exception.getMessage().contains(descriptionErrorMessage));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void addTask_valid_ECP_DatePrecedence(int daysToAdd) {
        // arrange
        String description = "aaaaa";
        Date startDate = getDate(LocalDate.now()), endDate = getDate(LocalDate.now().plusDays(daysToAdd));

        // act & assert
        assertDoesNotThrow(() -> {
            tasksService.addTask(description, startDate, endDate, 1, true);
        });
    }

    @Test
    void addTask_nonvalid_ECP_DatePrecedence() {
        // arrange
        String description = "aaaaa";
        Date startDate = getDate(LocalDate.now()), endDate = getDate(LocalDate.now().minusDays(1));

        // act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tasksService.addTask(description, startDate, endDate, 1, true);
        });

        // assert
        assertTrue(exception.getMessage().contains(dateErrorMessage));
    }
}
