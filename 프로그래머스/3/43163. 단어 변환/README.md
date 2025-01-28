# 43163. 단어 변환

## 문제 요구 사항 분석
1. 주어진 begin단어를 target단어로 규칙에 따라 변환 할 때 최소한의 변환 횟수를 구하시오
> 규칙 1, 한 개의 알파벳만 다른 단어로만 변환 가능
> 규칙 2. words에 있는 단어로만 변환 가능 
## 입력 사항
1. begin, target단어 (3이상 10이하 길이의 단어)
2. 변환 가능한 문자들의 배열 words
## 출력 사항
1. begin을 target까지 변환 할 때 변환 가능한 횟수
2. 변환할 수 없는 경우 0을 반환

## 입출력 예시
입력 1 
> begin = "hit", target="cog", words=["hot", "dog", "dot" , "lot", "cog"]

출력 1
>  4

입력 2
> begin = "hit", target="cog", words=["abc"]

출력 2
> 0
## 풀이 방법
1. 사용할 node class 선언
	> class Node{ ArrayList\<Node\> nodes, int level; }
2. 모든 노드 선언, 노드 연결
3. 모든 노드를 순회 하면서 깊이 우선 탐색 진행 (target 을 찾을 때 까지)

## 예상 함수
	public int solution(String begin, String target, String[] words)
	protected boolean isCanChange(Node a, Node b)
	protected int findTarget()
	protected String[] findPath()
## 예상 시간, 공간 복잡도
### 시간 복잡도 10n^2
	노드 생성 : 모든 노드 * 모든 노드 -1 * 변환 가능 여부 판단(String 길이) = 10n^2
	모든 노드 순회 : n
### 공간 복잡도 ?n^2
	queue : 최대 n개 * sizeof(Node) = n*(내부 변수 개수 * 4) = ?n
	words : 이하 동일 ?n
	입력 변수 공간 : ?n
