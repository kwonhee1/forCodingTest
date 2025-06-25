// 4, 6 36+16 52
class Solution {
    int x; int y;
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        this.y = n;
        this.x = m;
        int[] answer = new int[balls.length];
        for(int i = 0; i < balls.length; i++){
            answer[i] = getMostShort(startY, startX, balls[i][1], balls[i][0]);
        }
        return answer;
    }
    
    private Integer getMostShort(int standY, int standX, int targetY, int targetX) {
        int min = Integer.MAX_VALUE;
        int buff;
        
        // System.out.println(String.format("most min : (%d, %d) <> (%d, %d)", standY, standX, targetY, targetX));
        
        if(!(standY == targetY && standX > targetX)){
            buff = getDistance(standY, standX, targetY, -targetX); // 왼쪽 벽
            if(buff < min)
                min = buff;
        }
        if(!(standY == targetY && standX < targetX)){
            buff = getDistance(standY, standX, targetY, x+x-targetX); // 오른쪽 벽
            if(buff < min)
                min = buff;
        }
        if(!(standX == targetX && standY > targetY)){
            buff = getDistance(standY, standX, -targetY, targetX); // 아래쪽 벽
            if(buff < min)
                min = buff;
        }
        if(!(standX == targetX && standY < targetY)){
            buff = getDistance(standY, standX, y+y-targetY, targetX); // 위쪽 벽
            if(buff < min)
                min = buff;
        }

        return min;
    }
    
    private Integer getDistance(int y1, int x1, int y2, int x2) {
        int y = y1- y2;
        int x = x1- x2;
        // System.out.println(String.format("(%d, %d) <> (%d, %d) :: %d", y1,x1,y2,x2,x*x + y*y));
        return x*x + y*y;
    }
}