package Q3;
import Utils.Reader;

import java.io.IOException;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(Reader.getInstance().getFile("input4.txt"));

        Scanner sc = new Scanner(System.in);

        int nbCercles = sc.nextInt();

        List<Integer> rayon = new ArrayList<>();
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();

        for (int i = 0; i < nbCercles; i++) {
            x.add(sc.nextInt());
            y.add(sc.nextInt());
            rayon.add(sc.nextInt());
        }

        boolean res = true;

        for (int i = 0; i < nbCercles; i++) {
            int crayon = rayon.get(i);
            int cx = x.get(i);
            int cy = y.get(i);

            for (int j = i+1; j < nbCercles; j++) {
                int ccrayon = rayon.get(j);
                int ccx = x.get(j);
                int ccy = y.get(j);

                res = res && touch(cx, cy, crayon, ccx, ccy, ccrayon);
            }
        }

        System.out.println(res ? "OK" : "KO");

        sc.close();
    }

    private static boolean touch(int x1, int y1, int r1, int x2, int y2, int r2) {
        double d = Math.sqrt(Math.pow((x2 - x1), 2) +  Math.pow((y2 - y1), 2));
        boolean b = Math.max(r1, r2) > (d + Math.min(r1, r2));
        return b || d >= (r1 + r2);
    }
}