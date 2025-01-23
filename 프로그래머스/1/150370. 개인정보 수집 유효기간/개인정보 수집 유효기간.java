import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> result = new ArrayList<>();
        Date now = toDate(today);
        HashMap<String, Integer> termsMap = new HashMap<>();
        
        for(String str : terms){
            String[] input = str.split(" ");
            termsMap.put(input[0], Integer.parseInt(input[1]));
        }
        
        for(int i = 0; i<privacies.length; i++){
            String[] in =privacies[i].split(" ");
            Date d = toDate(in[0]);
            String term = in[1];
            
            d = addMonth(d, termsMap.get(term));
            
            if(!now.before(d))
                result.add(i+1);
            
            System.out.println(String.format("%d.%d.%d : %b", d.getYear(), d.getMonth(), d.getDate(),now.before(d) ));
        }
    
    int[] re = new int[result.size()];
    for(int i = 0; i< result.size(); i++){
        re[i] = result.get(i);
    }
    
    return re;
    }
    Date addMonth(Date target, int addMonth){
    int month = target.getMonth() + addMonth +1;
    int year = target.getYear();
    
    if(month > 12){
        month -= 12;
        year++;
    }
    
    target.setYear(year);
    target.setMonth(month-1);
    
    return target;
}
    Date toDate(String str){
        String[] input = str.split("\\.");
        return new Date(Integer.parseInt(input[0]), Integer.parseInt(input[1])-1, Integer.parseInt(input[2]));
    }
}
