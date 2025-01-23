class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        Time.setOpen(op_start, op_end, video_len);
        Time t = new Time(pos);
        t.command("");
        
        for(String command : commands){
            t.command(command);
        }
        
        return t.toString();
    }
    
}

class Time{
        static Time start;
        static Time end;
        static Time openStart;
        static Time openEnd;
        
        private int second;
        private int min;
        Time(String t){
            String[] input = t.split(":");
            min = Integer.parseInt(input[0]);
            second = Integer.parseInt(input[1]);
        }
        protected static void setOpen(String openStart, String openEnd, String end){
            start = new Time("00:00");
            Time.openStart = new Time(openStart);
            Time.openEnd = new Time(openEnd);
            Time.end = new Time(end);
        }
        
        protected void command(String command){
            switch(command){
                case "next":
                    next();
                    break;
                case "prev":
                    prev();
                    break;
            }
            
            checkEndLine();
            opening();
            
            System.out.println("command :: " + this.toString());
        }
        
        protected void next(){
            second += 10;
            if(second >= 60){
                min += 1;
                second -= 60;
            }
        }
        protected void prev(){
            second -= 10;
            if(second < 0){
                min -= 1;
                second += 60;
            }
        }
        
        private int compare(Time target){ // return -1(this<target) 0(this==target) 1(target < this)
            int targetMin = target.getMin();
            int targetSecond = target.getSecond();
            
            if(min != targetMin)
                return min > targetMin ? 1 : -1;
            
            if(second == targetSecond)
                return 0;
            
            return second > targetSecond ? 1 : -1;
        }
        
        private void checkEndLine(){
            if(this.compare(start) == -1){
                min = 0; second = 0;
            }
            if(this.compare(end) == 1){
                min = end.getMin(); second = end.getSecond();
            }
        }
        
        private void opening(){
            // openStart <= this <= openEnd :: 1 0 , 0 -1
            if(this.compare(openStart) >= 0 && this.compare(openEnd) <= 0){
                min = openEnd.getMin();
                second = openEnd.getSecond();
            }
        }
        
        public String toString(){
            return String.format("%02d:%02d", min, second);
        }
        
        public int getMin(){
            return min;
        }
        public int getSecond(){
            return second;
        }
    }