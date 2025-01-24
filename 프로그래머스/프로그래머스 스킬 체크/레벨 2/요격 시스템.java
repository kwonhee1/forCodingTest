import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 1;
        ArrayList<Pair> arr = new ArrayList<Pair>();

        for(int i = 0; i<targets.length; i++){
            arr.add(new Pair(targets[i][0], targets[i][1]));
        }

        Collections.sort(arr, (a,b) -> {return a.start - b.start;});
        // 정렬 확인
        //arr.forEach(i -> System.out.print(i + ", "));
        //System.out.println("sort check end");

        // 첫번 째 값 강제 입력
        int start = arr.get(0).start;
        int end = arr.get(0).end;

        for(int i = 0; i< arr.size(); i++){
            // 현재 구간 내에서 처리가 가능한지 확인
            Pair p = arr.get(i);
            if(p.start < end){
                end = p.end < end ? p.end : end;
            }
            else{
                answer++;
                end = p.end;
            }
            //System.out.println(end);
        }

        return answer;
    }
}

class Pair{
    int start;
    int end;
    Pair(int s, int e){
        start = s; end = e;
    }
    public String toString(){
        return String.format("(%d, %d)", start, end);
    }
}