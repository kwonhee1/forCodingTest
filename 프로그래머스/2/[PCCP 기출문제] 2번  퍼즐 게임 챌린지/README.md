# [PCCP 기출문제] 2번 / 퍼즐 게임 챌린지
https://school.programmers.co.kr/learn/courses/30/lessons/340212

## 문제 요구 사항 분석
1. 주어진 문제를 제한 시간 limit내에 모두 해결할 수 있는 숙련도 level의 최소값을 구하시오.
2. 문제별 난이도 diff, 소요 시간time, 제한 시간 limit이 주어진다.
3. diff > level이면 총 diff - level 만큼 틀리고 한 번 틀릴 때 마다 직전 문제와 해당 문제를 한번 씩 더 풀어야 합니다
	>  diff <= level :: time 만큼의 시간을 소요
	> diff > level :: (diff[i] - level) * (time[i] + time[i-1]) + time[i] 
## 입력 사항
1. 문제별 난이도 diff, 소요시간 time의 배열
 > diffs[], times[]
2. 제한 시간 limit
## 출력 사항
1. 제한 시간내 모든 문제를 해결할 수 있는 숙련도의 최소

## 입출력 예시
입력 1 
> diffs = [1, 5, 3]
> times = [2, 4, 7]
> limit = 30

출력 1
>  3

입력 2 
> diffs = [1, 4, 4, 2]
> times = [6, 3, 8, 2]
> limit = 59

출력 2
>  2

## 풀이 방법
1. 모든 문제의 times[i]값은 숙련도 level과 상관없이 무조건 소요해야하는 시간임
 > result -= for(times);
2. diffs의 값들을 정렬 해서 diff[n] - 1의 난이도로 제한 시간내 문제를 해결할 수 있는지 확인한다. n^2
 >  for ++{( diff[i] - diff[n]-1 ) * (diff[i] + diff[i-1]) }< result
3. 난이도의 값의 경계가 정해졌음으로 ax + b < result의 수식을 풀어 가장 낮은 난이도를 구한다.
## 예상 함수
	 모두 하드 코딩
	 
## 예상 시간, 공간 복잡도
### 시간 복잡도 n^2
