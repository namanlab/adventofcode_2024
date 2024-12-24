import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class d24 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> wireValues = new HashMap<>();
        Map<String, String[]> gateInstructions = new HashMap<>();

        // Read the initial wire values
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line == "")
                break;
            String[] parts = line.split(": ");
            wireValues.put(parts[0], Integer.parseInt(parts[1]));
        }

        // Read the gate instructions
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" -> ");
            String outputWire = parts[1];
            String[] instruction = parts[0].split(" ");
            gateInstructions.put(outputWire, instruction);
        }

        scanner.close();

        // Simulate gates
        boolean progress = true;
        while (progress) {
            progress = false;
            for (Map.Entry<String, String[]> entry : gateInstructions.entrySet()) {
                String outputWire = entry.getKey();
                if (wireValues.containsKey(outputWire))
                    continue;

                String[] instruction = entry.getValue();
                Integer input1 = wireValues.get(instruction[0]);
                Integer input2 = wireValues.get(instruction[2]);

                String operation = instruction[1];
                if (input1 != null && input2 != null) {
                    int result = switch (operation) {
                        case "AND" -> input1 & input2;
                        case "OR" -> input1 | input2;
                        case "XOR" -> input1 ^ input2;
                        default -> throw new IllegalArgumentException("Unknown operation: " + operation);
                    };
                    wireValues.put(outputWire, result);
                    progress = true;
                }
            }
        }

        // Calculate the decimal output
        StringBuilder binaryResult = new StringBuilder();
        for (int i = 0;; i++) {
            String wireName = "z" + String.format("%02d", i);
            if (!wireValues.containsKey(wireName))
                break;
            binaryResult.insert(0, wireValues.get(wireName));
        }
        System.out.println(binaryResult);
        long decimalResult = Long.parseLong(binaryResult.toString(), 2);
        System.out.println(decimalResult);
    }
}