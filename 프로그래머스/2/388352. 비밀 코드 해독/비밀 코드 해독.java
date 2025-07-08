import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        List<Code> map = new ArrayList<>();
        
        for(char first = (char)n; first > 0; first--){
            for(char second = (char)(first-1); second > 0; second--){
                for(char third = (char)(second-1); third > 0; third--){
                    for(char four = (char)(third-1); four > 0; four--){
                        for(char five = (char)(four-1); five > 0; five--){
                            map.add(new Code(new char[] {five, four, third, second, first}));
                        }
                    }
                }
            }
        }
        
        for(int i = 0; i < q.length; i++){
            int[] question = q[i];
            int answer = ans[i];
            // System.out.print("input question : ");
            // for(int a : question) { System.out.print(a); }
            // System.out.println();
            map = map.stream().filter(c->c.check(question, answer)).collect(Collectors.toList());
        }
        
        return map.size();
    }
    
    class Code{
        char[] code;
        public Code(char[] code){
            this.code = code;
        }
        public boolean check(int[] input, int ans) {
            int count = 0;
            
            int c = 0;
            int i = 0;
            
            while(c < 5 && i < 5) {
                if((int)code[c] == input[i]){
                    count++;
                    c++;
                }
                else if((int)code[c] > input[i]){
                    i++;
                }
                else{
                    c++;
                }
            }
            
            // if(count != ans){
            //     System.out.print("erase :: input:: ");
            //     for(int a : input) { System.out.print(a + " "); }
            //     System.out.print(" code :: ");
            //     for(char a : code) { System.out.print((int)a + " "); }
            //     System.out.println(count);
            // }
            return count == ans;
        }
    }
}
