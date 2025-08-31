# 42628. 이중우선순위큐

## 문제 요구 사항 분석

1. 오름차순으로 정렬되는 큐를 완성하시오
2. 주어진 연산을 실행하고 [최댓값, 최솟값] 을 반환하시오

| 명령어 | 연산 |
| --- | --------- |
| `I 숫자` | 큐에 주어진 숫자를 삽입 | 
| `D 1` | 큐에서 최댓값 삭제 |
| `D -1` | 큐에서 최솟값 삭제 |

3. 만약 큐가 비었다면 [0, 0]을 반환하시오

## 사용 알고리즘

1. 큐
2. 이진 탐색

## 예시 

입력1 : ["I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"]
출력1 : [0, 0]

입력2 : ["I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"]
출력2 : [333, -45]

## 풀이 방법

1. 주어진 숫자에 대해서 que에서 이진 탐색을 통해 삽입위치를 정한다 (que를 정렬된 상태로 유지한다)
2. que에 이진탐색을 더 쉽게하기 위해, D 1, D -1의 연산을 줄이기 위해 start(2e9), end(-2e9)를 지정한다
3. que에 static변수를 두어서 start, end 지점과 length값을 기억한다


## 예상 함수

```
public void add(int value);
private Que find(int value, int start, int end); // 삽입 전 Que를 반환
private Que move(int index); // index만큼 실행됨
public void popMin(); public void popMax();
```

## 예상 시간, 공간 복잡도

### 시간 복잡도
이진 탐색 : log n
실행 횟수 : n번
n * log n = 7000000
### 공간 복잡도
이진 탐색을 재귀적 호출로 구현할 경우 : log n * 20 byte = 140 byte 이내

# Test code

## Test 대상
1. Main solution 함수
2. Que
    > que.add()
    > que.move()

# 코드

```
package org.example;

import java.util.Arrays;

class Solution {
    public int[] solution(String[] operations) {
        Que first = new Que();

        for(String older : operations) {
            String[] olders = older.split(" ");
            switch(olders[0]) {
                case "I":
                    first.add(Integer.valueOf(olders[1]));
                    break;
                case "D":
                    if(olders[1].equals("1"))
                        first.popMax();
                    else
                        first.popMin();
            }
            // System.out.println(first.toString());
        }
        return first.end();
    }
}

class Que {
    private static int length = 0;
    private static Que first;
    private static Que last;
    private int value;
    private Que next;
    private Que old;
    public Que() {
        this.value = (int) 2e9 * -1;
        next = new Que((int)(2e9));
        next.old = this;
        first = this; last = next;
        length = 2;
    }
    private Que(int v) { this.value = v; length++; }

    public void add(int value){
        Que target = add(value, 1, length);
        Que newQue = new Que(value);
        newQue.next = target.next;
        newQue.old = target;
        target.next.old = newQue;
        target.next = newQue;
    }
    private Que add(int value, int start, int end) {
        int standIndex = (end + start) / 2;
        Que stand = this.move(standIndex - start);
        StringBuilder str = new StringBuilder(value + " :: ");

        while(end - start >= 3) {
            str.append("(" + standIndex + ", " + stand.value + " )");
            if(stand.value == value) {
                return stand;
            }
            else if(stand.value < value){
                start = standIndex;
                // 1 3 5 -> 3 4 5 | 1 3 6 -> 4 5 6
            }
            else{
                end = standIndex;
                // 1 3 5 -> 1 2 3
            }
            int nextStandIndex = (end + start) / 2;
            stand = stand.move(nextStandIndex - standIndex);
            standIndex = nextStandIndex;
        }

        // System.out.println(str.toString());

        if(stand.value < value)
            return stand;
        return stand.old;
    }
    private Que move(int index) {
        Que que = this;
        if(index >= 0){
            while(index-- != 0)
                que = que.next;
        }else{
            while(index++ != 0)
                que = que.old;
        }
        return que;
    }
    public void popMin(){
        if(length == 2)
            return;
        first.next.next.old = first;
        first.next = first.next.next;
        length--;
    }
    public void popMax(){
        if(length == 2)
            return;
        last.old.old.next = last;
        last.old = last.old.old;
        length--;
    }
    public int[] end(){
        if(length == 2)
            return new int[] {0, 0};
        return new int[] {last.old.value, first.next.value};
    }
    public String toString() {
        StringBuilder str = new StringBuilder();
        Que stand = first;
        while(stand != null) {
            str.append(stand.value);
            str.append(" ");
            stand = stand.next;
        }
        str.append(" :: ");
        str.append(first.length);
        return str.toString();
    }

    // for just test
    public int[] toIntegerArray() {
        int[] result = new int[length-2];
        Que stand = first.next;

        for(int i = 0; i < length-2; i++, stand = stand.next) {
            result[i] = stand.value;
        }

        return result;
    }
}
```

