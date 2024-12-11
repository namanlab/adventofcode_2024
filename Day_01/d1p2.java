import java.util.*;

public class d1p2 {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String curString = sc.nextLine();
            String[] curStringArr = curString.split("   ");   
            map1.put(Integer.parseInt(curStringArr[0]), map1.getOrDefault(Integer.parseInt(curStringArr[0]), 0) + 1);
            map2.put(Integer.parseInt(curStringArr[1]), map2.getOrDefault(Integer.parseInt(curStringArr[1]), 0) + 1);
        }
        long res = 0;
        for (int i: map1.keySet()) {
            res += i*map1.get(i) * map2.getOrDefault(i, 0);
        }
        System.out.println(res);
        sc.close();
    }
}