import java.util.*;

public class d5p2 {

    public static void modSort(ArrayList<Integer> clineInt, ArrayList<int[]> arrl) {
        for (int[] arr : arrl) {
            int x = arr[0];
            int y = arr[1];
            int px = clineInt.indexOf(x);
            int py = clineInt.indexOf(y);
            // System.out.println(x + " " + y);
            // System.out.println(px + " " + py);
            if (px != -1 && py != -1) {
                if (px > py) {
                    clineInt.set(px, y);
                    clineInt.set(py, x);
                    modSort(clineInt, arrl);
                }
            }
        } 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<int[]> arrl = new ArrayList<>();
        while (sc.hasNextLine()) {
            String tline = sc.nextLine();
            if (tline.equals("")) {
                break;
            }
            String[] line = tline.split("\\|");
            int[] curArr = new int[2];
            curArr[0] = Integer.parseInt(line[0]);
            curArr[1] = Integer.parseInt(line[1]);
            arrl.add(curArr);
        }
        long res = 0;
        while (sc.hasNextLine()) {
            String[] cline = sc.nextLine().split(",");
            ArrayList<Integer> clineInt = new ArrayList<>();
            for (int i = 0; i < cline.length; i++) {
                clineInt.add(Integer.parseInt(cline[i]));
            }
            boolean checker = true;
            for (int[] arr : arrl) {
                int x = arr[0];
                int y = arr[1];
                int px = clineInt.indexOf(x);
                int py = clineInt.indexOf(y);
                // System.out.println(x + " " + y);
                // System.out.println(px + " " + py);
                if (px != -1 && py != -1) {
                    if (px > py) {
                        checker = false;
                        clineInt.set(px, y);
                        clineInt.set(py, x);
                    }
                }
            }   
            if (!checker) {
                // System.out.println(clineInt.get(clineInt.size() / 2));
                modSort(clineInt, arrl);
                // System.out.println(clineInt);
                res += clineInt.get(clineInt.size() / 2);
            }
        }
        System.out.println(res);
        sc.close();
    }
}