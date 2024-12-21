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


public class d20p2 {
    // Direction vectors for East, South, West, North
    private static final int[] DX = {0, 1, 0, -1};
    private static final int[] DY = {1, 0, -1, 0};
    private static HashMap<String, Long> hm = new HashMap<>();

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
        Queue<State> que = new LinkedList<State>();
        que.offer(new State(startX, startY, 0)); 
        boolean[][] visited = new boolean[rows][cols];
        visited[startX][startY] = true;
        long fullTime = runBFS(maze, startX, startY, endX, endY, rows, cols, que, visited);
        int cnt = 0;
        for (String key : hm.keySet()) {
            for (String key2: hm.keySet()){
                String[] keyArr = key.split(" ");
                String[] keyArr2 = key2.split(" ");
                int x1 = Integer.parseInt(keyArr[0]);
                int y1 = Integer.parseInt(keyArr[1]);
                int x2 = Integer.parseInt(keyArr2[0]);
                int y2 = Integer.parseInt(keyArr2[1]);
                int d = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                if (hm.get(key) - hm.get(key2) - d >= 100 && d <= 20) {
                    cnt++;
                }
            }
        }
        
        System.out.println(cnt);
        sc.close();
    }

    private static long runBFS(char[][] maze, int startX, int startY, int endX, int endY, int rows, int cols, Queue<State> que, boolean[][] visited) {
        while (!que.isEmpty()) {
            State current = que.poll();
            int x = current.x, y = current.y;
            visited[x][y] = true;
            long cost = current.cost;
            hm.put(x + " " + y, cost); 
            if (x == endX && y == endY) {
                return cost;
            }
            for (int i = 0; i < 4; i++) {
                int newX = x + DX[i];
                int newY = y + DY[i];
                if (isInBounds(newX, newY, rows, cols) && maze[newX][newY] != '#' && !visited[newX][newY]) {
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