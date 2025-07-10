import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        char[] chars1 = toUpperCase(str1);
        char[] chars2 = toUpperCase(str2);
        
        List<Character[]> set1 = toSet(chars1);
        List<Character[]> set2 = toSet(chars2);
        
        Collections.sort(set1, (a,b)->compare(a,b));
        Collections.sort(set2, (a,b)->compare(a,b));
        
        System.out.print("set1 :: ");
        set1.stream().forEach(sc->System.out.print(sc[0] + "" + sc[1] + ", "));
        System.out.print("\nset2 :: ");
        set2.stream().forEach(sc->System.out.print(sc[0] + "" + sc[1] + ", "));
        
        int countAllElement = set1.size() + set2.size();
        int countSameElement = countSameElement(set1, set2);
        
        System.out.println("\nall : " + countAllElement + ", same :: " + countSameElement);
        
        if(countAllElement == 0)
            return 65536;
        
        return (int)(countSameElement / (double) (countAllElement-countSameElement) * 65536);
    }
    
    public char[] toUpperCase(String str) {
        char[] chars = new char[str.length()];
        for(int i = 0; i < str.length(); i++){
            chars[i] = str.charAt(i);
        }
        
        for(int i = 0; i < chars.length; i++){
            if('a' <= chars[i] && chars[i] <= 'z')
                chars[i]+= 'A' - 'a';
        }
        return chars;
    }
    
    public List<Character[]> toSet(char[] str) {
        List<Character[]> chars = new ArrayList<>();
        
        for(int i = 0; i < str.length-1; i++){
            // i, i+1 둘다 'A' ~'Z'라면 chars에 추가함
            if('A' <= str[i] && str[i] <='Z' && 'A' <= str[i+1] && str[i+1] <= 'Z')
                chars.add(new Character[] {str[i], str[i+1]});
        }
        
        // 중복 원소 제거
        // for(int i = 1; i < chars.size(); i++){
        //     for(int j = 0; j < i; j++){
        //         if(compare(chars.get(i), chars.get(j))){
        //            chars.remove(i);
        //             i--;
        //             break;
        //         }
        //     }
        // }
        return chars;
    }
    
    public int countSameElement(List<Character[]> set1, List<Character[]> set2) {
        int count = 0;
        int iter1 = 0;
        int iter2 = 0;
        
        while(iter1 < set1.size() && iter2 < set2.size()) {
            int compare = compare(set1.get(iter1), set2.get(iter2));
            if(compare == 0){
                count++;
                iter1++;
                iter2++;
            }
            else if(compare > 0)
                iter2++;
            else
                iter1++;
        }
        
        return count;
    }
    public int compare(Character[] char1, Character[] char2) {
        if(char1[0] == char2[0])
            return char1[1] - char2[1];
        else 
            return char1[0] - char2[0];
        
    }
}

// 1. 대문자 변경 n 2000
// 2. 원소 추출 후 집합 으로 변경 2000
// 3. 같은 원소 찾기 : 1000 * 1000 = 1000000