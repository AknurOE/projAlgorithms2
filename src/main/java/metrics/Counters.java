package metrics;

public class Counters {
    private long comparisons = 0;
    private long swaps = 0;
    private long allocations = 0;
    private int maxDepth = 0;
    private int curDepth = 0;

    private long startTime = 0;
    private long endTime = 0;

    // === Методы для глубины рекурсии ===
    public void enter() {
        curDepth++;
        if (curDepth > maxDepth) maxDepth = curDepth;
    }

    public void exit() {
        curDepth--;
    }

    // === Инкременты ===
    public void incComparisons() { comparisons++; }
    public void incSwaps() { swaps++; }
    public void incAllocations() { allocations++; }

    // === Геттеры ===
    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getAllocations() { return allocations; }
    public int getMaxDepth() { return maxDepth; }
    public long getExecutionTime() { return endTime - startTime; }

    // === Таймер ===
    public void startTimer() { startTime = System.nanoTime(); }
    public void stopTimer() { endTime = System.nanoTime(); }

    // === Сброс ===
    public void reset() {
        comparisons = swaps = allocations = 0;
        curDepth = maxDepth = 0;
        startTime = endTime = 0;
    }
}
