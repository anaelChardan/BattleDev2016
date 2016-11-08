package Q2;

import Utils.Reader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        System.setIn(Reader.getInstance().getFile("input3.txt"));


        Scanner scan = new Scanner(System.in);


//        int size = scan.nextInt();
        int size = 7;
        int nbEtoiles = -1;
        scan.close();
        String res = "";
        for (int i = 1; i <  size / 2 + -1; i += 2) {
            int nbPoints = (size - i) / 2;
            nbEtoiles = i + 2;

            for (int j = 1; j <= nbPoints; j++) {
                res += ".";
            }
            for (int j = 1; j <= nbEtoiles; j++) {
                res += "*";
            }
            for (int j = 1; j <= nbPoints; j++) {
                res += ".";
            }
            System.out.println(res);
            res = "";
        }
        for (int i = 0; i < size; i++) {
            res += "*";
        }
        System.out.println(res);
        res = "";
        nbEtoiles = size - 2;
        for (int i = size / 2; i >= 1; i -= 2) {
            int nbPoints = (size - i) / 2;
            nbEtoiles = i - 2;

            for (int j = 0; j <= nbPoints; j++) {
                res += ".";
            }
            for (int j = 0; j <= nbEtoiles; j++) {
                res += "*";
            }
            for (int j = 0; j <= nbPoints; j++) {
                res += ".";
            }
            System.out.println(res);
        }

    }
}
