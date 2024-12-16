import java.util.*;

class State {
    int x, y, direction, cost;
    State prev;

    State(int x, int y, int direction, int cost, State prev) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.cost = cost;
        this.prev = prev;
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

public class d16p2 {
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
        List<State> path = findMinimumScore(maze, startX, startY, 0, endX, endY, -1);
        if (path != null) {
            System.out.println("Minimum cost: " + path.get(path.size() - 1).cost);
            System.out.println("Path:");
            for (State state : path) {
                System.out.println(state);
            }
        } else {
            System.out.println("No path found.");
        }
        HashSet<String> uniquePositions = new HashSet<>();
        for (State state : path) {
            uniquePositions.add(state.x + "," + state.y);
        }

        int minCost = path.get(path.size() - 1).cost;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
            if (!uniquePositions.contains(i + "," + j) && maze[i][j] != '#') {
                for (int d = 0; d < 4; d++) {
                System.out.println("i: " + i + " j: " + j + " d: " + d);
                List<State> pathToIntermediate = findMinimumScore(maze, startX, startY, 0, i, j, d);
                List<State> pathFromIntermediate = findMinimumScore(maze, i, j, d, endX, endY, -1);
                if (pathToIntermediate != null && pathFromIntermediate != null) {
                    int totalCost = pathToIntermediate.get(pathToIntermediate.size() - 1).cost + pathFromIntermediate.get(pathFromIntermediate.size() - 1).cost;
                    if (totalCost == minCost) {
                        uniquePositions.add(i + "," + j);
                    }
                }
                }
            }
            }
        }
        System.out.println("Unique positions: " + uniquePositions.size());



        sc.close();
    }

    private static List<State> findMinimumScore(char[][] maze, int startX, int startY, int startD, int endX, int endY, int endD) {
        int rows = maze.length;
        int cols = maze[0].length;

        PriorityQueue<State> pq = new PriorityQueue<>(new StateComparator());
        pq.add(new State(startX, startY, startD, 0, null)); // Initially facing East
        boolean[][][] visited = new boolean[rows][cols][4];
        visited[startX][startY][0] = true;

        while (!pq.isEmpty()) {
            State current = pq.poll();
            int x = current.x, y = current.y, direction = current.direction, cost = current.cost;
            visited[x][y][direction] = true;

            if (x == endX && y == endY && (endD == -1 || direction == endD)) {
                List<State> path = new ArrayList<>();
                while (current != null) {
                    path.add(current);
                    current = current.prev;
                }
                Collections.reverse(path);
                return path;
            }

            for (int i = 0; i < 4; i++) {
                int newX = x + DX[i];
                int newY = y + DY[i];
                int newCost = cost + (i == direction ? 1 : 1001);
                if (isInBounds(newX, newY, rows, cols) && maze[newX][newY] != '#' && !visited[newX][newY][i]) {
                    pq.add(new State(newX, newY, i, newCost, current));
                }
            }
        }

        return null; // If no path is found
    }

    private static boolean isInBounds(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}