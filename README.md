Assignment 1 — Divide & Conquer Algorithms
Architecture

This project implements four classic divide-and-conquer algorithms:

MergeSort: splits arrays into halves, merges linearly, reuses a buffer, and switches to insertion sort for small arrays.

QuickSort: uses a randomized pivot and always recurses on the smaller partition to keep recursion depth O(log n).

Deterministic Select (Median-of-Medians): divides elements into groups of five, selects the median of medians as pivot, and recurses only into the required partition.

Closest Pair of Points (2D): sorts points by X-coordinate, recursively splits, and checks points in a vertical strip sorted by Y (7–8 neighbors per point).

A Metrics class tracks recursion depth, execution time, number of comparisons, swaps, and memory allocations.

Recurrence Relations and Analysis
MergeSort

Recurrence:
T(n) = 2T(n/2) + Θ(n)
Master Theorem, Case 2 → T(n) = Θ(n log n).

QuickSort

Average-case recurrence:
T(n) = T(αn) + T((1-α)n) + Θ(n) with randomized pivot.
Akra–Bazzi intuition → T(n) = Θ(n log n)
Worst-case: Θ(n²), but occurs rarely.

Deterministic Select

Recurrence:
T(n) = T(n/5) + T(7n/10) + Θ(n)
Akra–Bazzi → T(n) = Θ(n)
Guaranteed linear time.

Closest Pair of Points (2D)

Recurrence:
T(n) = 2T(n/2) + Θ(n) (sorting + strip check)
Master Theorem, Case 2 → T(n) = Θ(n log n).

Experimental Results
Running Time vs n

Insert your graph here:


MergeSort and QuickSort show Θ(n log n) growth.

Deterministic Select shows linear growth.

Closest Pair grows as Θ(n log n) with higher constants.

Recursion Depth vs n

Insert your graph here:


MergeSort depth grows logarithmically.

QuickSort remains O(log n) on average.

Depth matches theoretical expectations.

Discussion

Constant factors vary due to caching, JVM optimizations, and garbage collection.

QuickSort often outperforms MergeSort in practice, despite both being Θ(n log n).

Deterministic Select is slower than randomized selection but guarantees linear time.

Closest Pair is more costly due to sorting and strip management, though still Θ(n log n).

Conclusion

Theoretical bounds (Master Theorem, Akra–Bazzi intuition) match the experimental behavior:

Sorting algorithms run in Θ(n log n).

Deterministic Select is linear.

Closest Pair is Θ(n log n) with larger constants.

Performance differences are mainly caused by constant factors and runtime environment behavior.
