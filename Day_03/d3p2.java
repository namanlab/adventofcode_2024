import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.*;

public class d3p2 {
    public static void main(String[] args) {
        String filePath = "input.txt"; 
        String text = "";

        try {
            text = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return;
        }
        String mulRegex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        String controlRegex = "(do\\(\\)|don't\\(\\))";

        Pattern mulPattern = Pattern.compile(mulRegex);
        Pattern controlPattern = Pattern.compile(controlRegex);

        Matcher mulMatcher = mulPattern.matcher(text);
        Matcher controlMatcher = controlPattern.matcher(text);

        // Initial state: mul is enabled
        boolean isMulEnabled = true;
        long total = 0;

        // Traverse the text
        int currentIndex = 0;

        while (true) {
            // Find the next control or mul instruction
            int nextMulIndex = mulMatcher.find(currentIndex) ? mulMatcher.start() : Integer.MAX_VALUE;
            int nextControlIndex = controlMatcher.find(currentIndex) ? controlMatcher.start() : Integer.MAX_VALUE;
            System.out.println("Next Mul Index: " + nextMulIndex + ", Next Control Index: " + nextControlIndex);
            if (nextMulIndex == Integer.MAX_VALUE && nextControlIndex == Integer.MAX_VALUE) {
                // No more instructions
                break;
            }
            if (nextControlIndex < nextMulIndex) {
                String control = controlMatcher.group();
                isMulEnabled = control.equals("do()");
                currentIndex = controlMatcher.end();
            } else {
                if (isMulEnabled) {
                    int x = Integer.parseInt(mulMatcher.group(1));
                    int y = Integer.parseInt(mulMatcher.group(2));
                    total += x * y;
                }
                currentIndex = mulMatcher.end();
            }
        }
        System.out.println("Total Sum: " + total);
    }
}