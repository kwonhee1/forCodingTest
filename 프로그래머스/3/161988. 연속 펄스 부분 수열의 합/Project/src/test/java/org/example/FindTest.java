package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class FindTest {

    private Solution solution;

    @BeforeEach
    public void setUp() {
        solution = new Solution();
    }

    @Test
    public void findRightTest() {
        Method findRight;
        int[] sequence1 = {-5,-3,5,4,2};
        int[] sequence2 = {2};
        try {
            findRight = Solution.class.getDeclaredMethod("findRight", int[].class, int.class);
            findRight.setAccessible(true);
        } catch (Exception e) { e.printStackTrace(); throw new AssertionError(); }

        Seq seq1;
        Seq seq2;

        try {
            seq1 = (Seq) findRight.invoke(solution, sequence1, 0);
            seq2 = (Seq) findRight.invoke(solution, sequence2, 0);
        } catch (Exception e)  { e.printStackTrace(); throw new AssertionError(); }

        Assertions.assertAll(
                ()->Assertions.assertEquals(1, seq1.sum),
                ()->Assertions.assertEquals(3, seq1.index),

                ()->Assertions.assertEquals(2, seq2.sum),
                ()->Assertions.assertEquals(0, seq2.index)
        );
    }

    @Test
    public void findLeftTest() {
        Method findLeft;
        int[] sequence1 = {-5};
        int[] sequence2 = {5, -4, -2, 2};

        try{
            findLeft = Solution.class.getDeclaredMethod("findLeft", int[].class, int.class, int.class);
            findLeft.setAccessible(true);
        }catch (Exception e) {e.printStackTrace(); throw new AssertionError(); }

        Seq seq1;
        Seq seq2;

        try {
            seq1 = (Seq) findLeft.invoke(solution, sequence1, 0, sequence1.length);
            seq2 = (Seq) findLeft.invoke(solution, sequence2, 0, sequence2.length);
        } catch (Exception e)  { e.printStackTrace(); throw new AssertionError(); }

        Assertions.assertAll(
                ()->Assertions.assertEquals(-5, seq1.sum),
                ()->Assertions.assertEquals(0, seq1.index),

                ()->Assertions.assertEquals(-1, seq2.sum),
                ()->Assertions.assertEquals(2, seq2.index)
        );
    }
}
