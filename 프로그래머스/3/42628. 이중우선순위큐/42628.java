import java.util.*;
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
        length++;
        first = this; last = next;
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
        
        // left less than 3 elements
        // if(end - start <= 2)
        //     return stand;
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
}