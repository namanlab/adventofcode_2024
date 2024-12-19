import java.util.*;

public class d19p2 {

    public static HashMap<String, Long> memo = new HashMap<>();
    public static HashSet<String> towelPatterns = new HashSet<>();

    public static long countWays(String s) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        if (s.isEmpty()) {
            return 1;
        }

        long totalWays = 0;

        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            String suffix = s.substring(i);

            if (towelPatterns.contains(prefix)) {
                totalWays += countWays(suffix);
            }
        }

        memo.put(s, totalWays);
        return totalWays;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] patterns = s.split(", ");
        Collections.addAll(towelPatterns, patterns);
        sc.nextLine();
        List<String> designs = new ArrayList<>();
        while (sc.hasNextLine()) {
            s = sc.nextLine().trim();
            if (!s.isEmpty()) {
                designs.add(s);
            }
        }

        long totalArrangements = 0;
        for (String design : designs) {
            long ways = countWays(design);
            System.out.println(design + ": " + ways);
            totalArrangements += ways;
        }

        System.out.println(totalArrangements);
        sc.close();
    }
}