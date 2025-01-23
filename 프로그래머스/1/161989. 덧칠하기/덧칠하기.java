class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int i = 0;
        
        int[] arr = new int[n]; // 1 : 칠해짐 , 0 : 칠해야함
        for(int x = 0; x < n; x++){
            arr[x] = 1;
        }
        
        for(int x : section){
            arr[x-1] = 0;
        }
        
        while(i < n){
            if(arr[i] == 1){
                i++;
                continue;
            }
            
            answer++;
            i += m;
        }
        
        return answer;
    }
}