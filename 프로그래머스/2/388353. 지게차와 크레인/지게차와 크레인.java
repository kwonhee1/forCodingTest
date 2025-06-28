import java.util.*;

// emun status 0:empty, 1:edge, 2:lift

// Lift
//  findAllToLift :: check four round
//  changeLiftToEdge
// Excavator
//  findAllToEmpty
// All
//  findAllEdge
// Stack Edges // n
//  addEdge
//  findAllEdges :: change to edge


class Solution {
    char[][] arr;
    int maxY;
    int maxX;
    int directX[] = {0,0,1,-1};
    int directY[] = {1,-1,0,0};
    Stack start = new Stack(-1,-1);
    Stack last = start;
    public int solution(String[] storage, String[] requests) {
        maxY = storage.length;
        maxX = storage[0].length();
        arr = new char[maxY][maxX];
        
        for(int y = 0; y < maxY; y++){
            for(int x = 0; x < maxX; x++){
                arr[y][x] = storage[y].charAt(x);
            }
        }
        
        Lift lift = new Lift();
        Excavator excavator = new Excavator();
        char target;
        printArr();
        for(int i = 0; i < requests.length; i++){
            target = requests[i].charAt(0);
            if(requests[i].length() == 1){
                // Lift
                lift.findAll(target);
            }else{
                // excavator
                excavator.findAll(target);
            }
            // check stack
            start.findEdge(start);
            printArr();
            start.printAll(start);
        }
        
        // get count
        int count = 0;
        for(int y = 0; y<maxY; y++){
            for(int x = 0; x< maxX; x++){
                if(arr[y][x] != status.empty.value && arr[y][x] != status.edge.value)
                    count++;
            }
        }
        return count;
    }
    
    enum status {
        empty(30), edge(31), lift(32);
        char value;
        status(int c) { value = (char)c; }
    }
    
    private class Lift{
        public void findAll(char target){
            findAllToLift(target);
            changeLiftToEdge();
        }
        private void findAllToLift(char target){
            for(int y = 0; y < maxY; y++){
                for(int x =0; x< maxX; x++){
                    if(arr[y][x] == target && checkFourRoundHasEdge(y, x))
                        arr[y][x] = status.lift.value;
                }
            }
        }
        private boolean checkFourRoundHasEdge(int y, int x){
            if (y == maxY-1 || y == 0 || x ==0 || x == maxX-1)
                return true;
            for(int d = 0; d < 4; d++){
                if(!checkIsInArr(y+directY[d], x+directX[d]))
                    continue;
                if(arr[y+directY[d]][x+directX[d]] == status.edge.value)
                    return true;
            }
            return false;
        }
        private void changeLiftToEdge(){
            for(int y = 0; y < maxY; y++){
                for(int x =0; x< maxX; x++){
                    if(arr[y][x] == status.lift.value){
                        arr[y][x] = status.edge.value;
                        last = last.add(y, x);
                    }
                }
            }
        }
    }
    private class Excavator{
        public void findAll(char target){
            for(int y = 0; y < maxY; y++){
                for(int x = 0; x < maxX; x++){
                    if(arr[y][x] == target){
                        arr[y][x] = status.empty.value;
                        if(y==0 || y==maxY-1 || x==0 || x==maxX-1){
                            arr[y][x] = status.edge.value;
                            last = last.add(y,x);
                        }
                    }
                }
            }
        }
    }
    class Stack{
        int y;
        int x;
        Stack next;
        public Stack add(int y, int x) {
            //System.out.println("add stack :: "+y+", "+x);
            Stack newStack = new Stack(y, x);
            next = newStack;
            return newStack;
        }
        public Stack(int y, int x) {
            this.y =y; this.x = x;
        }
        public void findEdge(Stack start) {
            Stack stack = start.next;
            Stack old = start;
            for(; stack!=null; ) {
                if(checkFourRoundIsEdge(stack.y, stack.x)){
                    // erase stack.next
                    // System.out.println("erase stack :: " + stack.y +", "+ stack.x);
                    // old.next = stack.next;
                    // if(old == start)
                    //     last = start;
                }
                old = stack;
                stack = stack.next;
            }
        }
        private boolean checkFourRoundIsEdge(int y, int x){
            int dy; int dx;
            int edgeCount = 0;
            for(int d = 0; d < 4; d++){
                dy = directY[d] + y;
                dx = directX[d] + x;
                
                if(!checkIsInArr(dy,dx)){
                    edgeCount++;
                    continue;
                }
                if(arr[dy][dx] ==status.empty.value){
                    arr[dy][dx] = status.edge.value;
                    last = last.add(dy, dx);
                    edgeCount++;
                }
                else if(arr[dy][dx] ==status.edge.value)
                    edgeCount++;
            }
            return edgeCount == 4;
        }
        private void printAll(Stack start){
            // System.out.print("stack printall :: ");
            // for(Stack stack = start.next; stack!=null; stack=stack.next){
            //     System.out.print(String.format("(%d, %d)=%c, ", stack.y, stack.x, arr[stack.y][stack.x]));
            // }
        }
    }
    private boolean checkIsInArr(int y, int x){
        if(0<= y && y < maxY && 0<= x && x < maxX)
            return true;
        return false;
    }
    
    private void printArr() {
        // System.out.println("print all");
        // for(int y = 0; y < maxY; y++){
        //     for(int x = 0; x < maxX; x++){
        //         System.out.print(String.format("%2c", arr[y][x]));
        //     }
        //     System.out.println("");
        // }
    }
}