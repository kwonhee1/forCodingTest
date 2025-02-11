import java.util.*;

class Solution {
    private int len;
    private int startday;
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        len = schedules.length;
        this.startday = startday-1;

        ArrayList<Person> persons = new ArrayList<Person>(timelogs[0].length);
        for(int[] logs : timelogs){
            persons.add(new Person(logs));
        }
        
        int answer = 0;
        for(int i = 0; i < len; i++){
            Person p = persons.get(i);
            if(p.check(schedules[i], 0, 10)){
                //System.out.println("true");
                answer++;
            }
        }
        return answer;
    }
    
    class Person{
        private ArrayList<Time> logs;
        public Person(int[] strs){
            logs = new ArrayList<>(len);
            for(int str : strs){
                logs.add(new Time(str));
            }
        }
        public boolean check(int standard, int addHour, int addMin){
            //System.out.println(toString()+", "+ String.valueOf(standard));
            Time stand = new Time(standard + addHour*100 +addMin);
            
            int day;
            
            for(int i = 0; i < 7; i++){
                day = (startday+i) % 7 + 1;

                if (day == 6 || day == 7)
                    continue;
                //System.out.println(day);
                if (logs.get(i).compare(stand) > 0){
                    return false;
                }
            }
            return true;
        }
        public String toString(){
            return Arrays.toString(logs.toArray());
        }
    }
}

class Time{
    private int hour;
    private int min;
    public Time(int input){
        hour = input / 100;
        min = input % 100;
        
        if(min >= 60){
            min -= 60;
            hour++;
        }
    }
    public int compare(Time other){ // -1 = this<other, 1 = this > other
        //System.out.println(this + ", "+ other);
        if(other.hour != hour)
            return hour - (other.hour);
        if(other.min != min)
            return min - (other.min);
        
        return 0; // if same
    }
    public String toString(){
        return String.valueOf(hour*100+min);
    }
}

// 0855 -> 9005 (0860) | 09:00 (이놈이 더 빠른 걸로 취급됨) 