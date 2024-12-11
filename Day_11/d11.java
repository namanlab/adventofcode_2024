import java.util.*;


public class d11 {

    public static void insert(HashMap<Long, Integer> hm, long key, int value) {
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
        HashMap<Long, Integer> hm = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (hm.containsKey(Long.parseLong(arr[i]))) {
                hm.put(Long.parseLong(arr[i]), hm.get(Long.parseLong(arr[i])) + 1);
            } else {
                hm.put(Long.parseLong(arr[i]), 1);
            }
        }
        for (int j = 0; j < 25; j++){
           List<Long> l = new ArrayList<>(hm.keySet());
           HashMap<Long, Integer> hm2 = new HashMap<>();
            for (long i : l) {
                int cnt = hm.get(i);
                if (i == 0){
                    insert(hm2,1L, cnt);
                } else {
                    String s1 = String.valueOf(i);
                    if (s1.length() % 2 == 0) {
                        insert(hm2, Long.parseLong(s1.substring(0, s1.length() / 2)), cnt);
                        insert(hm2, Long.parseLong(s1.substring(s1.length() / 2)), cnt);
                    } else {
                        insert(hm2, i*2024, cnt);
                    }
                } 
            }
            hm = hm2;
            long res = 0;
            for (long i : hm.keySet()) {
                res +=  hm.get(i);
            }
            System.out.println(j + 1 + ", " + res);
        }
        
        sc.close();
    }
}