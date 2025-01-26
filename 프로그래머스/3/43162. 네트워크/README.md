# 43162. 네트워크

## 문제 요구 사항 분석
1.  컴퓨터 상호 간 직접적으로 이어진 네트워크의 정보를 입력 받는다
3. 직접 연결되지 않더라도 다른 컴퓨터를 통해 네트워크에 연결된다면 간접적으로 같은 네크워크로 가정한다
4. 직접, 간접 연결된 컴퓨터들은 모두 간은 네트워크 상에 있다고 가정할 때 각각의 다른 네트워크의 개수를 구하시오 
## 입력 사항
1. 컴퓨터의 개수 n은 1이상 200이하의 자연수
2. 직접적 네트워크 배열  int[][] computers[n-1][n-1]
	computers[i][j] 는 i-1번째 컴퓨터와 j-1번째 컴퓨터의 직접적 네트워크 상태를 나타냄
	0 : 연결되지 않음, 1 : 연결됨
	computers[i][i]는 항상 1로 일정
## 출력 사항
1. 각각의 이어지지 않은 네트워크의 개수를 int로 반환

## 입출력 예시
입력 1    
> n=3, computers : [[1, 1, 0], [1, 1, 0], [0, 0, 1]]

출력 1
> 2

입력 2
> n = 3, computers : [[1, 1, 0], [1, 1, 1], [0, 1, 1]]

출력 2
> 1

## 풀이 방법
1. i번째 컴퓨터가 네트워크에 연결됬는지 확인하는 배열 선언 
	```
	int[] visite[n] (0으로 초기화)
   ```
2. i번째 컴퓨터부터 컴퓨터와 연결된 모든 컴퓨터를 찾고 que에 넣는다
	```
	for(int j = 0; j < n; j++){
		if(i와 j가 연결되었다면 && 다른 컴퓨터와 연결된적 없다면){
			que에 j를 넣는다
			visite에 최신화 한다
		}
	}
	```
3. que에 있는 모든 컴퓨터에 대해서 2번 작업을 진행한다
	```
	while(que가 존재한다면){
		2번 작업 처럼 연결된 모든 컴퓨터를 찾는다
	}
	```
4. 2,3 번의 실행 한번에 네트워크 한개로 간주하고 아직 연결되지 않은 모든 컴퓨터에 반복한다
## 예상 함수
	void constructer
	public int solution(int n, int[][] computers);
	void findAllDirectlyConnectedComputer(int i);
	void findAllIndirectlyConnectedComputer();
	boolean isConnected(int i, int j);
## 예상 시간, 공간 복잡도
### 시간 복잡도 n^2
	실행 횟수가 직접적 네트워크의 상태인 computers를 모두 순회하며, 중복 순회하지 않음으로 n^2
### 공간 복잡도 4n^2
	computers : int[n][n] = 4n^2
	visite : int[n] : 4n
	que : int[n] : 4n