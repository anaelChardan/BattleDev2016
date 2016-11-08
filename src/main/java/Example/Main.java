package Example;

import Utils.Reader;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(Reader.getInstance().getFile("input1.txt"));

        String line;
        Scanner sc = new Scanner(System.in);

        final int nbGants = sc.nextInt();
        final Map<String, Integer> couleurs = new HashMap<>(nbGants);
        while (sc.hasNextLine()) {
            final String couleur = sc.nextLine();

            if (couleurs.containsKey(couleur)) {
                couleurs.put(couleur, couleurs.remove(couleur) + 1);
            } else {
                couleurs.put(couleur, 1);
            }
        }

        int nbPaires = 0;
        for (Integer nb : couleurs.values()) {
            nbPaires += Math.floorDiv(nb, 2);
        }
        System.out.println(nbPaires);
    }
}
