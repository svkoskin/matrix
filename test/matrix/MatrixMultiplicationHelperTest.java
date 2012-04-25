/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author svkoskin
 */
public class MatrixMultiplicationHelperTest {

    private float[][] x;
    private float[][] y;
    
    private MatrixMultiplicationHelper helper;

    public MatrixMultiplicationHelperTest() {
    }

    @Before
    public void setUp() {
        this.helper = new MatrixMultiplicationHelper();
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void testTranspose() {
        
        System.out.println("transpose");
        
        double[][] matrix = {{1, 2, 3},
                             {4, 5, 6},
                             {7, 8, 9}};
        
        double[][] expResult = {{1, 4, 7},
                               {2, 5, 8},
                               {3, 6, 9}};        
    
        assertArrayEquals(expResult, helper.transpose(matrix));
        
    }
    

    /**
     * Test of multiply method, of class MatrixMultiplicationHelper.
     */
    @Test
    public void testMultiply() throws Exception {
        
        System.out.println("multiply");
        
        double[][] a = {{1, 2, 3},
                       {4, 5, 6},
                       {7, 8, 9}};

        double[][] b = {{0, 3, 9},
                       {2, 4, 6},
                       {5, 0, 5}};
        
        double[][] bTransposed = helper.transpose(b);
        
        double[][] expResult = {{19, 11, 36},
                               {40, 32, 96},
                               {61, 53, 156}};        
        
        double[][] result = helper.multiply(a, bTransposed, 4);
        assertArrayEquals(expResult, result);
    }
}
