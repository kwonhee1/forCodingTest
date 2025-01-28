import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        // instance all nodes
        Node nodeBegin = new Node(begin);
        ArrayList<Node> allNodes = Node.createAll(words);
        allNodes.add(nodeBegin);
        
        // init all node.nodes
        allNodes.forEach(n->n.addAll(allNodes));
        
        return findTarget(nodeBegin, target);
    }
    
    private int findTarget(Node start, String end){
        LinkedList<Node> list = new LinkedList<Node>();
        start.setLevel(1);
        list.offer(start);
        
        Node node;
        while(!list.isEmpty()){
            node = list.pop();
            
            // 해당 node, level
            System.out.println(node.toString());
            
            if(end.equals(node.getValue()))
                return node.getLevel()-1;
            
            int level = node.getLevel();
            ArrayList<Node> nodes = node.getNodes();
            
            for(Node n : nodes){
                if(n.getLevel() != 0)
                    continue;
                
                n.setLevel(level+1);
                list.offer(n);
            }
        }
        
        return 0;
    }
}

class Node{
    private String value;
    private int level; // 0 : default (not visite), other : already visitie
    protected ArrayList<Node> nodes = new ArrayList<Node>();
    
    public Node(String value){
        this.value = value;
    }
    public static ArrayList<Node> createAll(String[] values){
        ArrayList<Node> nodes = new ArrayList<Node>();
        for(String value : values){
            nodes.add(new Node(value));
        }
        return nodes;
    }
    
    protected void addAll(ArrayList<Node> others){
        others.forEach(n -> {
            if(isCanChange(this, n)){ 
                nodes.add(n);
                //System.out.println(toString() + "<>" + n.toString());
            }
        });
    }
    
    protected static boolean isCanChange(Node a, Node b){
        if(a == b)
            return false;
        
        int count = 0;
        for(int i = 0; i<a.value.length(); i++){
            if(a.value.charAt(i) != b.value.charAt(i))
                count++;
        }
        
        return count == 1;
    }
    
    public String toString(){
        return String.format("%s, %d", value, level);
    }
    
    public String getValue(){return value;}
    public int getLevel(){return level;}
    public void setLevel(int level) {this.level = level;}
    public ArrayList<Node> getNodes(){return nodes;}
}