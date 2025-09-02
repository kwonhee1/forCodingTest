import java.util.*;
import java.util.stream.Collectors;
class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        Node[] nodes = new Node[n+1];
        int[] visit = new int[n+1]; // 0이면 간적 없음 / 0크면 값
        
        for(int i = 0; i <= n; i++){
            nodes[i] = new Node(i);
            visit[i] = -1;
        }
        
        // link all raods
        for(int i = 0; i < roads.length; i++){
            int target1 = roads[i][0];
            int target2 = roads[i][1];
            Node.linkRoad(nodes[target1], nodes[target2]);
        }
        
        //check
        // for(int i = 0; i <= n; i++){
        //     System.out.println(nodes[i].toString());
        // }
        
        // get all Node's legth from destination
        LinkedList<Stack> que = new LinkedList<>();
        que.addLast(new Stack(nodes[destination], 0));
        visit[destination] = 0; 
        
        while(!que.isEmpty()) {
            Stack stack = que.removeFirst();
            stack.node.roads.stream().forEach(node->{
                if(visit[node.position] == -1){
                    visit[node.position] = stack.level+1;
                    que.add(new Stack(node, stack.level+1));
                }
            });
        }
        
        // //check
        // for(int i = 0; i <= n; i++){
        //     System.out.println(i + "번째 :: " + visit[i]);
        // }
        
        // sources 값들 반환하기
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++){
            answer[i] = visit[sources[i]];
        }
        return answer;
    }
    
}

class Node{
    public int position;
    public Set<Node> roads = new HashSet<>();
    public Node(int p) { position = p; }
    
    public static void linkRoad(Node a, Node b) {
        a.roads.add(b); b.roads.add(a);
    }
    public int[] getLinkedRoads() {
        return roads.stream().mapToInt(n->n.position).toArray();
    }
    public String toString() {return position+"번 :: "+ Arrays.toString(getLinkedRoads());}
}

class Stack{
    int level;
    Node node;
    public Stack(Node n, int l ) { node = n; level = l;}
}