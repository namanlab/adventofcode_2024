import java.util.*;


public class d12 {

    public static int[] getAP(char[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return new int[]{0, 0};
        }
        if (visited[i][j]) {
            return new int[]{0, 0};
        }
        visited[i][j] = true;
        int[] res = new int[]{1, 4}; // area, perim
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Down, Up, Right, Left
        for (int d = 0; d < 4; d++) {
            int ni = i + directions[d][0];
            int nj = j + directions[d][1];
            if (ni >= 0 && ni < grid.length && nj >= 0 && nj < grid[0].length) {
                if (grid[ni][nj] == grid[i][j]){
                    res[1] -= 1;
                    int[] temp = getAP(grid, ni, nj, visited);
                    res[1] += temp[1];  
                    res[0] += temp[0];
                } 
            }
        }
        return res;
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<char[]> arr = new ArrayList<char[]>();
        int nrow = 0;
        int ncol = 0;
        long res = 0;
        while (sc.hasNextLine()) {
            nrow++;
            String[] line = sc.nextLine().split("");
            ncol = line.length;
            char[] row = new char[line.length];
            for (int i = 0; i < line.length; i++) {
                row[i] = line[i].charAt(0);
            }
            arr.add(row);
        }
        char[][] grid = new char[nrow][ncol];
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                grid[i][j] = arr.get(i)[j];
            }
        }
        boolean[][] visited = new boolean[nrow][ncol];
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                if (!visited[i][j]) {
                    int[] temp = getAP(grid, i, j, visited);
                    System.out.println("Area: " + temp[0] + " Perim: " + temp[1] + " char: " + grid[i][j]);
                    res += temp[0]*temp[1];

                }
            }
        }
        System.out.println(res);
        sc.close();
    }
}