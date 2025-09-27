package sort;

import metrics.Counters;

public class MergeSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] arr, Counters counters) {
        counters.startTimer();
        int[] buffer = new int[arr.length];
        counters.incAllocations();
        sort(arr, buffer, 0, arr.length - 1, counters);
        counters.stopTimer();
    }

    private static void sort(int[] arr, int[] buffer, int left, int right, Counters counters) {
        counters.enter();
        if (right - left <= CUTOFF) {
            insertionSort(arr, left, right, counters);
            counters.exit();
            return;
        }
        int mid = (left + right) / 2;
        sort(arr, buffer, left, mid, counters);
        sort(arr, buffer, mid + 1, right, counters);
        merge(arr, buffer, left, mid, right, counters);
        counters.exit();
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right, Counters counters) {
        System.arraycopy(arr, left, buffer, left, right - left + 1);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            counters.incComparisons();
            if (buffer[i] <= buffer[j]) arr[k++] = buffer[i++];
            else arr[k++] = buffer[j++];
        }
        while (i <= mid) arr[k++] = buffer[i++];
        while (j <= right) arr[k++] = buffer[j++];
    }

    private static void insertionSort(int[] arr, int left, int right, Counters counters) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i], j = i - 1;
            while (j >= left) {
                counters.incComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    counters.incSwaps();
                    j--;
                } else break;
            }
            arr[j + 1] = key;
        }
    }
}
