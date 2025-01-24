import java.util.*;
class Solution {
    public String[] solution(String[] strings, int n) {
        ArrayList<String> arr = new ArrayList<String>();

        for(String str : strings){
            arr.add(str);
        }

        Collections.sort(arr, new Comparator<String>(){
            @Override
            public int compare(String str1, String str2){
                char c1 = str1.charAt(n);
                char c2 = str2.charAt(n);

                if(c1 != c2){
                    return c1 > c2 ? 1 : -1;
                }
                int len1 = str1.length();
                int len2 = str2.length();
                int max = len1 > len2 ? len2 : len1;
                for(int i = 0; i < max; i++){
                    c1 = str1.charAt(i);
                    c2 = str2.charAt(i);

                    if(c1 != c2)
                        return c1 > c2 ? 1 : -1;
                }
                return 0;
            }
        });

        String[] re = new String[arr.size()];
        for(int i = 0; i< arr.size(); i++){
            re[i] = arr.get(i);
        }

        return re;
    }

}