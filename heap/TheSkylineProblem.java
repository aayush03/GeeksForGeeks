package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Aayush Srivastava
 */
public class TheSkylineProblem {


    /**
     * When visiting all start points and end points in order:
     *
     * Observations:
     *
     * If a position is shadowed by other buildings
     *
     *  1. height of that building is larger than the building to which current
     *      position belong;
     *  2. the start point of that building must be smaller(or equal to) than this
     *      position;
     *  3. the end point of that building must be larger(or equal to) than this
     *      position;
     * Tus we have:
     *
     * 1. when you reach a start point, the height of current building immediately
     *     takes effect which means it could possibly affect the contour or shadow
     *     others when mixed with other following buildings;
     * 2. when you reach a end point, the height of current building will stop its
     *     influences;
     * 3. our target exists at the position where height change happens and there
     *     is nothing above it shadowing it;
     * Obviously, to implement the idea that 'current height takes effect' and 'find out whether current height is shadowed by other buildings', we need a mechanism to store current taking effect heights, meanwhile, figure out which one is the maximum, delete it if needed efficiently, which hints us to use a priority queue or BST.
     *
     * Thus, our algorithm could be summarised in following pseudo code:
     *
     * for position in sorted(all start points and all end points)
     *        if this position is a start point
     *               add its height
     *        else if this position is a end point
     *               delete its height
     *        compare current max height with previous max height, if different, add
     *        current position together with this new max height to our result, at the
     *        same time, update previous max height to current max height;
     * To implement this algorithm, here are some concrete examples:
     *
     * In my implementation, I use a PriorityQueue to store end point values when visiting a start point, and store the [height, end point value] into a TreeMap. Thus:
     * when moving to next start point value, I can compare the next start point value with elements in PriorityQueue, thus achieving visiting all start points and end points in order(exploits the fact that start points are already sorted);
     * Meantime, I can get current max height from TreeMap in O(logn);
     *
     * Firstly, please notice what we need to achieve:
     *
     *   1. visit all start points and all end points in order;
     *   2. when visiting a point, we need to know whether it is a start point or a
     *       end point, based on which we can add a height or delete a height from
     *       our data structure;
     * To achieve this, his implementation:
     *
     *   1. use a int[][] to collect all [start point, - height] and [end point, height]
     *       for every building;
     *   2. sort it, firstly based on the first value, then use the second to break
     *       ties;
     * Thus,
     *
     *   1. we can visit all points in order;
     *   2. when points have the same value, higher height will shadow the lower one;
     *   3. we know whether current point is a start point or a end point based on the
     *       sign of its height;
     * @param buildings
     * @return
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for(int[] b:buildings) {
            // start point has negative height value
            height.add(new int[]{b[0], -b[2]});
            // end point has normal height value
            height.add(new int[]{b[1], b[2]});
        }

        // sort height, based on the first value, if necessary, use the second to
        // break ties
        Collections.sort(height, (a, b) -> {
            if(a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        // Use a maxHeap to store possible heights
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));

        // Provide a initial value to make it more consistent
        pq.offer(0);

        // Before starting, the previous max height is 0;
        int prev = 0;

        // visit all points in order
        for(int[] h:height) {
            if(h[1] < 0) { // a start point, add height
                pq.offer(-h[1]);
            } else {  // a end point, remove height
                pq.remove(h[1]);
            }
            int cur = pq.peek(); // current max height;

            // compare current max height with previous max height, update result and
            // previous max height if necessary
            if(prev != cur) {
                result.add(Arrays.asList(h[0], cur));
                prev = cur;
            }
        }
        return result;
    }

    class Sky {
        int x1, x2, y;
        Sky next;

        public Sky(int _x1, int _x2, int _y) {
            x1 = _x1;
            x2 = _x2;
            y = _y;
        }
    }

    Sky origin = new Sky(-1, Integer.MAX_VALUE, 0);

    public List<List<Integer>> getSkylineEfficient(int[][] buildings) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if (buildings.length == 0) return result;

        origin.next = new Sky(Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
        Sky current = origin, p, newp, prevp = current;
        int x1, x2, y;

        for (int[] building : buildings) {
            x1 = building[0];
            x2 = building[1];
            y = building[2];

            while (current.x2 < x1) {
                prevp = current;
                current = current.next;
            }

            p = current;

            while (x2 > x1) {
                newp = p;
                if (y > p.y) {
                    if (x2 < p.x2) {
                        newp = new Sky(x1, x2, y);
                        newp.next = new Sky(x2, p.x2, p.y);
                        newp.next.next = p.next;
                        p.next = newp;
                    } else {
                        newp = new Sky(x1, p.x2, y);
                        newp.next = p.next;
                        p.next = newp;

                    }

                    if (x1 == p.x1) {
                        prevp.next = newp;
                    } else {
                        p.x2 = x1;
                    }

                    prevp = newp;
                } else {
                    prevp = p;
                }

                if (x2 > newp.x2) {
                    x1 = newp.x2;
                    p = newp.next;
                } else {
                    x1 = x2;
                }
            }

        }

        current = origin.next;
        y = -1;
        x2 = 0;

        while (current != null) {
            if (current.y != y && current.x1 < current.x2) {
                result.add(Arrays.asList(current.x1, current.y));
                y = current.y;
                x2 = current.x2;
            }

            current = current.next;
        }

        if (y > 0) result.add(Arrays.asList(x2, 0));
        return result;
    }
}
