package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    void testSimpleCase() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(0, 0),
                new ClosestPair.Point(3, 4),
                new ClosestPair.Point(1, 1)
        };
        double dist = ClosestPair.closestPair(points);
        assertEquals(Math.sqrt(2), dist, 1e-6);
    }

    @Test
    void testTwoPoints() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(5, 5),
                new ClosestPair.Point(1, 1)
        };
        double dist = ClosestPair.closestPair(points);
        assertEquals(Math.sqrt(32), dist, 1e-6);
    }

    @Test
    void testIdenticalPoints() {
        ClosestPair.Point[] points = {
                new ClosestPair.Point(2, 2),
                new ClosestPair.Point(2, 2),
                new ClosestPair.Point(3, 3)
        };
        double dist = ClosestPair.closestPair(points);
        assertEquals(0.0, dist, 1e-6);
    }
}
