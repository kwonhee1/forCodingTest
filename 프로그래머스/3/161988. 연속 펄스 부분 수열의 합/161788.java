package org.example;

import java.util.*;

public class Solution {
    public long solution(int[] sequence) {
        int[] sequence1 = new int[sequence.length];
        int[] sequence2 = new int[sequence.length];

        int flag = 1;
        for(int i = 0; i < sequence.length; i++, flag *= -1){
            sequence1[i] = sequence[i] * flag;
            sequence2[i] = sequence[i] * flag * -1;
        }

        long answer1 = persSum(sequence1);
        long answer2 = persSum(sequence2);

        return answer1 > answer2 ? answer1 : answer2;
    }
    // 1 -5 2 -7 100 -99 1000 -> 1 5 2 7 100 99 1000
    private long persSum(int[] sequence) {
        //Arrays.stream(sequence).forEach(e->System.out.print(e + ", "));
        //System.out.println();
        int start = 0;
        int end = 0;
        long sum = 0L;

        // find
        Seq seq = findRight(sequence, end);
        while(seq != null) {
            end = seq.index;
            sum += seq.sum;
            // System.out.println(String.format("(%d,%d) sum : %d", start, end, sum));
            seq = findRight(sequence, end+1);
        }

        seq = findLeft(sequence, start, end);
        while(seq != null) {
            start = seq.index;
            sum -= seq.sum;
            // System.out.println(String.format("(%d,%d) sum : %d", start, end, sum));
            seq = findLeft(sequence, start+1, end);
        }
        return sum;
    }
    private Seq findRight(int[] sequence, int startIndex) {
        long sum = 0L;
        for(; startIndex < sequence.length; startIndex++){
            sum += sequence[startIndex];
            if(sum > 0)
                return new Seq(sum, startIndex);
        }
        return null;
    }
    private Seq findLeft(int[] sequence, int startIndex, int endIndex){
        long sum = 0L;
        for(; startIndex < endIndex; startIndex++){
            sum += sequence[startIndex];
            if(sum < 0)
                return new Seq(sum, startIndex);
        }
        return null;
    }
}

class Seq{
    public long sum;
    public int index;
    public Seq(long s, int i) { sum = s; index = i;}
}
