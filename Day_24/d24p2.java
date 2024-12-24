import java.util.*;

public class d24p2 {

    private static Set<String> swappedWires = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read initial wire values
        Map<String, Integer> wireValues = new HashMap<>();
        Map<String, String[]> gateInstructions = new HashMap<>();

        // Read the initial wire values
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) break;
            String[] parts = line.split(": ");
            wireValues.put(parts[0], Integer.parseInt(parts[1]));
        }

        // Read the gate instructions
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split(" -> ");
            String outputWire = parts[1];
            String[] instruction = parts[0].split(" ");
            gateInstructions.put(outputWire, instruction);
        }
        scanner.close();

        // Simulate system to find correct behavior
        findSwappedWires(wireValues, gateInstructions, swappedWires);

        // Output result
        List<String> sortedWires = new ArrayList<>(swappedWires);
        Collections.sort(sortedWires);
        System.out.println("Swapped wires: " + String.join(",", sortedWires));
    }

    private static void populateWireValues(Map<String, Integer> wireValues, long value, String prefix) {
        String binary = Long.toBinaryString(value);
        while (binary.length() < 45) { // HARD CODED 5
            binary = "0" + binary; // Ensure 8-bit representation
        }
        System.out.println("Binary (" + prefix + "): " + binary);
        for (int i = 0; i < binary.length(); i++) {
            wireValues.put(prefix + String.format("%02d", i), Integer.parseInt(String.valueOf(binary.charAt(i))));
        }
    }

    private static String extractBinaryString(Map<String, Integer> wireValues, String prefix) {
        StringBuilder binary = new StringBuilder();
        for (int i = 0;; i++) {
            String wire = prefix + String.format("%02d", i);
            if (!wireValues.containsKey(wire)) break;
            binary.append(wireValues.get(wire));
        }
        return binary.toString();
    }

    private static boolean findSwappedWires(Map<String, Integer> wireValues,
                                            Map<String, String[]> gateInstructions,
                                            Set<String> swappedWires) {
        System.out.println("Swapped wires: " + swappedWires);
        if (swappedWires.size() == 8) { // Stop after 4 pairs // HARD CODED 4
            // Call simulateSystem 3 times and check if true always
            for (int i = 0; i < 3; i++) {
                if (!simulateSystem(wireValues, gateInstructions)) {
                    return false;
                }
            }
            return simulateSystem(wireValues, gateInstructions);
        }

        for (String wire1 : gateInstructions.keySet()) {
            for (String wire2 : gateInstructions.keySet()) {
                if (wire1.equals(wire2) || swappedWires.contains(wire1) || swappedWires.contains(wire2)) {
                    continue;
                }
                // Swap outputs
                swapGateOutputs(gateInstructions, wire1, wire2);

                // Add swapped wires to the set
                swappedWires.add(wire1);
                swappedWires.add(wire2);

                // Simulate and backtrack
                if (findSwappedWires(wireValues, gateInstructions, swappedWires)) {
                    System.out.println("Swapped wires: " + wire1 + " and " + wire2);
                    return true;
                }

                // Undo swap
                swapGateOutputs(gateInstructions, wire1, wire2);
                swappedWires.remove(wire1);
                swappedWires.remove(wire2);
            }
        }
        return false;
    }

    private static void swapGateOutputs(Map<String, String[]> gateInstructions, String wire1, String wire2) {
        String[] temp = gateInstructions.get(wire1);
        gateInstructions.put(wire1, gateInstructions.get(wire2).clone());
        gateInstructions.put(wire2, temp.clone());
    }

    private static boolean simulateSystem(Map<String, Integer> wireValues, Map<String, String[]> gateInstructions) {
        // Generate random values for x and y
        Random random = new Random();
        long xValue = (long) random.nextDouble(Math.pow(2, 44)); // HARD CODED 32 // 6-bit random number for x
        long yValue =  (long) random.nextDouble(Math.pow(2, 44)); // HARD CODED 32 // 6-bit random number for y

        // Populate wire values for x and y
        populateWireValues(wireValues, xValue, "x");
        populateWireValues(wireValues, yValue, "y");

        Map<String, Integer> simulatedValues = new HashMap<>(wireValues);
        boolean progress = true;

        while (progress) {
            progress = false;

            for (Map.Entry<String, String[]> entry : gateInstructions.entrySet()) {
                String outputWire = entry.getKey();
                if (simulatedValues.containsKey(outputWire)) continue;

                String[] instruction = entry.getValue();
                Integer input1 = simulatedValues.get(instruction[0]);
                Integer input2 = simulatedValues.get(instruction[2]);

                if (input1 != null && input2 != null) {
                    int result = switch (instruction[1]) {
                        case "AND" -> input1 & input2;
                        case "OR" -> input1 | input2;
                        case "XOR" -> input1 ^ input2;
                        default -> throw new IllegalArgumentException("Unknown operation: " + instruction[1]);
                    };
                    simulatedValues.put(outputWire, result);
                    progress = true;
                }
            }
        }

        // Check if the output matches the sum of x and y
        return checkAdditionCorrectness(simulatedValues);
    }

    private static boolean checkAdditionCorrectness(Map<String, Integer> wireValues) {
        // Extract binary numbers from x and y wires
        String xBinary = extractBinaryString(wireValues, "x");
        String yBinary = extractBinaryString(wireValues, "y");
        String zBinary = extractBinaryString(wireValues, "z");

        String res = addBinaryStrings(xBinary, yBinary);
        // System.out.println(wireValues);
        System.out.println("x: " + xBinary);
        System.out.println("y: " + yBinary);
        System.out.println("R: " + res);
        System.out.println("z: " + zBinary);


        // Check if x + y equals z (compare strings directly)
        return (res.equals(zBinary));
    }

    private static String addBinaryStrings(String a, String b) {
        int carry = 0, i = a.length() - 1, j = b.length() - 1;
        StringBuilder result = new StringBuilder();

        while (i >= 0 || j >= 0 || carry > 0) {
            int bitA = i >= 0 ? a.charAt(i--) - '0' : 0;
            int bitB = j >= 0 ? b.charAt(j--) - '0' : 0;
            int sum = bitA + bitB + carry;
            result.append(sum % 2);
            carry = sum / 2;
        }

        String res = result.reverse().toString();
        if (res.length() < 46) { // HARD CODED 5
            res = "0".repeat(46 - res.length()) + res;
        }

        return res;
    }
}