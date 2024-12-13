import java.util.*;


public class d13 {

    public static long getCost(int Ax, int Ay, int Bx, int By, int tx, int ty) {
        System.out.println("Ax: " + Ax + " Ay: " + Ay + " Bx: " + Bx + " By: " + By + " tx: " + tx + " ty: " + ty);
        int c=0;
        int determinant = Ax * By - Ay * Bx;
        if (determinant != 0) {
            int R = (tx * By - ty * Bx) / determinant;
            int S = (Ax * ty - Ay * tx) / determinant;
            if (Ax*R + Bx*S != tx || Ay*R + By*S != ty) {
                R = 0;
                S = 0;
            } 
            if (R < 0 || S < 0 || R > 100 || S > 100) {R = 0; S = 0;}
            c = 3*R + S;
            System.out.println("R: " + R + " S: " + S + " c: " + c);
        } else {
            c = 0;
            System.out.println("c: " + c);
        }
        return c;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int Ax = 0;
        int Ay = 0;
        int Bx = 0;
        int By = 0;
        int tx = 0;
        int ty = 0;
        long res = 0;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] s1 = s.split(" ");
            Ax = Integer.parseInt(s1[2].split("\\+")[1].split(",")[0]);
            Ay = Integer.parseInt(s1[3].split("\\+")[1]);
            s = sc.nextLine();
            s1 = s.split(" ");
            Bx = Integer.parseInt(s1[2].split("\\+")[1].split(",")[0]);
            By = Integer.parseInt(s1[3].split("\\+")[1]);
            s = sc.nextLine();
            s1 = s.split(" ");
            tx = Integer.parseInt(s1[1].split("=")[1].split(",")[0]);
            ty = Integer.parseInt(s1[2].split("=")[1]);
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