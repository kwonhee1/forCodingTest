package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    void main() {
        Solution s = new Solution();
        int[] answer = s.solution(new String[] {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"});
        Assertions.assertArrayEquals(answer, new int[] {0,0});
    }

}
