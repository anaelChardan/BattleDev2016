package Q1;

import Utils.Reader;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(Reader.getInstance().getFile("input3.txt"));


        Scanner scan = new Scanner(System.in);

        Map<String, Integer> gants = new HashMap<>();

        scan.nextInt();

        while (scan.hasNext())
        {
            String gant = scan.next();

            gants.put(gant, 1 + gants.getOrDefault(gant, 0));
        }

        int sum = gants.entrySet()
                .stream()
                .mapToInt(e -> e.getValue() / 2)
                .sum();

        scan.close();
        System.out.println(sum);

    }
}
