import java.sql.Array;
import java.util.*;


public class d9p2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double res = 0;
        String s = sc.nextLine();
        String[] arr = s.split("");
        int[] numArr = new int[arr.length];
        int[] orig_arr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            numArr[i] = Integer.parseInt(arr[i]);
            orig_arr[i] = Integer.parseInt(arr[i]);
        }
        int numFiles = arr.length / 2;
        int curId = numFiles;
        int en = arr.length - 1;
        int st = 1;
        int curSum = numArr[0];
        int[] ids = new int[arr.length];    
        for (int i = 0; i < arr.length; i = i + 2) {
            ids[i] = i/2;
        }
        int[] finSum = new int[arr.length];
        finSum[0] = numArr[0];
        for (int i = 1; i < arr.length; i++) {
            finSum[i] = finSum[i - 1] + numArr[i];
        }
        System.out.println(Arrays.toString(finSum));
        int[] finArr = new int[finSum[arr.length - 1]];
        Arrays.fill(finArr, 0);
        int counter = 0;
        for (int i = 0; i < arr.length; i = i + 1) {
            for (int j = 0; j < numArr[i]; j++) {
                finArr[counter] = ids[i];
                counter++;
            }
        }
        System.out.println(Arrays.toString(finArr));


        while (en >= st) {
            int flag = -1;
            for (int i = st; i < en; i = i + 2) {
                if (numArr[i] >= numArr[en]) {
                    flag = i;
                    break;
                }
            }
            if (flag != -1) {
                for (int i = 0; i < numArr[en]; i++) {
                    finArr[finSum[flag - 1] + orig_arr[flag] - numArr[flag] + i] = ids[en];
                    finArr[finSum[en - 1] + i]  = 0;
                }
                numArr[flag] = numArr[flag] - numArr[en];
                en = en - 2;
            } else {
                en = en - 2;
            }
        }
        System.out.println(Arrays.toString(finArr));
        for (int i = 0; i < finArr.length; i++) {
            res += finArr[i]*i;
        }
        System.out.println(res);
        sc.close();
    }
}