import java.util.*;


public class d10p2 {

    public static long getPaths(int[][] grid, int i, int j, int prev, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return 0; 
        }
        if (visited[i][j] || grid[i][j] != prev + 1) {
            return 0; 
        }
        if (grid[i][j] == 9) {
            return 1; 
        }

        long res = 0;
        res += getPaths(grid, i + 1, j, grid[i][j], visited);
        res += getPaths(grid, i - 1, j, grid[i][j], visited);
        res += getPaths(grid, i, j + 1, grid[i][j], visited);
        res += getPaths(grid, i, j - 1, grid[i][j], visited);
    
        return res;
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<int[]> arr = new ArrayList<int[]>();
        int nrow = 0;
        int ncol = 0;
        long res = 0;
        while (sc.hasNextLine()) {
            nrow++;
            String[] line = sc.nextLine().split("");
            ncol = line.length;
            int[] row = new int[line.length];
            for (int i = 0; i < line.length; i++) {
                if (line[i].equals(".")) {
                    row[i] = -1;
                } else {
                    row[i] = Integer.parseInt(line[i]);
                }
                
            }
            arr.add(row);
        }
        int[][] grid = new int[nrow][ncol];
        ArrayList<int[]> zp = new ArrayList<>();
        for (int i = 0; i < nrow; i++) {
            System.out.println(Arrays.toString(arr.get(i)));
            for (int j = 0; j < ncol; j++) {
                if (arr.get(i)[j] == 0) {
                    zp.add(new int[] { i, j });
                }
                grid[i][j] = arr.get(i)[j];
            }
        }
        for (int[] cur: zp){
            boolean[][] visited = new boolean[nrow][ncol];
            long temp = getPaths(grid, cur[0], cur[1], -1, visited);
            System.out.println(cur[0] + " " + cur[1] + " " + temp);
            res += temp;
        }
        System.out.println(res);
        sc.close();
    }
}