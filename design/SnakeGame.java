package design;

/**
 * @author Aayush Srivastava
 */

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

/**
 * Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.
 *
 * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
 *
 * You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
 *
 * Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
 *
 * When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 *
 * Example:
 *
 * Given width = 3, height = 2, and food = [[1,2],[0,1]].
 *
 * Snake snake = new Snake(width, height, food);
 *
 * Initially the snake appears at position (0,0) and the food at (1,2).
 *
 * |S| | |
 * | | |F|
 *
 * snake.move("R"); -> Returns 0
 *
 * | |S| |
 * | | |F|
 *
 * snake.move("D"); -> Returns 0
 *
 * | | | |
 * | |S|F|
 *
 * snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
 *
 * | |F| |
 * | |S|S|
 *
 * snake.move("U"); -> Returns 1
 *
 * | |F|S|
 * | | |S|
 *
 * snake.move("L"); -> Returns 2 (Snake eats the second food)
 *
 * | |S|S|
 * | | |S|
 *
 * snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */
public class SnakeGame {

    public static void main(String[] args) {
        int w = 3, h = 2;
        int[][] f = new int[][] {
                {1, 2},
                {0, 1}
        };

        SnakeGame game = new SnakeGame(w, h, f);
        String[] gameMoves = new String[]{"R", "D", "R", "U", "L", "U"};
        for (String gameMove : gameMoves) {
            System.out.print(game.move(gameMove) + ", ");
        }
        System.out.println();
        w = 2;
        h = 2;
        f = new int[][] {
                {0, 1}
        };
        game = new SnakeGame(w, h, f);
        gameMoves = new String[]{"R", "D"};
        for (String gameMove : gameMoves) {
            System.out.print(game.move(gameMove) + ", ");
        }
        System.out.println();
        w = 2;
        h = 2;
        f = new int[][] {
                {1, 0}
        };
        game = new SnakeGame(w, h, f);
        gameMoves = new String[]{"R", "D", "L", "U", "R"};
        for (String gameMove : gameMoves) {
            System.out.print(game.move(gameMove) + ", ");
        }
    }

    class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x &&
                    y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    Deque<Position> deque;
    Set<Position> occupied;
    int score;
    int[][] food;
    int foodIndex;
    int width;
    int height;
    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.deque = new LinkedList<>();
        this.occupied = new HashSet<>();
        this.food = food;
        this.width = width;
        this.height = height;
        deque.addFirst(new Position(0, 0));
        occupied.add(deque.peekFirst());
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        Position head = deque.peekFirst();
        Position next = null;
        switch (direction) {
            case "U" : next = new Position(head.x - 1, head.y);
                break;
            case "L" : next = new Position(head.x, head.y - 1);
                break;
            case "D" : next = new Position(head.x + 1, head.y);
                break;
            case "R" : next = new Position(head.x, head.y + 1);
                break;
        }

        if (next.x < 0 || next.x >= height || next.y < 0 || next.y >= width || (!next.equals(deque.peekLast()) && occupied.contains(next)))
            return -1;


        deque.addFirst(next);
        occupied.add(next);

        if (foodIndex < food.length && next.x == food[foodIndex][0] && next.y == food[foodIndex][1]) {
            occupied.add(next);
            score++;
            foodIndex++;
        } else {
            Position tail = deque.pollLast();
            if (!next.equals(tail))
                occupied.remove(tail);
        }

        return score;
    }
}
/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */