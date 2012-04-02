package matrix;

import java.util.Scanner;

/**
 * Matriisien lukemisen ja kirjoittamisen toteuttava luokka
 *
 * TODO: matriisin kirjoittaminen?
 *
 * @author juhanile
 */
public class Tiedosto {

    /**
     * [ a b c ]    [ a b c ]
     * [ d e f ] -> [ d e f ]
     * [ g h i ]    [ g h i ]
     */
    public static double[][] parseMatrixAFromFile(String path) throws Exception {

        double[][] matrix;
        int koko;

        Scanner lukija = new Scanner(new java.io.File(path));

        koko = lukija.nextInt();
        matrix = new double[koko][koko];

        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                String seuraava = lukija.next();
                matrix[i][j] = Double.valueOf(seuraava);
            }
        }
        return matrix;
    }

    /**
     * [ a b c ]    [ a d g ]
     * [ d e f ] -> [ b e h ]
     * [ g h i ]    [ c f i ]
     */
    public static double[][] parseMatrixBFromFile(String path) throws Exception {

        double[][] matrix;
        int koko;

        Scanner lukija = new Scanner(new java.io.File(path));

        koko = lukija.nextInt();
        matrix = new double[koko][koko];

        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                String seuraava = lukija.next();
                matrix[j][i] = Double.valueOf(seuraava);
            }
        }
        return matrix;
    }
}