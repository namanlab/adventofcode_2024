import java.util.*;

public class d17p2 {

    public static long getCombo(long operand, long A, long B, long C) {
        if (operand <= 3) return operand;
        switch ((int) operand) {
            case 4:
                return A;
            case 5:
                return B;
            case 6:
                return C;
            default:
                return 7;
        }
    }

    public static ArrayList<Long> runProgram(List<Long> program, long A, long B, long C, boolean oneTime) {
        ArrayList<Long> output = new ArrayList<>();
        int i = 0;

        while (i < program.size()) {
            long opcode = program.get(i);
            long operand = program.get(i + 1);

            switch ((int) opcode) {
                case 0: // adv
                    A = A / (long) Math.pow(2, getCombo(operand, A, B, C));
                    break;
                case 1: // bxl
                    B = B ^ operand;
                    break;
                case 2: // bst
                    B = getCombo(operand, A, B, C) % 8;
                    break;
                case 3: // jnz
                    if (A == 0 || oneTime) {
                        return output;
                    } else {
                        i = (int) operand - 2;
                        break;
                    }
                case 4: // bxc
                    B = B ^ C;
                    break;
                case 5: // out
                    output.add(B % 8);
                    break;
                case 6: // bdv
                    B = A / (long) Math.pow(2, getCombo(operand, A, B, C));
                    break;
                case 7: // cdv
                    C = A / (long) Math.pow(2, getCombo(operand, A, B, C));
                    break;
            }
            i += 2;
        }

        return output;
    }

    public static void findSolutions(List<Long> values, List<Long> program, long a, List<Long> results, int level) {
        long val = values.get(values.size() - level);

        for (int i = 0; i < 8; i++) {
            ArrayList<Long> test = runProgram(program, a + i, 0, 0, true);

            if (!test.isEmpty() && test.get(0) == val) {
                if (level == values.size()) {
                    results.add(a + i);
                } else {
                    findSolutions(values, program, (a + i) * 8, results, level + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read inputs
        long A = 0, B = 0, C = 0;
        List<Long> program = new ArrayList<>();
        List<Long> values = new ArrayList<>();

        String s = sc.nextLine();
        A = Long.parseLong(s.split(": ")[1]);
        s = sc.nextLine();
        B = Long.parseLong(s.split(": ")[1]);
        s = sc.nextLine();
        C = Long.parseLong(s.split(": ")[1]);
        sc.nextLine();
        s = sc.nextLine();
        String[] s1 = s.split(": ");
        String[] s2 = s1[1].split(",");
        for (String value : s2) {
            program.add(Long.parseLong(value));
            values.add(Long.parseLong(value));
        }
        // Part 2: Find smallest A
        List<Long> results = new ArrayList<>();
        findSolutions(values, program, 0, results, 1);

        if (!results.isEmpty()) {
            results.sort(Comparator.naturalOrder());
            System.out.println(results.get(0));
        }

        sc.close();
    }
}