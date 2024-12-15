import java.util.*;


public class d14 {

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
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] s1 = s.split(" ");
            String[] p = s1[0].split("\\=")[1].split(",");
            String[] v = s1[1].split("\\=")[1].split(",");
            px = Integer.parseInt(p[0]);
            py = Integer.parseInt(p[1]);
            vx = Integer.parseInt(v[0]);
            vy = Integer.parseInt(v[1]);
            int dx = 101;
            int dy = 103;
            int[] final_pos = getPos(px, py, vx, vy, 100, dx, dy);
            System.out.println(Arrays.toString(final_pos) + " " + px + " " + py + " " + vx + " " + vy);
            int midX = dx / 2;
            int midY = dy / 2;
            if (final_pos[0] == midX && final_pos[1] == midY) {
                continue;
            } else if (final_pos[0] < midX && final_pos[1] < midY) {
                res[0]++;
            } else if (final_pos[0] > midX && final_pos[1] < midY) {
                res[1]++;
            } else if (final_pos[0] < midX && final_pos[1] > midY) {
                res[2]++;
            } else if (final_pos[0] > midX && final_pos[1] > midY) {
                res[3]++;
            }

        }
        System.out.println(Arrays.toString(res));
        long fin = 1;
        for (int i = 0; i < 4; i++) {
            fin *= res[i];
        }
        System.out.println(fin);
        sc.close();
    }
}