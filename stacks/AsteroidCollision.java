package stacks;

/**
 * @author Aayush Srivastava
 */

import java.util.Arrays;
import java.util.Stack;

/**
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 *
 *
 *
 * Example 1:
 *
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
 * Example 2:
 *
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 * Example 3:
 *
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 * Example 4:
 *
 * Input: asteroids = [-2,-1,1,2]
 * Output: [-2,-1,1,2]
 * Explanation: The -2 and -1 are moving left, while the 1 and 2 are moving right. Asteroids moving the same direction never meet, so no asteroids will meet each other.
 *
 *
 * Constraints:
 *
 * 1 <= asteroids <= 104
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 */
public class AsteroidCollision {

    public int[] asteroidCollisionWithoutExtraSpace(int[] asteroids) {
        int n = asteroids.length;
        if (n == 0)
            return asteroids;
        int index = 1;
        int end = 0;
        while (index < asteroids.length) {
            if (end == -1) {
                asteroids[0] = asteroids[index];
                end = 0;
                index++;
            } else {
                if (asteroids[end] > 0 && asteroids[index] < 0) {
                    if (Math.abs(asteroids[end]) == Math.abs(asteroids[index])) {
                        end--;
                        index++;
                    } else if (Math.abs(asteroids[end]) > Math.abs(asteroids[index])) {
                        index++;
                    } else {
                        end--;
                    }
                } else {
                    end++;
                    asteroids[end] = asteroids[index];
                    index++;
                }

            }

        }

        return Arrays.copyOf(asteroids, end + 1);
    }

    public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        if (n == 0)
            return asteroids;
        Stack<Integer> stack = new Stack<>();

        for (int i : asteroids) {
            if (i > 0) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -i) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() == -i) {
                    stack.pop();
                } else if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(i);
                }
            }
        }

        int[] res = new int[stack.size()];
        int i = res.length - 1;
        while (!stack.isEmpty() && i >= 0) {
            res[i--] = stack.pop();
        }

        return res;
    }
}
