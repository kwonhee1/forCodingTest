import java.util.*;
import java.util.stream.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        ArrayList<Data> dataList = new ArrayList<Data>();
        for(int[] input : data){
            dataList.add(new Data(input));
        }
        
        List<Data> arr = dataList.stream().filter(d -> !d.validate(ext, val_ext)).collect(Collectors.toList());
        arr.forEach(d -> dataList.remove(d));
        
        Comparator<Data> compare = new Comparator<Data>(){
            @Override
            public int compare(Data a, Data b){
                return a.get(sort_by) > b.get(sort_by) ? 1 : -1;
            }
        };
        
        Collections.sort(dataList, compare);
        
        int[][] answer = new int[dataList.size()][];
        for(int i = 0; i < dataList.size(); i++){
            answer[i] = dataList.get(i).toInt();
        }
        
        return answer;
    }
    
    class Data{
        private HashMap<String, Integer> dataMap = new HashMap<String, Integer>();
        
        protected Data(int[] input){
            dataMap.put("code", input[0]);
            dataMap.put("date", input[1]);
            dataMap.put("maximum", input[2]);
            dataMap.put("remain", input[3]);
        }
        
        protected boolean validate(String standard, int value){
            return dataMap.get(standard) < value;
        }
        
        protected int get(String col){
            return dataMap.get(col);
        }
        
        protected int[] toInt(){
            return new int[] {dataMap.get("code"),dataMap.get("date"),dataMap.get("maximum"),dataMap.get("remain")};
        }
        
        public String toString(){
            return String.format("%d, %d, %d, %d",
            dataMap.get("code"),
            dataMap.get("date"),
            dataMap.get("maximum"),
            dataMap.get("remain"));
        }
    }
}