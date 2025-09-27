package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class QuickSortTest {

    @Test
    void testSortedArray() {
        int[] arr = {1, 2, 3, 4};
        int[] expected = arr.clone();
        QuickSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testUnsortedArray() {
        int[] arr = {10, -1, 2, 5, 0};
        int[] expected = arr.clone();
        Arrays.sort(expected);
        QuickSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        QuickSort.sort(arr);
        assertEquals(42, arr[0]);
    }
}
