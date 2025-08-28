# 네트워크

## 문제 요구 사항 분석  

1. 컴퓨터 A와 컴퓨터 B가 같은 네트워크에 있고 컴퓨터 B와 컴퓨터 C가 같은 네트워크에 있다면 컴퓨터 A,B,C모두 같은 네트워크에 있다고 할 수 있습니다.
2. 주어진 컴퓨터의 네트워크 연결 정보를 가지고 다른 네트워크의 개수를 구하시오.

## 사용 알고리즘  

 1.  그리디
 2. 각 컴퓨터마다 네트워크 stack을 만들고 새로운 연결을 할 때 마다 stack에 다음 연결할 네트워크를 넣음
 3. 네트워크를 조회 할 때마다 해당 컴퓨터의 네트워크를 stack의 마지막 값으로 최신화함

## 입력 사항

1. int n :: 총 컴퓨터의 개수
2. boolean computers[][] :: 컴퓨터 연결 정보 
	> computer[i][j] == 1 인 경우 i 과 j 은 같은 네트워크에 있다.
	> computer[i][j] == 0 인 경우 i과 j은 직접 연결되어있지 않다.
	> 0<= i,j <= n

## 출력 사항

1.  다른 네트워크의 개수

## 풀이 방법

1.  모든 컴퓨터에 각자의 network를 배정한다
	> class Network { Network next ; }
2. computers[i][j] == 1인 경우 컴퓨터 i, j의 네트워크를 연결한다
	> i번.next = j;
3. 시간복잡도를 위해 i번째 network를 조회 할 때 마다 next값이 있다면 i의 네트워크를 next로 업데이트 한다.
	> if(i번.network.next != null) { i번.network = i번.network.next; }
4. 총 네트워크의 개수를 센다.


## 예상 함수

```
class NetworkPointer
	public Network network;

class Netwrok
	public static void linkNetwork(NetworkPointer left, NetworkPointer right);
	private static Network getLastNetwork(NetworkPointer pointer);
	private boolean equals(Network other);
```

## 예상 시간, 공간 복잡도

### 시간 복잡도 n^2
n * n / 2 만큼 반복하며 computers를 순회
계속 network를 최신화 하였음으로 linkedNetwork함수는 1회로 처리

n * n/2 * 1 = n * n / 2
### 공간 복잡도
계산하지 않는다

# 코드

```
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
```

