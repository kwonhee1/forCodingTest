class Solution {
    public boolean solution(int x) {
        int add = 0;
        int input = x;
        
        while(input != 0){
            add += input % 10;
            input /= 10;
        }
        
        return x % add  == 0;
    }
}