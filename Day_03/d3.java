import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;

public class d3 {
    public static void main(String[] args) {
        String filePath = "input.txt"; // Path to your input file
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return;
        }

        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        // List to store the products
        // ArrayList<String> results = new ArrayList<>();
        long res = 0;
        while (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int product = x * y;
            res += product;
            //results.add("mul(" + x + ", " + y + ") = " + product);
        }
        System.out.println(res);
        //results.forEach(System.out::println);
    }
}