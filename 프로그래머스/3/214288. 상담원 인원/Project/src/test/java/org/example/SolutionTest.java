package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest{
    @Test
    public void solutionTest(){
        Solution s = new Solution();
        int answer = s.solution(3, 5, new int [][] {{10, 60, 1}, {15, 100, 3}, {20, 30, 1}, {30, 50, 3}, {50, 40, 1}, {60, 30, 2}, {65, 30, 1}, {70, 100, 2}});
        Assertions.assertEquals(25, answer);
    }

    @Test
    public void solutionTest2(){
        Solution s = new Solution();
        int answer = s.solution(2, 3, new int[][] {{5, 55, 2}, {10, 90, 2}, {20, 40, 2}, {50, 45, 2}, {100, 50, 2}});
        Assertions.assertEquals(90, answer);
    }

    @Test
    public void solutionTest3(){
        Solution s = new Solution();
        int answer = s.solution(2, 3, new int[][] {{5, 55, 2}, {10, 90, 2}, {20, 40, 2}, {50, 45, 2}, {100, 50, 2}});
        Assertions.assertEquals(90, answer);
    }
}
