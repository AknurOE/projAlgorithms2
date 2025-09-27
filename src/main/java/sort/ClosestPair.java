package sort;

import java.util.Arrays;
import java.util.Comparator;
import metrics.Counters;

public class ClosestPair {
    public static class Point {
        public double x, y;

        public Point(double x, double y) {   // теперь public
            this.x = x;
            this.y = y;
        }
    }

    public static double closestPair(Point[] points, Counters counters) {
        counters.startTimer();
        Point[] sortedX = points.clone();
        Arrays.sort(sortedX, Comparator.comparingDouble(p -> p.x));
        counters.incAllocations();

        Point[] sortedY = points.clone();
        Arrays.sort(sortedY, Comparator.comparingDouble(p -> p.y));
        counters.incAllocations();

        double result = closest(sortedX, sortedY, counters);
        counters.stopTimer();
        return result;
    }

    private static double closest(Point[] sortedX, Point[] sortedY, Counters counters) {
        counters.enter();
        int n = sortedX.length;
        if (n <= 3) {
            double res = bruteForce(sortedX, counters);
            counters.exit();
            return res;
        }

        int mid = n / 2;
        Point midPoint = sortedX[mid];

        Point[] leftX = Arrays.copyOfRange(sortedX, 0, mid);
        Point[] rightX = Arrays.copyOfRange(sortedX, mid, n);
        counters.incAllocations();
        counters.incAllocations();

        Point[] leftY = Arrays.stream(sortedY).filter(p -> p.x <= midPoint.x).toArray(Point[]::new);
        Point[] rightY = Arrays.stream(sortedY).filter(p -> p.x > midPoint.x).toArray(Point[]::new);
        counters.incAllocations();
        counters.incAllocations();

        double d1 = closest(leftX, leftY, counters);
        double d2 = closest(rightX, rightY, counters);
        double d = Math.min(d1, d2);

        double res = Math.min(d, stripClosest(sortedY, midPoint.x, d, counters));
        counters.exit();
        return res;
    }

    private static double bruteForce(Point[] points, Counters counters) {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                counters.incComparisons();
                min = Math.min(min, distance(points[i], points[j]));
            }
        }
        return min;
    }

    private static double stripClosest(Point[] points, double midX, double d, Counters counters) {
        Point[] strip = Arrays.stream(points)
                .filter(p -> Math.abs(p.x - midX) < d)
                .toArray(Point[]::new);
        counters.incAllocations();

        double min = d;
        for (int i = 0; i < strip.length; i++) {
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < min; j++) {
                counters.incComparisons();
                min = Math.min(min, distance(strip[i], strip[j]));
            }
        }
        return min;
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y));
    }
}
