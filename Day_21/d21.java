import java.util.*;


class State {
    int x, y;
    long cost;
    char cur1 = 'A';
    char cur2 = 'A';
    char cur3 = 'A';

    State(int x, int y, long cost, char cur1, char cur2, char cur3) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.cur1 = cur1;
        this.cur2 = cur2;
        this.cur3 = cur3;
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
                ", cur1=" + cur1 +
                ", cur2=" + cur2 +
                ", cur3=" + cur3 +
                '}';
    }
}


public class d21 {
    // Direction vectors for East, South, West, North
    private static final int[] DX = {0, 1, 0, -1};
    private static final int[] DY = {1, 0, -1, 0};

    public static char curM = 'A';
    public static char cur1 = 'A';
    public static char cur2 = 'A';
    public static char cur3 = 'A';

    private static char[][] grid1 = {
        {'7', '8', '9'},
        {'4', '5', '6'},
        {'1', '2', '3'},
        {'-', '0', 'A'}
    };
    private static char[][] grid2 = {
        {'-', '^', 'A'},
        {'<', 'v', '>'}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long res = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            char[] temp = line.toCharArray();
            curM = 'A';
            cur1 = 'A';
            cur2 = 'A';
            cur3 = 'A';
            long lenVal = getLength(temp);
            System.out.println("Length: " + lenVal);
            res += Integer.parseInt(line.substring(0, line.length() - 1)) * lenVal;
        }
        System.out.println(res);
        sc.close();
    }

    private static long getLength(char[] line) {
        long res = 0;
        for (int i = 0; i < line.length; i++) {
            char c = line[i];
            long temp = runDjikstra(grid1, curM, c, 0);
            System.out.println(curM + " " + c + " " + temp);
            res += temp;
            curM = c;
        }
        return res;
    }

    public static long runDjikstra(char[][] maze, char startM, char endM, int lvl) {
        int rows = maze.length;
        int cols = maze[0].length;
        long[][] dist = new long[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == startM) {
                    startX = i;
                    startY = j;
                }
            }
        }
        dist[startX][startY] = 0;
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.cost));
        pq.add(new State(startX, startY, 0, cur1, cur2, cur3));
        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int x = curr.x;
            int y = curr.y;
            long cost = curr.cost;
            if (maze[x][y] == endM) {
                cur1 = curr.cur1;
                cur2 = curr.cur2;
                cur3 = curr.cur3;
                return cost + getDst(-1, curr, lvl, true);
            }
            for (int i = 0; i < 4; i++) {
                int newX = x + DX[i];
                int newY = y + DY[i];
                if (isInBounds(newX, newY, rows, cols) && maze[newX][newY] != '-') {
                    long newCost = cost + getDst(i, curr, lvl, false);
                    if (newCost < dist[newX][newY]) {
                        dist[newX][newY] = newCost;
                        pq.add(new State(newX, newY, newCost, cur1, cur2, cur3));
                    }
                }
            }
        }
        return -1;
    }


    private static long getDst(int dir, State curr, int lvl, boolean flagA) {
        char tcur1 = curr.cur1;
        char tcur2 = curr.cur2;
        char tcur3 = curr.cur3;
        char endChar = ' ';
        if (dir == 0) {
            endChar = '>';
        } else if (dir == 1) {
            endChar = 'v';
        } else if (dir == 2) {
            endChar = '<';
        } else if (dir == 3) {
            endChar = '^';
        }
        if (flagA){endChar = 'A';}
        if (lvl == 0){
            long cost = runDjikstra(grid2, tcur1, endChar, 1);
            cur1 = endChar;
            return cost;
        }
        if (lvl == 1){
            long cost = runDjikstra(grid2, tcur2, endChar, 2);
            cur2 = endChar;
            return cost;
        }
        if (lvl == 2){
            long cost = runDjikstra(grid2, tcur3, endChar, 3);
            cur3 = endChar;
            return cost;
        }
        return 1;
    }

    private static boolean isInBounds(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}