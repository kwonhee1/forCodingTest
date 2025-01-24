import java.util.*;

class Solution {
    public int[] solution(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int[] re = new int [s.length()];

        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);

            if(map.get(c) == null){
                map.put(c, i);
                re[i] = -1;
            }else{
                re[i] = i - map.get(c);
                map.put(c, i);
            }
        }
        return re;
    }
}