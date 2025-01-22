class Solution {
    public int solution(String[][] board, int h, int w) {
        int[] directY = {0,0,1,-1};
        int[] directX = {1,-1,0,0};
        int answer = 0, dx, dy;
        String compare = board[h][w];
        
        // 4가지 방향을 모두 돌면서
        for(int d = 0; d < 4; d++){
            dy = directY[d] + h;
            dx = directX[d] + w;
            
            if(0<=dy && dy < board.length && 0<= dx && dx < board[0].length)
                if(board[dy][dx].equals(compare))
                    answer++;
        }
        return answer;
    }
}