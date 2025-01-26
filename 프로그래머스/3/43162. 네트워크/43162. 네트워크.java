import java.util.*;
class Solution {
    private int n;
    private int[][] computers;
    protected int[] visite; // 자동 0으로 초기화 됨
    Stack<Integer> stack = new Stack<Integer>();
    
    public int solution(int n, int[][] computers) {
        this.n = n;
        this.computers = computers;
        
        visite = new int[n];
        int answer = 0;
        
        for(int i = 0; i < n; i++){
            if(visite[i] == 1)
                // already visite before >> skip
                continue;
            answer++;
            System.out.println("\n"+i+" : ");
            
            findAllDirectlyConnectedComputer(i);
            findAllindirectlyConnectedComputer();
        }
        
        return answer;
    }
    
    protected void findAllDirectlyConnectedComputer(int i){
        // find all directly connected computer
        for(int j = 0; j < n; j++){
            if(visite[j] == 1)
                // already visite before >> skip
                continue;
            
            if(isConnected(i, j)){
                System.out.print(j+" ");
                // j is connected i
                // update vitie, add stack j
                visite[j] = 1;
                stack.push(j);
            }
        }
    }
    
    protected void findAllindirectlyConnectedComputer(){
        while(!stack.isEmpty()){
            findAllDirectlyConnectedComputer(stack.pop());
        }
    }
    
    protected boolean isConnected(int i, int j){
        return computers[i][j] == 1;
    }
}