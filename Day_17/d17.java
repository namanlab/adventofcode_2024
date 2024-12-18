import java.util.*;


public class d17 {

    public static int getCombo(int operand, int A, int B, int C) {
        int value = operand;
        switch (operand) {
            case 4:
                value = A;
                break;
            case 5:
                value = B;
                break;
            case 6:
                value = C;
                break;
        }
        return value;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = 0;
        int B = 0;
        int C = 0;
        ArrayList<Integer> progs = new ArrayList<Integer>();
        String s = sc.nextLine();
        String[] s1 = s.split(": ");
        A = Integer.parseInt(s1[1]);
        s = sc.nextLine();
        s1 = s.split(": ");
        B = Integer.parseInt(s1[1]);
        s = sc.nextLine();
        s1 = s.split(": ");
        C = Integer.parseInt(s1[1]);
        sc.nextLine();
        s = sc.nextLine();
        s1 = s.split(": ");
        String[] s2 = s1[1].split(",");
        for (int i = 0; i < s2.length; i++) {
            progs.add(Integer.parseInt(s2[i]));
        }
        int i = 0;
        while (i < progs.size()) {
            int opcode = progs.get(i);
            int operand = progs.get(i + 1);
            // System.out.println(i + " " + opcode + " " + operand + " " + A + " " + B + " " + C);
            switch (opcode) {
                case 0: // adv
                    A = A / (int) Math.pow(2, getCombo(operand, A, B, C));
                    break;
                case 1: // bxl
                    B = B ^ operand;
                    break;
                case 2: // bst
                    B = getCombo(operand, A, B, C) % 8;
                    break;
                case 3: // jnz
                    if (A != 0) {
                        i = operand - 2;
                    }
                    break;
                case 4: // bxc
                    B = B ^ C;
                    break;
                case 5: // out
                    System.out.print(getCombo(operand, A, B, C) % 8 + ",");
                    break;
                case 6: // bdv
                    B = A / (int) Math.pow(2, getCombo(operand, A, B, C));
                    break;
                case 7: // cdv
                    C = A / (int) Math.pow(2, getCombo(operand, A, B, C));
                    break;
            }
            i += 2;
        }

        sc.close();
    }
}