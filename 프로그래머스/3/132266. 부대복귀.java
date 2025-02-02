import java.util.*;
class Solution {
    private int destination;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        this.destination = destination;
        
        // create all Nodes
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node(0)); // input nodes(0) empty Node
        for(int i = 1; i <= n; i++){
            nodes.add(new Node(i));
        }
        
        // read roads and update all Nodes nodes
        for(int i = 0; i < roads.length; i++){
            Node a = nodes.get(roads[i][0]);
            Node b = nodes.get(roads[i][1]);
            a.addNode(b);
            b.addNode(a);
        }
        
        // for () find path
        for(int i = 1; i<=sources.length; i++){
            clearAllNodeDistances(nodes);
            answer[i-1] = nodes.get(sources[i-1]).findPath();
        }
        
        return answer;
    }
    public void clearAllNodeDistances(ArrayList<Node> nodes){
        for(Node n : nodes){
            n.reseatDistance();
        }
    }
    
    class Node{
        private int value;
        private int distance;
        private ArrayList<Node> nodes = new ArrayList<Node>();
        
        public int findPath(){
            LinkedList<Node> stack = new LinkedList<Node>();
            setDistance(1);
            stack.offer(this);
            
            Node node;
            int distance;
        
            while(!stack.isEmpty()){
                node = stack.pop();
                distance = node.getDistance();
            
                if(node.isDestination())
                    return distance -1;
            
                for(Node n : node.getNodes()){
                    if(n.getDistance() != 0)
                        continue;
                    n.setDistance(distance+1);
                    stack.offer(n);
                }
            }
        
            return -1;
        }
    
        public Node(int v) { value = v;}
        public void addNode(Node n){nodes.add(n);}
        public void reseatDistance(){distance = 0;}
        public void setDistance(int d){distance = d;}
        public int getDistance(){return distance;}
        public boolean isDestination(){
            return value == destination;
        }
        public ArrayList<Node> getNodes(){return nodes;}
    }
}