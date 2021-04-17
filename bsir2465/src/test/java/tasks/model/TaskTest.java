package tasks.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static tasks.Utils.d;

class TaskTest {

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void setActive(boolean value) {
        // arrange
        Task t = new Task("a", d(LocalDate.now()));

        // act
        t.setActive(value);

        // assert
        assertEquals(value, t.isActive());
    }

    @Test
    void nextTimeAfter_null() {
        // arrange
        Task t = new Task("a", d(LocalDate.now()));

        // act & assert
        assertNull(t.nextTimeAfter(d(LocalDate.now().plusDays(1))));
    }
}