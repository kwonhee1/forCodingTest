// 논리 오류 발생 알고리즘 부터 다시 짤것!
import java.util.*;
class Solution {
    public String solution(String s, String skip, int index) {
        char[] re = new char[s.length()];
        ArrayList<Integer> arpabet = new ArrayList<Integer>();
        for(int i = 0; i<s.length(); i++){
            arpabet.add(s.charAt(i) - 'a');
        }
        
        ArrayList<Integer> skips = new ArrayList<Integer>();
        for(int i = 0; i < skip.length(); i++){
            skips.add(skip.charAt(i) - 'a');
        }
        skips.add(30);
        
        //sort
        Collections.sort(skips);
        System.out.println(skips);
        
        for(int i = 0; i <arpabet.size(); i++){
            int start = arpabet.get(i);
            int end = (arpabet.get(i) + index) % 26;
            
            if(start < end){
                end += getIndex(end, skips) - getIndex(start, skips);
            }else{
                end += getIndex(end, skips) + getIndex(start, skips);
            }
            
            re[i] = (char)((end % 26) + 'a');
        }
        
        return new String(re);
    }
    
    private int getIndex(int target, ArrayList<Integer> skips){
        int index = 0;
        for(int i = 0; i<skips.size(); i++){
            if(skips.get(i) <= target)
                index++;
            else
                break;
        }
        System.out.println(target + " : "+ index);
        return index;
    }
}