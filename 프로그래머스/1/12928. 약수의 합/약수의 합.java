class Solution {
    private int add;
    private int input;
    public int solution(int input) {
        this.input = input;

        next();
        
        return add;
    }
    
    private void next(){
        for(int i =1; i<=input; i++){ // 1과 자기 자신
            if(input % i == 0){
                add += i;
            }
        }
    }
}