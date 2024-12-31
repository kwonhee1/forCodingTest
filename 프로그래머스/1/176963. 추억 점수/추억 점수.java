import java.util.ArrayList;
class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        for(int y = 0; y < photo.length; y++){
            int re = 0;
            for(int x = 0; x <photo[y].length; x++){
                for(int z = 0; z<name.length; z++){
                    if(name[z].equals(photo[y][x])){
                        re += yearning[z];
                        break;
                    }
                }
            }
            answer[y] = re;
        }
        
        return answer;
    }
}