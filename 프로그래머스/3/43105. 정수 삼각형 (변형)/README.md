# 43105. 정수 삼각형 (변형)

## 문제 요구 사항 분석
1.  가중치(int)를 정보를 담은 피라미드 형태의 배열을 입력 받는다
2.  꼭대기에서 아래 칸으로 이동할 때는 대각선으로만 이동 가능하다
3. 꼭대기에서 최하층까지 이동 가능한 길들 중 지나온 가중치가 가장 높은 길을 찾는다 (지나온 모든 길들의 가중치의 합을 구한다)
4. 변형 : 지나온 길들의 가중치를 배열로 출력하시오
## 입력 사항
1. 가중치를 표현한 배열 int[][] triangle
## 출력 사항
1. 지나온 모든 길의 가중치의 합
2. 지나온 길의 가중치 배열을 반환 (변형)

## 입출력 예시
입력 1 
> triangle =  [[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]]

출력 1
>  30
>  [7,3,8,7,5]
## 풀이 방법
1. h줄 n번째 (h-1, i-1), (h-1, i+1)두개의 값을 비교해서 triangle[h][n]값을 최신화 한다
	```
	if(triangle[h-1][(i-1) / 2] > triangle[h-1][(i+1)/2])
		triangle[h][i] = 더 큰 값
	```
2. 1번 단계를 2번째 줄부터 꼭대기 까지 반복하여 모든 triangle을 완성한다
3. 맨 위의 점부터 다시 내려오면서 완성된 triangle을 통해 길을 탐색한다
	```
	if(아래 좌 > 아래 우)  
		path[h] = 더 큰 값의 가중치
	```

## 예상 함수
	public int solution(int[][] triangle)
	protected void updateTriagle()
	protected int getWeight(int h, int w);
	protected void getPath(int[][] origin)
## 예상 시간, 공간 복잡도
	height = triangle.length;
    width = triangle[height-1].length;
### 시간 복잡도 n^2
주어진 배열 triangle을 넘거나 중복 순회하지 않음으로
height * heigth * 2/1
### 공간 복잡도 n^2
주어진 배열을 origin triangle 2개 저장 해야함으로
2 * height * height * 2/1
