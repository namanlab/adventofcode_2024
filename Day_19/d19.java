import java.util.*;

public class d19 {

    public static HashMap<String, Boolean> memo = new HashMap<>();

    public static boolean check(String s) {
        // If the result is already cached, return it
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        
        // Try splitting the string and checking both parts
        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            String suffix = s.substring(i);
            
            if (memo.containsKey(prefix) && memo.get(prefix) && check(suffix)) {
                memo.put(s, true); // Cache the result
                return true;
            }
        }

        // If no combination works, mark as impossible
        memo.put(s, false);
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read available towel patterns
        String s = sc.nextLine();
        String[] arr = s.split(", ");
        for (String str : arr) {
            memo.put(str, true);
        }

        // Read desired designs
        sc.nextLine();
        long res = 0;
        while (sc.hasNextLine()) {
            s = sc.nextLine().trim();
            if (s.isEmpty()) continue;
            boolean temp = check(s);
            System.out.println(s + " " + temp);
            res += temp ? 1 : 0;
        }

        System.out.println(res);
        sc.close();
    }
}