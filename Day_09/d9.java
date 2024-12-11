import java.util.*;


public class d9 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double res = 0;
        String s = sc.nextLine();
        String[] arr = s.split("");
        int[] numArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            numArr[i] = Integer.parseInt(arr[i]);
        }
        double numFiles = Math.floor(arr.length / 2);
        double curId = numFiles;
        double curStId = 1;
        int en = arr.length - 1;
        int st = 1;
        double curSum = numArr[0];
        while (en >= st) {
            System.out.println("st: " + st + " en: " + en + " curSum: " + curSum + " curId: " + curId + " res: " + res);
            if (numArr[st] == numArr[en]) {
                for (int i = 0; i < numArr[en]; i++) {
                    res += curSum*curId;
                    curSum++;
                }
                numArr[st] = 0;  numArr[en] = 0;
                // st updates
                for (int i = 0; i < numArr[st + 1]; i++) {
                    res += curSum*curStId;
                    curSum++;
                }
                curStId++;
                st = st + 2;
                // en updates
                curId = curId - 1;
                en = en - 2;
            } else if (numArr[st] > numArr[en]) {
                for (int i = 0; i < numArr[en]; i++) {
                    res += curSum*curId;
                    curSum++;
                }
                numArr[st] = numArr[st] - numArr[en]; numArr[en] = 0;
                // en updates
                curId = curId - 1;
                en = en - 2;
            } else {
                for (int i = 0; i < numArr[st]; i++) {
                    res += curSum*curId;
                    curSum++;
                }
                numArr[en] = numArr[en] - numArr[st]; numArr[st] = 0;
                // st updates
                for (int i = 0; i < numArr[st + 1]; i++) {
                    res += curSum*curStId;
                    curSum++;
                }
                curStId++;
                st = st + 2;
            }
        }
        System.out.println(res);
        sc.close();
    }
}