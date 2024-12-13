import java.util.*;

public class d13p2 {

    public static long getCost(long Ax, long Ay, long Bx, long By, long tx, long ty) {
        System.out.println("Ax: " + Ax + " Ay: " + Ay + " Bx: " + Bx + " By: " + By + " tx: " + tx + " ty: " + ty);
        long c = 0;
        long determinant = Ax * By - Ay * Bx;
        if (determinant != 0) {
            long R = (tx * By - ty * Bx) / determinant;
            long S = (Ax * ty - Ay * tx) / determinant;
            if (Ax * R + Bx * S != tx || Ay * R + By * S != ty) {
                R = 0;
                S = 0;
            }
            if (R < 0 || S < 0) {
                R = 0;
                S = 0;
            }
            c = 3 * R + S;
            System.out.println("R: " + R + " S: " + S + " c: " + c);
        } else {
            c = 0;
            System.out.println("c: " + c);
        }
        return c;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long Ax = 0;
        long Ay = 0;
        long Bx = 0;
        long By = 0;
        long tx = 0;
        long ty = 0;
        long res = 0;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] s1 = s.split(" ");
            Ax = Long.parseLong(s1[2].split("\\+")[1].split(",")[0]);
            Ay = Long.parseLong(s1[3].split("\\+")[1]);
            s = sc.nextLine();
            s1 = s.split(" ");
            Bx = Long.parseLong(s1[2].split("\\+")[1].split(",")[0]);
            By = Long.parseLong(s1[3].split("\\+")[1]);
            s = sc.nextLine();
            s1 = s.split(" ");
            tx = Long.parseLong(s1[1].split("=")[1].split(",")[0]) + 10000000000000L;
            ty = Long.parseLong(s1[2].split("=")[1]) + 10000000000000L;
            res += getCost(Ax, Ay, Bx, By, tx, ty);
            if (!sc.hasNextLine()) {
                break;
            }
            sc.nextLine();
        }
        System.out.println(res);
        sc.close();
    }
}