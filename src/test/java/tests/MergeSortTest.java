package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class MergeSortTest {

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = arr.clone();
        MergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testReversed() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        MergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRandomOrder() {
        int[] arr = {9, 1, 6, 3, 7};
        int[] expected = arr.clone();
        Arrays.sort(expected);
        MergeSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testEmptyArray() {
        int[] arr = {};
        MergeSort.sort(arr);
        assertEquals(0, arr.length);
    }
}
