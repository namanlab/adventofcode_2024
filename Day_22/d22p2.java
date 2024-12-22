import java.util.*;

public class d22p2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long maxBananas = 0;
        List<int[]> secretNumbers = new ArrayList<>();

        // Read inputs and generate secret number sequences
        while (sc.hasNextLine()) {
            long initialNumber = Long.parseLong(sc.nextLine());
            secretNumbers.add(generatePrices(initialNumber));
        }

        List<Map<String, Integer>> frequencyMaps = new ArrayList<>();
        for (int[] prices : secretNumbers) {
            Map<String, Integer> freqMap = new HashMap<>();
            for (int i = 4; i < 2001; i++) {
                String key = (prices[i - 4] - prices[i - 3]) + "," +
                             (prices[i - 3] - prices[i - 2]) + "," +
                             (prices[i - 2] - prices[i - 1]) + "," +
                             (prices[i - 1] - prices[i]);
                freqMap.putIfAbsent(key, prices[i]);
            }
            frequencyMaps.add(freqMap);
        }
        for (int i = -9; i <= 9; i++) {
            for (int j = -9; j <= 9; j++) {
                for (int k = -9; k <= 9; k++) {
                    for (int l = -9; l <= 9; l++) {
                        String sequence = i + "," + j + "," + k + "," + l;
                        long currentBananas = 0;
                        for (Map<String, Integer> freqMap : frequencyMaps) {
                            if (freqMap.containsKey(sequence)) {
                                currentBananas += freqMap.get(sequence);
                            }
                        }
                        maxBananas = Math.max(maxBananas, currentBananas);
                    }
                }
            }
        }

        System.out.println(maxBananas);
        sc.close();
    }

    public static int[] generatePrices(long num) {
        int[] prices = new int[2001];
        prices[0] = (int) (num % 10);
        for (int i = 1; i <= 2000; i++) {
            num = evolveSecretNumber(num);
            prices[i] = (int) (num % 10);
        }
        return prices;
    }

    public static long evolveSecretNumber(long num) {
        num = (num * 64) ^ num;
        num %= 16777216;
        num = (num / 32) ^ num;
        num %= 16777216;
        num = (num * 2048) ^ num;
        num %= 16777216;
        return num;
    }
}