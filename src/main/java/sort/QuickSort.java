package sort;

import java.util.Random;
import metrics.Counters;

public class QuickSort {
    private static final Random rand = new Random();

    public static void sort(int[] arr, Counters counters) {
        counters.startTimer();
        quickSort(arr, 0, arr.length - 1, counters);
        counters.stopTimer();
    }

    private static void quickSort(int[] arr, int left, int right, Counters counters) {
        counters.enter();
        while (left < right) {
            int pivotIndex = left + rand.nextInt(right - left + 1);
            int pivot = arr[pivotIndex];

            int i = left, j = right;
            while (i <= j) {
                while (true) {
                    counters.incComparisons();
                    if (!(arr[i] < pivot)) break;
                    i++;
                }
                while (true) {
                    counters.incComparisons();
                    if (!(arr[j] > pivot)) break;
                    j--;
                }
                if (i <= j) {
                    swap(arr, i, j, counters);
                    i++;
                    j--;
                }
            }

            if (j - left < right - i) {
                if (left < j) quickSort(arr, left, j, counters);
                left = i;
            } else {
                if (i < right) quickSort(arr, i, right, counters);
                right = j;
            }
        }
        counters.exit();
    }

    private static void swap(int[] arr, int i, int j, Counters counters) {
        counters.incSwaps();
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
