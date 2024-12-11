import java.util.*;

public class d6p2 {

    public static int getDirIndex(int[] dir){
        if (dir[0] == 0 && dir[1] == 1) {
            return 0;
        } else if (dir[0] == 1 && dir[1] == 0) {
            return 1;
        } else if (dir[0] == -1 && dir[1] == 0) {
            return 2;
        } else {
            return 3;
        }
    }

    public static int traverse(char[][] arr, int[] st, int[] curDir, int[][][] visited){
        
        int new_x = st[0] + curDir[0];
        int new_y = st[1] + curDir[1];

        // System.out.println("Traversing from " + st[0] + " " + st[1] + " in direction " + curDir[0] + " " + curDir[1] + " at " + arr[st[0]][st[1]] + "giving" + new_x + " " + new_y);
        if (new_x < 0 || new_x >= arr.length || new_y < 0 || new_y >= arr[0].length ) {
            return 0;
        } 
        int dir_index = getDirIndex(curDir);
        if (arr[new_x][new_y] != '#' && visited[new_x][new_y][dir_index] == 1) {
            // printVisited(visited);
            return 1;
        }
        else {
            if (arr[new_x][new_y] == '#'){
                // System.out.println("Hit wall at " + new_x + " " + new_y);
                curDir = new int[] {curDir[1], -curDir[0]};
                dir_index = getDirIndex(curDir);
                visited[st[0]][st[1]][dir_index] = 1;
                return traverse(arr, st, curDir, visited);
            } else {
                dir_index = getDirIndex(curDir);
                visited[new_x][new_y][dir_index] = 1;
                st[0] = new_x;
                st[1] = new_y;
                return traverse(arr, st, curDir, visited);
            }

        }
    }

    public static void printVisited(int[][][] visited){
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                for (int k = 0; k < visited[0][0].length; k++) {
                    System.out.print(i + "," + j + "," + k + ":::" + visited[i][j][k] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<List<String>> arrl = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split("");
            List<String> curArr = Arrays.asList(line);
            arrl.add(curArr);
        }
        int[] mainst = new int[2];
        char[][] arr = new char[arrl.size()][arrl.get(0).size()];
        for (int i = 0; i < arrl.size(); i++) {
            for (int j = 0; j < arrl.get(i).size(); j++) {
                arr[i][j] = arrl.get(i).get(j).charAt(0);
                if (arr[i][j] == '^') {
                    System.out.println("X at " + i + " " + j);
                    mainst[0] = i; mainst[1] = j;
                }
            }
        }
        int[] st = new int[2]; st[0] = mainst[0]; st[1] = mainst[1];
        int[][] dir = new int[][] { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
        long res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == '#' || arr[i][j] == '^') {
                    continue;
                }
                arr[i][j] = '#';
                int[][][] visited = new int[arr.length][arr[0].length][4];
                st[0] = mainst[0]; st[1] = mainst[1];
                int[] curDir = dir[2];
                visited[st[0]][st[1]][getDirIndex(curDir)] = 1;
                int temp = traverse(arr, st, curDir, visited);
                // System.out.println("For " + i + " " + j + " " + temp);
                res += temp;
                arr[i][j] = '.';
            }
        }
        System.out.println(res);
        sc.close();
    }
}