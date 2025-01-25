import java.util.*;
class Solution {
    private String s;
    public int solution(String s) {
        this.s = s;
        
        int count = 0;
        int answer = 0;
        do{
            count = once(count);
            answer++;
        }while(count < s.length());
        
        return answer;
    }
    
    public int once(int start){
        char stand = s.charAt(start);
        
        int count = 1;
        for(start++; start < s.length() && count != 0; start++){
            count += s.charAt(start) == stand ? 1 : -1;
        }
        
        return start;
    }
}