import java.util.*;

public class d21p2 {

    // Define the keyboard layout
    private static final Map<Character, long[]> keyp = new HashMap<>();
    private static final Map<Character, long[]> dirp = new HashMap<>();

    static {
        // Populate key positions
        String keys = "789456123 0A";
        for (int i = 0; i < keys.length(); i++) {
            char c = keys.charAt(i);
            keyp.put(c, new long[]{i % 3, i / 3});
        }

        // Populate direction positions
        String dirs = " ^A<v>";
        for (int i = 0; i < dirs.length(); i++) {
            char c = dirs.charAt(i);
            dirp.put(c, new long[]{i % 3, i / 3});
        }
    }

    // Read the input codes from file
    private static List<String> codes;

    static {
        codes = new ArrayList<>();
        try (Scanner scanner = new Scanner(new java.io.File("input.txt"))) {
            while (scanner.hasNextLine()) {
                codes.add(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Compute the steps
    private static Map<String, Long> computeSteps(Map<Character, long[]> grid, String sequence, long multiplier) {
        long px = grid.get('A')[0], py = grid.get('A')[1];
        long bx = grid.get(' ')[0], by = grid.get(' ')[1];
        Map<String, Long> result = new HashMap<>();

        for (char c : sequence.toCharArray()) {
            long[] nextPos = grid.get(c);
            long npx = nextPos[0], npy = nextPos[1];
            boolean flag = (npx == bx && py == by) || (npy == by && px == bx);

            // Use a unique string to represent the state
            String key = (npx - px) + "," + (npy - py) + "," + flag;
            result.put(key, result.getOrDefault(key, 0L) + multiplier);

            px = npx;
            py = npy;
        }
        return result;
    }

    // Recursive computation
    private static long computeResult(long n) {
        long total = 0;

        for (String code : codes) {
            Map<String, Long> stepsMap = computeSteps(keyp, code, 1);

            for (long i = 0; i <= n; i++) {
                Map<String, Long> newStepsMap = new HashMap<>();

                for (Map.Entry<String, Long> entry : stepsMap.entrySet()) {
                    String[] parts = entry.getKey().split(",");
                    long dx = Long.parseLong(parts[0]);
                    long dy = Long.parseLong(parts[1]);
                    boolean flag = Boolean.parseBoolean(parts[2]);
                    long count = entry.getValue();

                    String directions = (repeat('<', -dx) + repeat('v', dy) + repeat('^', -dy) + repeat('>', dx));
                    if (flag) directions = new StringBuilder(directions).reverse().toString();
                    directions += "A";

                    Map<String, Long> tempSteps = computeSteps(dirp, directions, count);
                    for (Map.Entry<String, Long> tempEntry : tempSteps.entrySet()) {
                        newStepsMap.put(tempEntry.getKey(), newStepsMap.getOrDefault(tempEntry.getKey(), 0L) + tempEntry.getValue());
                    }
                }
                stepsMap = newStepsMap;
            }

            long multiplier = Long.parseLong(code.substring(0, 3));
            total += stepsMap.values().stream().mapToLong(Long::longValue).sum() * multiplier;
        }

        return total;
    }

    // Utility to repeat a character n times
    private static String repeat(char c, long n) {
        if (n <= 0) return "";
        return String.valueOf(c).repeat((int) n);
    }

    public static void main(String[] args) {
        System.out.println(computeResult(2));
        System.out.println(computeResult(25));
    }
}