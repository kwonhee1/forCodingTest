import java.util.*;

class Solution {
    private ArrayList<Robot> robots;
    private int[][] map;
    private int answer;
    private int[][] points;
    private int[][] routes;
    
    private int maxY;
    private int maxX;
    public int solution(int[][] p, int[][] r) {
        // constructor
        maxY = 101; maxX = 101;
        points = p; routes = r;
        robots = new ArrayList<Robot>(routes.length);
        map = new int[maxY][maxX];
        answer = 0;
        
        for(int y = 0; y < maxY; y++){
            for(int x = 0; x < maxX; x++){
                map[y][x] = 0;
            }
        }
        
        for(int i = 0; i < routes.length; i++) {
            robots.add(new Robot(i, points[routes[i][0] - 1][0], points[routes[i][0] - 1][1]));
            map[points[routes[i][0] - 1][0]][points[routes[i][0] - 1][1]]++;
        }
        
        checkMap();
        
        while(robots.size() > 0) {
            printAllMap();
            Iterator<Robot> iterator = robots.iterator();
            while(iterator.hasNext()) {
                Robot robot = iterator.next();
                if(robot.direct == routes[robot.number].length) {
                    map[robot.y][robot.x]--;
                    iterator.remove();
                    continue;
                }
                moveRobot(robot);
            }
            checkMap();
        }
        printAllMap();
        
        return answer;
    }
    public void moveRobot(Robot robot) {
        int targetY = points[routes[robot.number][robot.direct] - 1][0];
        int targetX = points[routes[robot.number][robot.direct] - 1][1];
        
        map[robot.y][robot.x]--;
        
        // Y값 우선
        if(targetY != robot.y)
            robot.move(targetY > robot.y ? 1 : -1, 0);
        else
            robot.move(0, targetX > robot.x ? 1 : -1);
        
        map[robot.y][robot.x]++;
        
        if(robot.y == targetY && robot.x == targetX){
            robot.direct++;
        }
    }
    
    private void checkMap() {
        for(int y = 0; y < maxY; y++){
            for(int x = 0; x < maxX; x++){
                if(map[y][x] > 1) {
                    //System.out.println("check map :: (" +y + ", " + x + ")" );
                    answer++;
                }
            }
        }
    }
    
    private void printAllMap() {
        // System.out.println("print all map");
        // for(int y = 0; y <maxY; y++) {
        //     for(int x = 0; x< maxX; x++){
        //         System.out.print(map[y][x] + " ");
        //     }
        //     System.out.println();
        // }
    }
}

class Robot{
    int y;
    int x;
    int number;
    int direct;
    
    public Robot(int number, int startY, int startX){
        this.number = number; y = startY; x = startX; direct = 1;
    }
    
    public void move(int dy, int dx) {
        y += dy; x += dx;
    }
}

// 200 *100 * 100 = 2000000 