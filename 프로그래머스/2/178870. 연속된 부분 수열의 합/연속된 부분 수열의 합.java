import java.util.*;

class Solution {
    private int start = 0;
    private int end = 0;
    private int sum;
    public int[] solution(int[] se, int k) {
        sum = se[start];
        ArrayList<Stack> results = new ArrayList<>();
        
        int[] sequence = new int[se.length+1];
        for(int i = 0; i < se.length; i++){
            sequence[i] = se[i];
        }
        sequence[se.length] = 0;
        
        while(end < se.length) { 
            //System.out.println(String.format("[%d, %d]=%d", start, end, sum));
            if(sum == k){
                results.add(new Stack(start, end));
                //System.out.println(String.format("add [%d,%d]", start, end));
                sum += sequence[++end];
            }
            else if(sum <= k)
                sum += sequence[++end];
            else if(sum > k){
                sum -= sequence[start++];
            }
        }
    
        
        // for(int i = 0; i < results.size(); i++){
        //     System.out.print(String.format("[%d, %d]", results.get(i).start, results.get(i).end));
        // }
        
        // find most short
        int mostShort = 0;
        for(int i = 1; i < results.size(); i++){
            //System.out.print(results.get(mostShort).toString() +"<>" + results.get(i).toString());
            if(results.get(mostShort).getLength() > results.get(i).getLength())
                mostShort = i;
        }
        
        return new int[]{results.get(mostShort).start,results.get(mostShort).end};
    }
    
    private class Stack{
        int start; int end;
        public Stack(int s, int e) {
            start = s; end = e;
        }
        public int getLength(){
            return end - start;
        }
        
        public String toString(){
            return String.format("[%d, %d] = %d", start, end, end - start);
        }
    }
}