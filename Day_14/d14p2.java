import java.util.*;


public class d14p2 {

    public static int[] getPos(int px, int py, int vx, int vy, int t, int dx, int dy) {
        int[] pos = new int[2];
        pos[0] = px + vx * t;
        pos[1] = py + vy * t;
        pos[0] = pos[0] % dx;
        pos[1] = pos[1] % dy;  
        if (pos[0] < 0) {
            pos[0] += dx;
        }
        if (pos[1] < 0) {
            pos[1] += dy;
        }
        return pos;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int px = 0;
        int py = 0;
        int vx = 0;
        int vy = 0;
        long[] res = new long[4];
        ArrayList<int[]> positions = new ArrayList<int[]>();
        ArrayList<int[]> velocities = new ArrayList<int[]>();
        int dx = 101;
        int dy = 103;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] s1 = s.split(" ");
            String[] p = s1[0].split("\\=")[1].split(",");
            String[] v = s1[1].split("\\=")[1].split(",");
            px = Integer.parseInt(p[0]);
            py = Integer.parseInt(p[1]);
            vx = Integer.parseInt(v[0]);
            vy = Integer.parseInt(v[1]);
            positions.add(new int[]{px, py});
            velocities.add(new int[]{vx, vy});
        }
        int num_iter = dx*dy;
        double[] res_var = new double[num_iter];
        for (int t = 0; t < num_iter; t++) {
            ArrayList<Integer> pos_x = new ArrayList<Integer>();
            ArrayList<Integer> pos_y = new ArrayList<Integer>();
            for (int i = 0; i < positions.size(); i++) {
                int[] cur = getPos(positions.get(i)[0], positions.get(i)[1], velocities.get(i)[0], velocities.get(i)[1], t, dx, dy);
                pos_x.add(cur[0]);
                pos_y.add(cur[1]);
            }
            double mean_x = pos_x.stream().mapToInt(Integer::intValue).average().orElse(0.0);
            double mean_y = pos_y.stream().mapToInt(Integer::intValue).average().orElse(0.0);
            double variance_x = pos_x.stream().mapToDouble(x -> Math.pow(x - mean_x, 2)).average().orElse(0.0);
            double variance_y = pos_y.stream().mapToDouble(y -> Math.pow(y - mean_y, 2)).average().orElse(0.0);
            res_var[t] = variance_x + variance_y;
        }
        int min_idx = 0;
        double min_val = res_var[0];
        for (int i = 1; i < res_var.length; i++) {
            if (res_var[i] < min_val) {
                min_val = res_var[i];
                min_idx = i;
            }
        }
        System.out.println(min_idx);
        sc.close();
    }
}