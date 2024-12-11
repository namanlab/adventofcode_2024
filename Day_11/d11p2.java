import java.math.BigInteger;
import java.util.*;

public class d11p2 {

    public static void insert(HashMap<BigInteger, Long> hm, BigInteger key, long value) {
        if (hm.containsKey(key)) {
            hm.put(key, hm.get(key) + value);
        } else {
            hm.put(key, value);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] arr = s.split(" ");
        HashMap<BigInteger, Long> hm = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            BigInteger key = new BigInteger(arr[i]);
            if (hm.containsKey(key)) {
                hm.put(key, hm.get(key) + 1);
            } else {
                hm.put(key, 1L);
            }
        }
        for (int j = 0; j < 75; j++) {
            List<BigInteger> l = new ArrayList<>(hm.keySet());
            HashMap<BigInteger, Long> hm2 = new HashMap<>();
            for (BigInteger i : l) {
                long cnt = hm.get(i);
                if (i.equals(BigInteger.ZERO)) {
                    insert(hm2, BigInteger.ONE, cnt);
                } else {
                    String s1 = i.toString();
                    if (s1.length() % 2 == 0) {
                        insert(hm2, new BigInteger(s1.substring(0, s1.length() / 2)), cnt);
                        insert(hm2, new BigInteger(s1.substring(s1.length() / 2)), cnt);
                    } else {
                        insert(hm2, i.multiply(BigInteger.valueOf(2024)), cnt);
                    }
                }
            }
            hm = hm2;
            BigInteger res = BigInteger.ZERO;
            for (BigInteger i : hm.keySet()) {
                res = res.add(BigInteger.valueOf(hm.get(i)));
            }
            System.out.println(j + 1 + ", " + res);
        }

        sc.close();
    }
}