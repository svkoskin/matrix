package matrix;

/**
 *
 * @author svkoskin
 */
public class Matrix {

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
                a = Tiedosto.parseMatrixFromFile(pathA);
                b = Tiedosto.parseTransposedMatrixFromFile(pathB);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


            try {

                alkuAika = System.currentTimeMillis();

                result = helper.multiply(a, b, threadCount);

                loppuAika = System.currentTimeMillis();

                // Mikäli käyttäjä on niin komentorivillä vaatinut, tulostetaan
                // tulosmatriisi.
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
