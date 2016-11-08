package Q1;

import Utils.Reader;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(Reader.getInstance().getFile("Q1_1.txt"));
        char ret = (char)System.in.read();

        // returns the first character
        System.out.println(ret);
    }
}
