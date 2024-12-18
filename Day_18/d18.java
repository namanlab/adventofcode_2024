import java.util.*;


class State {
    int x, y;
    long cost;

    State(int x, int y, long cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        State state = (State) obj;
        return x == state.x && y == state.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, cost);
    }

    @Override
    public String toString() {
        return "State{" +
                "x=" + x +
                ", y=" + y +
                ", cost=" + cost +
                '}';
    }
}


public class d18 {
    // Direction vectors for East, South, West, North
    private static final int[] DX = {0, 1, 0, -1};
    private static final int[] DY = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] grid = new int[71][71];
        int counter = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] coords = line.split(",");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            grid[x][y] = 1; 
            counter++;
            if (counter == 1024) {
                break;
            }
        }
        System.out.println(findSteps(grid));
        sc.close();
    }

    private static long findSteps(int[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;
        int startX = 0, startY = 0, endX = rows - 1, endY = cols - 1;
        Queue<State> que = new LinkedList<State>();
        que.offer(new State(startX, startY, 0)); 
        boolean[][] visited = new boolean[rows][cols];
        visited[startX][startY] = true;
        while (!que.isEmpty()) {
            State current = que.poll();
            int x = current.x, y = current.y;
            long cost = current.cost;
            if (x == endX && y == endY) {
                return cost;
            }
            for (int i = 0; i < 4; i++) {
                int newX = x + DX[i];
                int newY = y + DY[i];
                if (isInBounds(newX, newY, rows, cols) && maze[newX][newY] != 1 && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    que.offer(new State(newX, newY, cost + 1));
                }
            }
        }

        return -1; // If no path is found
    }

    private static boolean isInBounds(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}