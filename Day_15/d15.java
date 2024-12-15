import java.util.*;


public class d15 {

    public static int[] execMove(char[][] grid, char move, int st_x, int st_y) {
        int[] new_st = {st_x, st_y};
        int temp_x = 0;
        int temp_y = 0;
        if (move == '^') {
            temp_x = st_x - 1;
            temp_y = st_y;
            if (temp_x >= 0 && grid[temp_x][st_y] != '#') {
                if (grid[temp_x][st_y] == 'O') {
                    for (int cp = temp_x; cp >= 0; cp--) {
                        if (grid[cp][st_y] == '#') {return new_st;}
                        if (grid[cp][st_y] == '.') {grid[cp][st_y] = 'O'; break;}
                    }
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
                if (grid[temp_x][st_y] == 'O') {
                    for (int cp = temp_x; cp < grid.length; cp++) {
                        if (grid[cp][st_y] == '#') {return new_st;}
                        if (grid[cp][st_y] == '.') {grid[cp][st_y] = 'O'; break;}
                    }
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
                if (grid[st_x][temp_y] == 'O') {
                    for (int cp = temp_y; cp >= 0; cp--) {
                        if (grid[st_x][cp] == '#') {return new_st;}
                        if (grid[st_x][cp] == '.') {grid[st_x][cp] = 'O'; break;}
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
                if (grid[st_x][temp_y] == 'O') {
                    for (int cp = temp_y; cp < grid[0].length; cp++) {
                        if (grid[st_x][cp] == '#') {return new_st;}
                        if (grid[st_x][cp] == '.') {grid[st_x][cp] = 'O'; break;}
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
        int st_x = 0;
        int st_y = 0;
        char[][] grid = new char[nrow][ncol];
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                grid[i][j] = arr.get(i)[j];
                if (grid[i][j] == '@') {
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
            System.out.println(st_x + " " + st_y);
            st_x = new_st[0];
            st_y = new_st[1];
        }
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                if (grid[i][j] == 'O') {
                    res += 100*i + j;
                }
            }
        }
        System.out.println(res);
        sc.close();
    }
}