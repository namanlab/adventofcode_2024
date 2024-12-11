import java.util.*;

public class d2p2 {

    public static boolean checkArr(int ivar, String[] curStringArr){
            int curNum = Integer.parseInt(curStringArr[0]);
            boolean temp2 = true;  
            boolean desc = false;
            for (int i = 1; i < curStringArr.length; i++){
                if (i == ivar){continue;}   
                int tempNum = Integer.parseInt(curStringArr[i]);
                if ((i == 1) || (i == 2 && ivar == 1)){
                    if (curNum > tempNum){desc = true;}
                } else {
                    if (desc){
                        if (curNum < tempNum){temp2 = false; break;}
                    } else {
                        if (curNum > tempNum){temp2 = false; break;}
                    }
                }
                if (Math.abs(curNum - tempNum) < 1 || Math.abs(curNum - tempNum) > 3){
                    temp2 = false; break;
                }
                curNum = tempNum;
            }
            return temp2;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long res = 0;
        while (sc.hasNext()){
            String curString = sc.nextLine();
            String[] curStringArr = curString.split(" "); 


            // ignore 1st one
            int curNum = Integer.parseInt(curStringArr[1]);
            boolean temp2 = true;  
            boolean desc = false;
            for (int i = 2; i < curStringArr.length; i++){
                int tempNum = Integer.parseInt(curStringArr[i]);
                if (i == 2){
                    if (curNum > tempNum){desc = true;}
                } else {
                    if (desc){
                        if (curNum < tempNum){temp2 = false; break;}
                    } else {
                        if (curNum > tempNum){temp2 = false; break;}
                    }
                }
                if (Math.abs(curNum - tempNum) < 1 || Math.abs(curNum - tempNum) > 3){
                    temp2 = false; break;}
                curNum = tempNum;
            }
            boolean temp = temp2;
            System.out.println(curStringArr[0]);
            System.out.println(temp);
            for (int i = 1; i < curStringArr.length + 1; i++){
                System.out.println(checkArr(i, curStringArr));
                temp = temp || checkArr(i, curStringArr);
            }
            res += temp ? 1 : 0;
            
        }
        System.out.println(res);
        sc.close();
    }
}