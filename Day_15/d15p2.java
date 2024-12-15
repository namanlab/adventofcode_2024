import java.util.*;


public class d15p2 {

    public static void printGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j = j + 2) {
                System.out.print(grid[i][j]);
                System.out.print(grid[i][j + 1]);
            }
            System.out.println();
        }
    }

    public static boolean moveRockUp(char[][] grid, int st_x, int st_y) { // st_y is ']'
        if (st_x < 0 || st_y < 0 || st_x >= grid.length || st_y >= grid[0].length) {
            return false;
        }
        if (st_x - 1 < 0) {
            return false;
        }
        if  (grid[st_x - 1][st_y] == '.' && grid[st_x - 1][st_y - 1] == '.') {
            grid[st_x - 1][st_y] = ']'; grid[st_x - 1][st_y - 1] = '[';
            grid[st_x][st_y] = '.'; grid[st_x][st_y - 1] = '.';
            return true;
        }
        if (grid[st_x - 1][st_y] == '#' || grid[st_x - 1][st_y - 1] == '#') {
            return false;
        }
        if  (grid[st_x - 1][st_y] == ']' && grid[st_x - 1][st_y - 1] == '[') {
            boolean temp = moveRockUp(grid, st_x - 1, st_y);
            if (temp){
                grid[st_x - 1][st_y] = ']'; grid[st_x - 1][st_y - 1] = '[';
                grid[st_x][st_y] = '.'; grid[st_x][st_y - 1] = '.';
                return true;
            }
            return false;
        }
        boolean temp1 = true;
        boolean temp2 = true;
        if  (grid[st_x - 1][st_y] == '[') {
            temp1 = moveRockUp(grid, st_x - 1, st_y + 1);
        }
        if  (grid[st_x - 1][st_y - 1] == ']') {
            temp2 = moveRockUp(grid, st_x - 1, st_y - 1);
        }
        if (temp1 && temp2) {
            grid[st_x - 1][st_y] = ']'; grid[st_x - 1][st_y - 1] = '[';
            grid[st_x][st_y] = '.'; grid[st_x][st_y - 1] = '.';
            return true;
        }
        return false;
    }

    public static boolean moveRockDown(char[][] grid, int st_x, int st_y) { // st_y is ']'
        if (st_x < 0 || st_y < 0 || st_x >= grid.length || st_y >= grid[0].length) {
            return false;
        }
        if (st_x + 1 >= grid.length) {
            return false;
        }
        if  (grid[st_x + 1][st_y] == '.' && grid[st_x + 1][st_y - 1] == '.') {
            grid[st_x + 1][st_y] = ']'; grid[st_x + 1][st_y - 1] = '[';
            grid[st_x][st_y] = '.'; grid[st_x][st_y - 1] = '.';
            return true;
        }
        if (grid[st_x + 1][st_y] == '#' || grid[st_x + 1][st_y - 1] == '#') {
            return false;
        }
        if  (grid[st_x + 1][st_y] == ']' && grid[st_x + 1][st_y - 1] == '[') {
            boolean temp = moveRockDown(grid, st_x + 1, st_y);
            if (temp){
                grid[st_x + 1][st_y] = ']'; grid[st_x + 1][st_y - 1] = '[';
                grid[st_x][st_y] = '.'; grid[st_x][st_y - 1] = '.';
                return true;
            }
            return false;
        }
        boolean temp1 = true;
        boolean temp2 = true;
        if  (grid[st_x + 1][st_y] == '[') {
            temp1 = moveRockDown(grid, st_x + 1, st_y + 1);
        }
        if  (grid[st_x + 1][st_y - 1] == ']') {
            temp2 = moveRockDown(grid, st_x + 1, st_y - 1);
        }
        if (temp1 && temp2) {
            grid[st_x + 1][st_y] = ']'; grid[st_x + 1][st_y - 1] = '[';
            grid[st_x][st_y] = '.'; grid[st_x][st_y - 1] = '.';
            return true;
        }
        return false;
    }

    public static char[][] copyGrid(char[][] grid) {
        char[][] new_grid = new char[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                new_grid[i][j] = grid[i][j];
            }
        }
        return new_grid;
    }

    public static void copyNewGrid(char[][] grid, char[][] new_grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                new_grid[i][j] = grid[i][j];
            }
        }
    }

    public static int[] execMove(char[][] grid, char move, int st_x, int st_y) {
        int[] new_st = {st_x, st_y};
        int temp_x = 0;
        int temp_y = 0;
        if (move == '^') {
            temp_x = st_x - 1;
            temp_y = st_y;
            if (temp_x >= 0 && grid[temp_x][st_y] != '#') {
                char[][] cgrid = copyGrid(grid);
                if (grid[temp_x][st_y] == ']') {
                    boolean result = moveRockUp(cgrid, temp_x, st_y);
                    if (!result) {return new_st;} 
                    copyNewGrid(cgrid, grid);
                }
                if (grid[temp_x][st_y] == '[') {
                    boolean result = moveRockUp(cgrid, temp_x, st_y + 1);
                    if (!result) {return new_st;} 
                    copyNewGrid(cgrid, grid);
                }
                new_st[0] = temp_x;
                new_st[1] = temp_y;  
                grid[st_x][st_y] = '.';
                grid[temp_x][st_y] = '@';
                
            }
        }
        if (move == 'v') {
            temp_x = st_x + 1;
            temp_y = st_y;
            if (temp_x < grid.length && grid[temp_x][st_y] != '#') {
                char[][] cgrid = copyGrid(grid);
                if (grid[temp_x][st_y] == ']') {
                    boolean result = moveRockDown(cgrid, temp_x, st_y);
                    if (!result) {return new_st;}
                    copyNewGrid(cgrid, grid);
                }
                if (grid[temp_x][st_y] == '[') {
                    boolean result = moveRockDown(cgrid, temp_x, st_y + 1);
                    if (!result) {return new_st;} 
                    copyNewGrid(cgrid, grid);
                }
                new_st[0] = temp_x;
                new_st[1] = temp_y;  
                grid[st_x][st_y] = '.';
                grid[temp_x][st_y] = '@';
                
            }
        }
        if (move == '<') {
            temp_x = st_x;
            temp_y = st_y - 1;
            if (temp_y >= 0 && grid[st_x][temp_y] != '#') {
                if (grid[st_x][temp_y] == ']') {
                    int cp = temp_y;
                    boolean flag_found = false;
                    while (cp >= 0) {
                        if (grid[st_x][cp] == '#') {
                            return new_st;
                        }
                        if (grid[st_x][cp] == '.') {
                            flag_found = true;
                            break;
                        }
                        cp--;
                    }
                    if (!flag_found) {
                        return new_st;
                    }
                    // swap chars from cp to temp_y
                    for (int i = cp; i < temp_y; i++) {
                        grid[st_x][i] = grid[st_x][i+1];
                    }
                }
                new_st[0] = temp_x;
                new_st[1] = temp_y;  
                grid[st_x][st_y] = '.';
                grid[st_x][temp_y] = '@';
            }
        }
        if (move == '>') {
            temp_x = st_x;
            temp_y = st_y + 1;
            if (temp_y < grid[0].length && grid[st_x][temp_y] != '#') {
                if (grid[st_x][temp_y] == '[') {
                    int cp = temp_y;
                    boolean flag_found = false;
                    while (cp < grid[0].length ) {
                        if (grid[st_x][cp] == '#') {
                            return new_st;
                        }
                        if (grid[st_x][cp] == '.') {
                            flag_found = true;
                            break;
                        }
                        
                        cp++;
                    }
                    if (!flag_found) {
                        return new_st;
                    }
                    // swap chars from cp to temp_y
                    for (int i = cp; i > temp_y; i--) {
                        grid[st_x][i] = grid[st_x][i - 1];
                    }
                }
                new_st[0] = temp_x;
                new_st[1] = temp_y;  
                grid[st_x][st_y] = '.';
                grid[st_x][temp_y] = '@';
            }
        }
        return new_st;
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<char[]> arr = new ArrayList<char[]>();
        int nrow = 0;
        int ncol = 0;
        while (sc.hasNextLine()) {
            String cline = sc.nextLine();
            if (cline.equals("")) {
                break;
            }
            nrow++;
            String[] line = cline.split("");
            ncol = line.length;
            char[] row = new char[line.length];
            for (int i = 0; i < line.length; i++) {
                row[i] = line[i].charAt(0);
            }
            arr.add(row);
        }
        ncol = 2*ncol;
        int st_x = 0;
        int st_y = 0;
        char[][] grid = new char[nrow][ncol];
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j = j + 2) {
                char c = arr.get(i)[j/2];
                if (c == '#') {
                    grid[i][j] = '#';
                    grid[i][j + 1] = '#';
                } else if (c == 'O') {
                    grid[i][j] = '[';
                    grid[i][j + 1] = ']';
                } else if (c == '.') {
                    grid[i][j] = '.';
                    grid[i][j + 1] = '.';
                } else if (c == '@') {
                    grid[i][j] = '@';
                    grid[i][j + 1] = '.';
                    st_x = i;
                    st_y = j;
                }
            }
        }
        System.out.println(st_x + " " + st_y);
        ArrayList<Character> moves = new ArrayList<Character>();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            for (int i = 0; i < s.length(); i++) {
                moves.add(s.charAt(i));
            }
                
        }
        long res = 0;
        for (char move : moves) {
            int[] new_st = execMove(grid, move, st_x, st_y);
            System.out.println(move);
            // printGrid(grid);
            st_x = new_st[0];
            st_y = new_st[1];
            System.out.println(st_x + " " + st_y);
        }
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                if (grid[i][j] == '[') {
                    res += 100*i + j;
                }
            }
        }
        System.out.println(res);
        sc.close();
    }
}