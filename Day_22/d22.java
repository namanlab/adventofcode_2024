import java.util.*;



public class d22 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long res = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            long curNum = Long.parseLong(line);
            long outp = getRes(curNum);
            System.out.println(outp);
            res += outp;
        }
        System.out.println(res);
        sc.close();
    }

    public static long getRes(long num) {
        long temp = num;
        for (int i = 0; i < 2000; i++) {
            temp = getResHelper(temp);
        }
        return temp;
    }

    public static long getResHelper(long num) {
        // Multiply the secret number by 64, mix, and prune
        num = (num * 64) ^ num;
        num = num % 16777216;

        // Divide the secret number by 32, mix, and prune
        num = (num / 32) ^ num;
        num = num % 16777216;

        // Multiply the secret number by 2048, mix, and prune
        num = (num * 2048) ^ num;
        num = num % 16777216;

        return num;
    }
}