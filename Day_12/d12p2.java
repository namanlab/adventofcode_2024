import java.util.*;
import java.util.function.Function;

public class d12p2 {

    // Helper function to check grid bounds
    public static boolean isValid(char[][] grid, int i, int j) {
        return i >= 0 && j >= 0 && i < grid.length && j < grid[0].length;
    }

    // Function to get all coordinates of a region
    public static List<int[]> getCoordinates(char[][] grid, int i, int j, boolean[][] visited) {
        List<int[]> coordinates = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;
        
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            coordinates.add(cell);
            
            for (int[] dir : directions) {
                int ni = cell[0] + dir[0];
                int nj = cell[1] + dir[1];
                
                if (isValid(grid, ni, nj) && !visited[ni][nj] && grid[ni][nj] == grid[i][j]) {
                    visited[ni][nj] = true;
                    queue.add(new int[]{ni, nj});
                }
            }
        }
        
        return coordinates;
    }

    // Function to count corners of a region based on adjacency rules
    public static int countCorners(List<int[]> coordinates, char[][] grid) {
        int corners = 0;

        for (int[] cell : coordinates) {
            int i = cell[0], j = cell[1];
            int unattachedCount = 0;
            if (!isValid(grid, i - 1, j) || grid[i - 1][j] != grid[i][j]) unattachedCount++; // North
            if (!isValid(grid, i + 1, j) || grid[i + 1][j] != grid[i][j]) unattachedCount++; // South
            if (!isValid(grid, i, j - 1) || grid[i][j - 1] != grid[i][j]) unattachedCount++; // West
            if (!isValid(grid, i, j + 1) || grid[i][j + 1] != grid[i][j]) unattachedCount++; // East
            corners += switch (unattachedCount) {
                case 4 -> 4; // Fully isolated cell
                case 3 -> 2; // Corner with three unattached sides
                case 2 -> {
                    boolean oppositeAttached = 
                        (isValid(grid, i - 1, j) && grid[i - 1][j] == grid[i][j] && 
                         isValid(grid, i + 1, j) && grid[i + 1][j] == grid[i][j]) ||
                        (isValid(grid, i, j - 1) && grid[i][j - 1] == grid[i][j] && 
                         isValid(grid, i, j + 1) && grid[i][j + 1] == grid[i][j]);
                    yield oppositeAttached ? 0 : 1;
                }
                default -> 0; // Not a corner
            };
            if (!isValid(grid, i - 1, j + 1) || grid[i - 1][j + 1] != grid[i][j]) { // NorthEast
                if (isValid(grid, i - 1, j) && grid[i - 1][j] == grid[i][j] &&
                    isValid(grid, i, j + 1) && grid[i][j + 1] == grid[i][j]) {
                    corners++;
                }
            }
            if (!isValid(grid, i + 1, j + 1) || grid[i + 1][j + 1] != grid[i][j]) { // SouthEast
                if (isValid(grid, i + 1, j) && grid[i + 1][j] == grid[i][j] &&
                    isValid(grid, i, j + 1) && grid[i][j + 1] == grid[i][j]) {
                    corners++;
                }
            }
            if (!isValid(grid, i - 1, j - 1) || grid[i - 1][j - 1] != grid[i][j]) { // NorthWest
                if (isValid(grid, i - 1, j) && grid[i - 1][j] == grid[i][j] &&
                    isValid(grid, i, j - 1) && grid[i][j - 1] == grid[i][j]) {
                    corners++;
                }
            }
            if (!isValid(grid, i + 1, j - 1) || grid[i + 1][j - 1] != grid[i][j]) { // SouthWest
                if (isValid(grid, i + 1, j) && grid[i + 1][j] == grid[i][j] &&
                    isValid(grid, i, j - 1) && grid[i][j - 1] == grid[i][j]) {
                    corners++;
                }
            }
        }

        return corners;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<char[]> arr = new ArrayList<>();
        int nrow = 0;
        int ncol = 0;
        long res = 0;

        // Read the grid
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

        // Convert ArrayList to 2D grid
        char[][] grid = new char[nrow][ncol];
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                grid[i][j] = arr.get(i)[j];
            }
        }

        // Visited array
        boolean[][] visited = new boolean[nrow][ncol];

        // Process each cell
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                if (!visited[i][j]) {
                    // Get all coordinates of the current region
                    List<int[]> regionCoordinates = getCoordinates(grid, i, j, visited);

                    // Calculate area and corners
                    int area = regionCoordinates.size();
                    int corners = countCorners(regionCoordinates, grid);

                    // Multiply area by corners
                    res += area * corners;

                    System.out.println("Area: " + area + " Corners: " + corners + " Char: " + grid[i][j]);
                }
            }
        }

        // Print the result
        System.out.println(res);
        sc.close();
    }
}