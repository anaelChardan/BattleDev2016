package Q3;

import Utils.Reader;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(Reader.getInstance().getFile("Q3/input1.txt"));

        String line;
        Scanner sc = new Scanner(System.in);

        final int nbCercles = sc.nextInt();     // 2 - 1500
        final List<Cercle> cercles = new ArrayList<>(nbCercles);

        while (sc.hasNext()) {
            cercles.add(new Cercle(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }


        for (Cercle c1 : cercles) {
            for (Cercle c2 : cercles) {
                if (c2.equals(c1)) {
                    continue;
                }

                double distance = Math.sqrt(Math.pow(c2.x - c1.x, 2) + Math.pow(c2.y - c1.y, 2));
                if (c2.r >= c1.r && distance <= (c2.r - c1.r)){
                    // inside
                }
                else if (c1.r >= c2.r && distance <= (c1.r - c2.r) ) {
                    // inside
                }
                else if (distance > (c1.r + c2.r)){
                    // not overlap
                }
                else {
                    System.out.println("KO");
                    return;
                }


                /*
                            if (distance <= c1.r + c2.r) {
                    if (c1.r >= c2.r) {
                        if (!((Math.abs(c2.x - c1.x) < c1.r) && (Math.abs(c2.y - c1.y) < c1.r))) {
                            System.out.println("KO");
                            return;
                        }
                    } else if (c1.r < c2.r) {
                        if (!((Math.abs(c1.x - c2.x) < c2.r) && (Math.abs(c1.y - c2.y) < c2.r))) {
                            System.out.println("KO");
                            return;
                        }
                    }
                }*/
            }
        }
        System.out.println("OK");


    }

    private static class Cercle {
        final int x;
        final int y;
        final int r;

        Cercle(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }
}
