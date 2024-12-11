import java.util.*;

public class d7 {

    public static boolean check(long target, int[] dirs) {
        return backtrack(0, 0, target, dirs);
    }
    
    private static boolean backtrack(int index, long current, long target, int[] dirs) {
        if (current == target) {
            return true;
        }
        if (index == dirs.length) {
            return false;
        }
        if (backtrack(index + 1, current + dirs[index], target, dirs)) {
            return true;
        }
        if (backtrack(index + 1, current * dirs[index], target, dirs)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long res = 0;
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(":");
            long target = Long.parseLong(line[0]);
            String[] dirs = line[1].split(" ");
            int[] dirs2 = new int[dirs.length];
            for (int i = 1; i < dirs.length; i++) {
                dirs2[i] = Integer.parseInt(dirs[i]);
            }
            boolean temp = check(target, dirs2);
            res += temp ? target : 0;
        }
        System.out.println(res);
        sc.close();
    }
}