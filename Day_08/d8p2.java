import java.util.*;


public class d8p2 {

    public static HashSet<String> visited = new HashSet<>();

    public static int checker(int[] p, int nrow, int ncol) {
        if (p[0] < 0 || p[0] >= nrow || p[1] < 0 || p[1] >= ncol) {
            return 0;
        }
        if (visited.contains(p[0] + " " + p[1])) {
            return 0;
        } else {
            visited.add(p[0] + " " + p[1]);
        }
        return 1;
    }

    public static int getCount(ArrayList<int[]> arr, int nrow, int ncol) {
        int res = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                int[] p1 = arr.get(i);
                int[] p2 = arr.get(j);
                int x1 = p1[0];
                int y1 = p1[1];
                int x2 = p2[0];
                int y2 = p2[1];
                int dx = x1 - x2;
                int dy = y1 - y2;
                for (int k = 0; k < Math.min(nrow, ncol); k++) {
                    int[] p1n = new int[] { x1 + k*dx, y1 + k*dy };
                    int[] p2n = new int[] { x2 - k*dx, y2 - k*dy };
                    res += checker(p1n, nrow, ncol) + checker(p2n, nrow, ncol);
                }
                
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Character, ArrayList<int[]>> hm = new HashMap<>();
        int nrow = 0;
        int ncol = 0;
        long res = 0;
        while (sc.hasNextLine()) {
            nrow++;
            String[] line = sc.nextLine().split("");
            ncol = line.length;
            for (int i = 0; i < line.length; i++) {
                if (!line[i].equals(".")) {
                    if (!hm.containsKey(line[i].charAt(0))) {
                        hm.put(line[i].charAt(0), new ArrayList<int[]>());
                    }
                    hm.get(line[i].charAt(0)).add(new int[] { nrow - 1, i });
                }
            }
        }
        for (char c : hm.keySet()) {
            ArrayList<int[]> arr = hm.get(c);
            res += getCount(arr, nrow, ncol);
        }
        System.out.println(res);
        sc.close();
    }
}