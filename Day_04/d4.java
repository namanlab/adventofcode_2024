import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;

public class d4 {

    public static long searchSt(char[][] arr, int i, int j) {
        long res = 0;

        // up
        if (i >= 3 && arr[i][j] == 'X' && arr[i - 1][j] == 'M' && arr[i - 2][j] == 'A' && arr[i - 3][j] == 'S') {
            res += 1;
        }
        // down
        if (i <= arr.length - 4 && arr[i][j] == 'X' && arr[i + 1][j] == 'M' && arr[i + 2][j] == 'A' && arr[i + 3][j] == 'S') {
            res += 1;
        }
        // left
        if (j >= 3 && arr[i][j] == 'X' && arr[i][j - 1] == 'M' && arr[i][j - 2] == 'A' && arr[i][j - 3] == 'S') {
            res += 1;
        }
        // right
        if (j <= arr[0].length - 4 && arr[i][j] == 'X' && arr[i][j + 1] == 'M' && arr[i][j + 2] == 'A' && arr[i][j + 3] == 'S') {
            res += 1;
        }
        // down-right diagonal
        if (i <= arr.length - 4 && j <= arr[0].length - 4 && arr[i][j] == 'X' && arr[i + 1][j + 1] == 'M' && arr[i + 2][j + 2] == 'A' && arr[i + 3][j + 3] == 'S') {
            res += 1;
        }
        // up-left diagonal
        if (i >= 3 && j >= 3 && arr[i][j] == 'X' && arr[i - 1][j - 1] == 'M' && arr[i - 2][j - 2] == 'A' && arr[i - 3][j - 3] == 'S') {
            res += 1;
        }
        // down-left diagonal
        if (i <= arr.length - 4 && j >= 3 && arr[i][j] == 'X' && arr[i + 1][j - 1] == 'M' && arr[i + 2][j - 2] == 'A' && arr[i + 3][j - 3] == 'S') {
            res += 1;
        }
        // up-right diagonal
        if (i >= 3 && j <= arr[0].length - 4 && arr[i][j] == 'X' && arr[i - 1][j + 1] == 'M' && arr[i - 2][j + 2] == 'A' && arr[i - 3][j + 3] == 'S') {
            res += 1;
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<List<String>> arrl = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split("");
            List<String> curArr = Arrays.asList(line);
            arrl.add(curArr);
        }
        char[][] arr = new char[arrl.size()][arrl.get(0).size()];
        for (int i = 0; i < arrl.size(); i++) {
            for (int j = 0; j < arrl.get(i).size(); j++) {
                arr[i][j] = arrl.get(i).get(j).charAt(0);
            }
        }

        long res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                res += searchSt(arr, i, j);
            }
        }
        System.out.println(res);
        sc.close();
    }
}