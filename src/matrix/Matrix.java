package matrix;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author svkoskin
 */
public class Matrix {

    public static double[][] parseMatrixFromFile(String path) throws Exception {

        double[][] matrix;


        Scanner lukija = new Scanner(new File(path));

        int koko = lukija.nextInt();

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
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        boolean verboseOutput;
        String pathA;
        String pathB;
        int threadCount;
        double[][] a = null;
        double[][] b = null;
        double[][] result;
        long alkuAika;
        long loppuAika;
        MatrixMultiplicationHelper helper = new MatrixMultiplicationHelper();

        if (args.length >= 3) {
            if (args[1].equals("-v")) {
                verboseOutput = true;
                threadCount = Integer.parseInt(args[2]);
                pathA = args[3];
                pathB = args[4];
            } else {
                verboseOutput = false;
                threadCount = Integer.parseInt(args[1]);
                pathA = args[2];
                pathB = args[3];
            }

            try {
                a = parseMatrixFromFile(pathA);
                b = parseMatrixFromFile(pathB);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


            try {

                alkuAika = System.currentTimeMillis();

                result = helper.multiply(a, b, threadCount);

                loppuAika = System.currentTimeMillis();

                if (verboseOutput) {
                    for (double[] col : result) {
                        for (double d : col) {
                            System.out.print(d + " ");
                        }
                        System.out.print("\n");
                    }
                }

                System.out.println("Aikaa kului " + (loppuAika - alkuAika));

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("Komentoriviparametreina on annettava vähintään polut kerrottaviin matriiseihin.");
        }
    }
}
