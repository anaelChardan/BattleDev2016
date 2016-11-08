package Example;

import Utils.Reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//EXEMPLE DU MOT LE PLUS LONG DANS
public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(Reader.getInstance().getFile("input3.txt"));

        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<Integer>();
        int N = scan.nextInt();
        while (scan.hasNext())
        {
            list.add(scan.next().length());
        }
        scan.close();
        System.out.println(Collections.max(list));
    }
}
