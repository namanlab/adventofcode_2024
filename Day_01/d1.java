import java.util.*;

public class d1 {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String curString = sc.nextLine();
            String[] curStringArr = curString.split("   ");   
            list1.add(Integer.parseInt(curStringArr[0]));
            list2.add(Integer.parseInt(curStringArr[1]));
        }
        Collections.sort(list1);
        Collections.sort(list2);
        int res = 0;
        for (int i = 0; i < list1.size(); i++) {
            res += Math.abs(list1.get(i) - list2.get(i));
        }
        System.out.println(res);
        sc.close();
    }
}