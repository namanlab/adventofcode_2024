import java.util.*;

public class d6 {

    public static void traverse(char[][] arr, int[] st, int[] curDir, int[][] visited){
        // System.out.println("Traversing from " + st[0] + " " + st[1] + " in direction " + curDir[0] + " " + curDir[1] + " at " + arr[st[0]][st[1]]);
        int new_x = st[0] + curDir[0];
        int new_y = st[1] + curDir[1];
        if (new_x < 0 || new_x >= arr.length || new_y < 0 || new_y >= arr[0].length ) {
            return;
        } else {
            if (arr[new_x][new_y] == '#'){
                // System.out.println("Hit wall at " + new_x + " " + new_y);
                curDir = new int[] {curDir[1], -curDir[0]};
                traverse(arr, st, curDir, visited);
            } else {
                visited[new_x][new_y] = 1;
                st[0] = new_x;
                st[1] = new_y;
                traverse(arr, st, curDir, visited);
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
        int[] st = new int[2];
        char[][] arr = new char[arrl.size()][arrl.get(0).size()];
        for (int i = 0; i < arrl.size(); i++) {
            for (int j = 0; j < arrl.get(i).size(); j++) {
                arr[i][j] = arrl.get(i).get(j).charAt(0);
                if (arr[i][j] == '^') {
                    System.out.println("X at " + i + " " + j);
                    st[0] = i; st[1] = j;
                }
            }
        }
        int[][] dir = new int[][] { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
        int[][] visited = new int[arr.length][arr[0].length];
        int[] curDir = dir[2];
        traverse(arr, st, curDir, visited);
        visited[st[0]][st[1]] = 1;
        long res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                res += visited[i][j] == 1 ?  1: 0;
            }
        }
        System.out.println(res);
        sc.close();
    }
}