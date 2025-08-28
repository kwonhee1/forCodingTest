import java.util.*;

class Solution {

    public int solution(int n, int[][] computers) {
        ArrayList<NetworkPointer> pointerList = new ArrayList<>();

        int answer = 0;
        
        for(int i = 0; i < n; i ++)
            pointerList.add(new NetworkPointer());
        
        for(int y = 0; y < n; y++){
            for(int x = 0; x < y; x++){
                if(computers[y][x] == 1){
                    Network.linkNetwork(pointerList.get(x), pointerList.get(y));
                    //printAll(pointerList, n);
                }
            }
        }
        
        return Network.count;
    }
    private void printAll(ArrayList<NetworkPointer> pointerList, int n) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < n; i ++) {
            str.append("(" + i + ", ");
            if(pointerList.get(i).network == null)
                str.append("-1");
            else
                str.append(String.valueOf(pointerList.get(i).network.pk));
            str.append(")");
        }
        str.append(" :: " + Network.count);
        System.out.println(str.toString());
    }
}

class NetworkPointer{
    public Network network = new Network();
}

class Network{
    private static int pkNumber = 0;
    public static int count = 0;
    public int pk;
    private Network next;
    public Network() { pk = ++pkNumber; count++; }
    
    public static void linkNetwork(NetworkPointer left, NetworkPointer right){
        Network leftNetwork = Network.getLast(left);
        Network rightNetwork = Network.getLast(right);
        
        if(!leftNetwork.equals(rightNetwork)){
            leftNetwork.next = rightNetwork;
            count--;
        }
    }

    private static Network getLast(NetworkPointer pointer){
        Network network = pointer.network;
        // if(network == null)
        //     network = new Network();
        
        while (network.next != null) {
            network = network.next;
        }
        
        pointer.network = network;
        return network;
    }
    private boolean equals(Network other) { 
        return other != null && other.pk == this.pk;
    }
}