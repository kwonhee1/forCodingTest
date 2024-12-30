class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int [targets.length];
        
        for(int n = 0; n< targets.length; n++){
            
            answer[n] = get(n, keymap, targets);
        }
        
        return answer;
    }
    
    private int get(int n, String[] keymap, String[] targets){
        int add = 0;
        for(int c = 0; c< targets[n].length(); c++){
    
            int min = (int)2e9;
            for(int k = 0; k< keymap.length; k++){
                for(int i = 0; i<keymap[k].length(); i++){
                    if(keymap[k].charAt(i) == targets[n].charAt(c)){
                        if(min > i)
                            min = i+1;
                        break;
                    }
                }
            }
            if(min == (int)2e9)
                return -1;
            add += min;
        }
        return add;
    }
}