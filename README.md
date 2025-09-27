# Assignment 1 — Divide & Conquer Algorithms

## Architecture
This project implements four classic divide-and-conquer algorithms:

- **MergeSort**: splits arrays into halves, merges linearly, reuses a buffer, and switches to insertion sort for small arrays.  
- **QuickSort**: randomized pivot; recurse on smaller partition (`O(log n)`).  
- **Deterministic Select (Median-of-Medians)**: groups of 5, median-of-medians pivot, recurse only needed side.  
- **Closest Pair (2D)**: sort by X, recursive split, strip check by Y (7–8 neighbors).

A `Metrics` class tracks **execution time, comparisons, swaps, allocations, recursion depth**.

---

## Recurrence Analysis
- **MergeSort**: `T(n) = 2T(n/2) + Θ(n)` → `Θ(n log n)`  
- **QuickSort**: `T(n) = T(αn)+T((1-α)n)+Θ(n)` → `Θ(n log n)` avg, worst-case `Θ(n²)`  
- **Deterministic Select**: `T(n) = T(n/5)+T(7n/10)+Θ(n)` → `Θ(n)`  
- **Closest Pair**: `T(n) = 2T(n/2)+Θ(n)` → `Θ(n log n)`

---

## Experimental Results 📊

### Running Time vs n
![Running Time](time_vs_n.png)  
- MergeSort & QuickSort → `Θ(n log n)`  
- Select → linear  
- Closest Pair → `Θ(n log n)` with higher constants

### Recursion Depth vs n
![Recursion Depth](depth_vs_n.png)  
- MergeSort → logarithmic  
- QuickSort → `O(log n)` avg

---

## Discussion
- JVM, cache, and garbage collection affect constant factors.  
- QuickSort often faster than MergeSort.  
- Deterministic Select slower than randomized select, but guarantees linear time.  
- Closest Pair costly due to sorting + strip check, still `Θ(n log n)`.

---

## Conclusion 
- Sorting algorithms → `Θ(n log n)`  
- Deterministic Select → linear  
- Closest Pair → `Θ(n log n)`  
- Differences caused mainly by constants and runtime environment.


Deterministic Select is linear.

Closest Pair is Θ(n log n) with larger constants.

Performance differences are mainly caused by constant factors and runtime environment behavior.
