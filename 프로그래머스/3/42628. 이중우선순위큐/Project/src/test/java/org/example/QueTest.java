package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class QueTest {

    private Que que;

    @BeforeEach
    public void setUp() {
        que = new Que();
    }

    @Test
    public void addTest() {
        que.add(4);
        que.add(5);
        int[] result = que.toIntegerArray();
        Assertions.assertArrayEquals(new int[]{4, 5}, result);

        que.add(1);
        result = que.toIntegerArray();
        Assertions.assertArrayEquals(new int[]{1, 4, 5}, result);

        que.add(9);
        que.add(-1);
        que.add(5);
        que.add(3);
        result = que.toIntegerArray();
        Assertions.assertArrayEquals(new int[]{-1, 1, 3, 4, 5, 5, 9}, result);
    }

    @Test
    public void moveTest() {
        Method move;
        Field value;
        try{
            move = Que.class.getDeclaredMethod("move", int.class);
            move.setAccessible(true);
            value = Que.class.getDeclaredField("value");
            value.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError();
        }

        que.add(4);
        que.add(5);
        que.add(3);

        int moveForwardResult;
        int moveBackwardResult;
        try {
            Que resultForwardQue = (Que) move.invoke(que, 3);
            moveForwardResult = (Integer) value.get(resultForwardQue);

            Que resultBackwardQue = (Que) move.invoke(resultForwardQue, -2);
            moveBackwardResult = (Integer) value.get(resultBackwardQue);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError();
        }

        Assertions.assertAll(
            ()->Assertions.assertEquals(moveForwardResult, 5),
            ()->Assertions.assertEquals(moveBackwardResult, 3)
        );
    }

}
