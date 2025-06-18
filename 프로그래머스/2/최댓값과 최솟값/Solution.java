class Solution {    
    public String solution(String s) {
        String[] strs = s.split(" ");
        int max = Integer.valueOf(strs[0]); 
        int min = max;
        for(int i = 1; i < strs.length; i++){
            int v =Integer.valueOf(strs[i]);
            if(max < v)
                max = v;
            if(min > v)
                min = v;
        }
        return String.format("%d %d", min, max);
    }

}