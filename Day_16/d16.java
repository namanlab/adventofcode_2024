import java.util.*;


class State {
    int x, y, direction, cost;

    State(int x, int y, int direction, int cost) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        State state = (State) obj;
        return x == state.x && y == state.y && direction == state.direction && cost == state.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, direction, cost);
    }

    @Override
    public String toString() {
        return "State{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                ", cost=" + cost +
                '}';
    }
}

class StateComparator implements Comparator<State> {
    @Override
    public int compare(State s1, State s2) {
        return Integer.compare(s1.cost, s2.cost);
    }
}


public class d16 {
    // Direction vectors for East, South, West, North
    private static final int[] DX = {0, 1, 0, -1};
    private static final int[] DY = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<char[]> mazeList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            mazeList.add(line.toCharArray());
        }
        char[][] maze = new char[mazeList.size()][mazeList.get(0).length];
        for (int i = 0; i < mazeList.size(); i++) {
            maze[i] = mazeList.get(i);
        }
        System.out.println(findMinimumScore(maze));
        sc.close();
    }

    private static int findMinimumScore(char[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;
        int startX = 0, startY = 0, endX = 0, endY = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 'S') {
                    startX = i;
                    startY = j;
                } else if (maze[i][j] == 'E') {
                    endX = i;
                    endY = j;
                }
            }
        }

        PriorityQueue<State> pq = new PriorityQueue<>(new StateComparator());
        pq.add(new State(startX, startY, 0, 0)); // Initially facing East
        boolean[][][] visited = new boolean[rows][cols][4];
        visited[startX][startY][0] = true;

        while (!pq.isEmpty()) {
            State current = pq.poll();
            int x = current.x, y = current.y, direction = current.direction, cost = current.cost;
            visited[x][y][direction] = true;

            if (x == endX && y == endY) {
            return cost;
            }

            for (int i = 0; i < 4; i++) {
            int newX = x + DX[i];
            int newY = y + DY[i];
            int newCost = cost + (i == direction ? 1 : 1001);
            if (isInBounds(newX, newY, rows, cols) && maze[newX][newY] != '#' && !visited[newX][newY][i]) {
                pq.add(new State(newX, newY, i, newCost));
            }
            }
        }

        return -1; // If no path is found
    }

    private static boolean isInBounds(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}