import java.util.*;

public class d2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long res = 0;
        while (sc.hasNext()){
            String curString = sc.nextLine();
            String[] curStringArr = curString.split(" "); 
            boolean temp = true;  
            boolean desc = false;
            int curNum = Integer.parseInt(curStringArr[0]);
            for (int i = 1; i < curStringArr.length; i++){
                int tempNum = Integer.parseInt(curStringArr[i]);
                if (i == 1){
                    if (curNum > tempNum){
                        desc = true;
                    }
                } else {
                    if (desc){
                        if (curNum < tempNum){
                            temp = false;
                            break;
                        }
                    } else {
                        if (curNum > tempNum){
                            temp = false;
                            break;
                        }
                    }
                }
                if (Math.abs(curNum - tempNum) < 1 || Math.abs(curNum - tempNum) > 3){
                    temp = false;
                    break;
                }
                curNum = tempNum;
            }
            res += temp ? 1 : 0;
            
        }
        System.out.println(res);
        sc.close();
    }
}