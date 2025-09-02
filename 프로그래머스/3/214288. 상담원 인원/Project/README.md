# 상담원 인원
https://school.programmers.co.kr/learn/courses/30/lessons/214288

## 문제 요구 사항 분석

1. 각 참가자는 1가지 유형에 대해서 상담을 요청할 수 있습니다.
2. 상담사는 1번에 1명의 참가자와 상담을 진행할 수 있고, 모든 상담사가 상담중인 경우 다음 참가자는 대기해야 합니다.
3. 주어진 모든 상담사의 수와 상담 신청 정보를 가지고 각 유형별로 상담사를 분배해주세요.
4. 모든 참가자의 대기 시간이 최소가 될때의 대기시간을 구하시오.

## 사용 알고리즘

1. stack
2. 완전탐색

## 풀이 방법

1. k, n의 범위를 고려하여 모든 reqs의 대기시간을 구하고 그 최소를 찾는다
2. k, n에 맞는 모든 reqs의 조합을 찾는다
```
int[] arr = new int[];
for(int y = 0; y <= 0; y++){
  if(y == arr.length) {
    마지막 요소 = k - (나머지 의 합);
    if(마지막 요소 >= 0) 
        대기시간을 구한다
    y -= 2; continue;
  }
  arr[y]++;
  if(0~y 까지의 값들의 합이 max(n)을 넘긴다면)
    (y ~ arr.length) 값들을 모두 0으로 초기화 한다
    y -= 2;
}
```
3. 각 reqs마다 대기시간을 구한다
```
class Consultant { int endTime; }
class Client {int start; int duration;}
1. 배정된 상담사 수 만큼을 가진 stack을 만든다
2. stack을 언제나 endTime 오름차순으로 정렬된 상태를 유지한다
3. 각 client마다 stack의 첫 번째 Consultant에게 상담을 받는다
    (Consultant.endTime > client.start)인 경우 대시가간을 추가한다
4. consultant를 stack에서 지우고 endTime을 최신화해서 stack에 다시 넣는다
    (stack이 endTime의 오름차순이 유지되게 넣는다)
5. 모든 client의 상담이 종료되면 대기시간을 반환한다
```

## 예상 함수

```
private int getTotalWaittedTime(int[][] reqs, int[] consultants);
class ConsultantPool { 
  public ConsultantPool(List<Client> clients, int consultantCount);
  public long getWaittedTime();
}
class Consultant { int end }
class Client { int start, int duration }
```

## 예상 시간, 공간 복잡도

### 시간 복잡도
15 명을 5 개로 분류함
15명을 순서 상관없이 1열로 세움
15사이에 4개의 막대를 설치함 (순서 상관없음, 막대로 나누어진 사람들 모임을 순서대로 5개 분류로 고려함)
막대를 둘수 있는 경우의 수(15를 5그룹으로 나눌수 있는 경우의 수) : 15*14*13*12*11/4*3*2*1
*
대기시간을 구함
15 * 5
=
15*7*13*11*15*5 = 1,126,125 (충분함)
### 공간 복잡도
계산하지 않음 (충분함)

# Test 코드
## test 대상 코드들
1. solution 
2. seqs의 모든 조합을 구했는지 여부
3. ConsultantPool의 Consultant statck이 언제나 endTime의 오름차순인지 여부
4. 대기 시간을 적절하게 구하는지 여부

# 코드

```
package org.example;

import java.util.*;

class Solution {
    int k; // 4
    int n; // 6
    int answer = (int)2e9;
    public int solution(int k, int n, int[][] reqs) {
        this.k = k; this.n = n;

        solution(reqs, k, n);

        return answer;
    }

    private void solution(int[][] reqs, int k, int n) {
        int[] arr = new int[k];
        for(int y = 0; y >= 0; y++){
            if(y == k-1){
                // 마지막 값을 구한다
                arr[k-1] = n - getSum(arr);
                if(arr[k-1] >= 1) solution(reqs, arr);

                y -= 2; continue;
            }
            arr[y]++;
            if(getSum(arr) >= n){
                for(int i = y; i < k; i++) arr[i] = 0;
                y-=2;
            }
        }
    }

    private static int getSum(int[] arr) {
        int sum = 0;
        for(int i = 0; i < arr.length-1; i++){sum += arr[i];}
        return sum;
    }

    private void solution(int[][] reqs, int[] consultants) {
        int totalSum = getTotalWaittedTime(reqs, consultants);
        answer = answer < totalSum ? answer : totalSum;
    }

    private int getTotalWaittedTime(int[][] reqs, int[] consultants) {
        //System.out.println("consultants : " + Arrays.toString(consultants));
        int totalSum = 0;
        List<List<Client>> schedules = new ArrayList<List<Client>>(k);
        for(int i = 0; i < k; i++){
            schedules.add(new ArrayList<Client>());
        }

        for(int i = 0; i < reqs.length; i++) {
            schedules.get(reqs[i][2] - 1).add(new Client(reqs[i][0], reqs[i][1]));
        }

        for(int i = 0; i < k; i++){
            totalSum += new ConsultantPool(schedules.get(i), consultants[i]).getWaittedTime();
            // System.out.println(i + "번 째 유형의 상담 :: " + Arrays.toString(schedules.get(i).toArray()) +" consultant count ="+consultants[i]+" sum=" + totalSum);
        }
        return totalSum;
    }
}

class ConsultantPool {
    private long sum = 0;
    List<Client> clients;
    private static Consultant start = new Consultant((int)2e9, null);
    public ConsultantPool(List<Client> clients, int consultantCount) {
        this.clients = clients;
        Consultant consultant = start;
        for(int i = 0; i < consultantCount; i++){
            consultant.next = new Consultant(0, null);
            consultant = consultant.next;
        }
    }
    public long getWaittedTime() {
        for(Client client : clients){
            // System.out.println(Arrays.toString( getConsultantEndTime()) );

            Consultant consultant = start.next;
            start.next = consultant.next;

            if(consultant.end > client.start){
                sum += consultant.end - client.start;
                appendToList(consultant, client.duration + consultant.end);
            }else{
                appendToList(consultant, client.duration + client.start);
            }
        }
        return sum;
    }
    private void appendToList(Consultant target, int endTime) {
        Consultant consultant = start;
        while(consultant.next != null && consultant.next.end < endTime) { consultant = consultant.next; }

        target.end = endTime;
        target.next = consultant.next;
        consultant.next = target;
    }
    private int[] getConsultantEndTime() {
        ArrayList<Integer> arr = new ArrayList<>();
        Consultant consultant = start.next;
        while(consultant != null) { arr.add(consultant.end); consultant = consultant.next; }
        return arr.stream().mapToInt(Integer::intValue).toArray();
    }
}

class Consultant {
    public int end;
    public Consultant next;
    public Consultant(int end, Consultant next) { this.end = end; this.next = next; }
}

class Client {
    public int start;
    public int duration;
    public Client(int start, int duration) { this.start = start; this.duration = duration; }
    public String toString() { return "Client (" + start + ", " + duration + ")"; }
}

// 15 명을 5 개로 분류함
// 15명을 순서 상관없이 1열로 세움
// 15사이에 4개의 막대를 설치함 (순서 상관없음, 막대로 나누어진 사람들 모임을 순서대로 5개 분류로 고려함)
// 막대를 둘수 있는 경우의 수(15를 5그룹으로 나눌수 있는 경우의 수) : 15*14*13*12*11/4*3*2*1
// (15를 5그룹으로 나눌수 있는 경우의 수) * 300 = 약 1200000 백만번 이내 (충분함)
```

