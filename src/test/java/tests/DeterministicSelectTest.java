package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {

    @Test
    void testSelectFirst() {
        int[] arr = {7, 2, 5, 3, 9};
        assertEquals(2, DeterministicSelect.select(arr, 1)); // минимальный
    }

    @Test
    void testSelectMiddle() {
        int[] arr = {7, 2, 5, 3, 9};
        assertEquals(5, DeterministicSelect.select(arr, 3)); // медиана
    }

    @Test
    void testSelectLast() {
        int[] arr = {7, 2, 5, 3, 9};
        assertEquals(9, DeterministicSelect.select(arr, 5)); // максимум
    }
}
