package Q2;

import Utils.Reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(Reader.getInstance().getFile("input1.txt"));

        //Scanner sc = new Scanner(System.in);

        final int length = 7;
        final String dot = ".";
        final String star = "*";

        for (int i = 1; i <= length; i+= 2) {
            int diff = (length - i) / 2;
            String line = "";
            for (int x = 1; x <= diff; x++) {
                line += dot;
            }
            for (int x = 1; x <= i; x++) {
                line += star;
            }
            for (int x = 1; x <= diff; x++) {
                line += dot;
            }
            System.out.println(line);
        }
        for (int i = length - 2; i >= 1; i-= 2) {
            int diff = (length - i) / 2;
            String line = "";
            for (int x = 1; x <= diff; x++) {
                line += dot;
            }
            for (int x = 1; x <= i; x++) {
                line += star;
            }
            for (int x = 1; x <= diff; x++) {
                line += dot;
            }
            System.out.println(line);
        }
    }
}
