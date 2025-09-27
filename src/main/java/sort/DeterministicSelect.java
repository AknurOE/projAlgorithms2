package sort;

import java.util.Arrays;
import metrics.Counters;

public class DeterministicSelect {
    public static int select(int[] arr, int k, Counters counters) {
        counters.startTimer();
        int result = select(arr, 0, arr.length - 1, k, counters);
        counters.stopTimer();
        return result;
    }

    private static int select(int[] arr, int left, int right, int k, Counters counters) {
        counters.enter();
        if (left == right) {
            counters.exit();
            return arr[left];
        }

        int pivot = medianOfMedians(arr, left, right, counters);
        int pivotIndex = partition(arr, left, right, pivot, counters);

        int rank = pivotIndex - left + 1;
        int result;
        if (k == rank) result = arr[pivotIndex];
        else if (k < rank) result = select(arr, left, pivotIndex - 1, k, counters);
        else result = select(arr, pivotIndex + 1, right, k - rank, counters);

        counters.exit();
        return result;
    }

    private static int medianOfMedians(int[] arr, int left, int right, Counters counters) {
        int n = right - left + 1;
        if (n < 5) {
            Arrays.sort(arr, left, right + 1);
            counters.incAllocations();
            return arr[left + n / 2];
        }
        int[] medians = new int[(n + 4) / 5];
        counters.incAllocations();
        for (int i = 0; i < medians.length; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }
        return medianOfMedians(medians, 0, medians.length - 1, counters);
    }

    private static int partition(int[] arr, int left, int right, int pivot, Counters counters) {
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
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                counters.incSwaps();
                i++;
                j--;
            }
        }
        return i - 1;
    }
}
