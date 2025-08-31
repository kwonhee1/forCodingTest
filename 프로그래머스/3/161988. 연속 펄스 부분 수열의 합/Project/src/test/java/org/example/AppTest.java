package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void mainTest() {
         Solution solution = new Solution();

         long answer1 = solution.solution(new int[] {2, 3, -6, 1, 3, -1, 2, 4});
         long answer2 = solution.solution(new int[] {1, 5, 2, 7, 100, 99, 1000});

         Assertions.assertAll(
             ()->Assertions.assertEquals(10, answer1),
             ()->Assertions.assertEquals(1001, answer2)
         );
    }

}
