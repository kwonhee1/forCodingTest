import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        HashMap<String, Friend> map = new HashMap<>();
        for(String firend : friends){
            map.put(firend, new Friend(firend));
        }
        
        Friend to, from;
        String[] buff;
        for(String gift : gifts){
            buff = gift.split(" ");
            from = map.get(buff[0]);
            to = map.get(buff[1]);
            
            to.down(from);
            from.up(to);
        }
        
        map.values().forEach(f -> {System.out.println(f.toString());});
        
        int re = 0;
        for(Friend f : map.values()){
            int value = f.get(map.values());
            System.out.println(String.format("(%s,%d) ", f.name,value));
            if(re < value)
                re = value;
        }
        return re;
    }
}

class Friend{
    String name;
    private int gift = 0;
    private HashMap<Friend, Integer> map = new HashMap<>();
    public Friend(String name){this.name = name;}
    private void create(Friend f){
        if(map.get(f) == null)
            map.put(f, 0);
    }
    protected void up(Friend f){
        create(f);
        map.put(f, map.get(f)+1);
        gift++;
    }
    protected void down(Friend f){
        create(f);
        map.put(f, map.get(f)-1);
        gift--;
    }
    protected int get(Collection<Friend> friends){
        int re = 0;
        for(Friend friend : friends){
            re += compare(friend) ? 1 : 0;
        }
        return re;
    }
    
    private boolean compare(Friend other){
        if(other == this)
            return false;
            
        Integer value = map.get(other);
        if(value == null || value == 0){
            // 선물점수가 더 높은 사람
            return this.gift > other.gift;
        }
        else
            return value > 0;
    }
    
    public String toString(){
        StringBuilder builder = new StringBuilder(name + ":" + gift);
        
        map.keySet().forEach(f->{
            builder.append(String.format("(%s,%d) ", f.name, map.get(f)));
        });
        
        return builder.toString();
    }
}