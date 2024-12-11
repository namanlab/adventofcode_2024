import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;

public class d4p2 {

    public static long searchSt(char[][] arr, int i, int j) {
        if (i < 1 || i >= arr.length - 1 || j < 1 || j >= arr[0].length - 1) {
            return 0; // Ensure we're not at the edges.
        }
        int counter = 0;
        if (arr[i][j] == 'A') {
            if ((arr[i - 1][j - 1] == 'M' && arr[i + 1][j + 1] == 'S') ||
                (arr[i - 1][j - 1] == 'S' && arr[i + 1][j + 1] == 'M') ){
                counter += 1;
            }
            if ((arr[i - 1][j + 1] == 'M' && arr[i + 1][j - 1] == 'S') ||
                (arr[i - 1][j + 1] == 'S' && arr[i + 1][j - 1] == 'M') ){
                counter += 1;
            }
        }
        if (counter == 2) {
            return 1;
        }
        return 0;
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