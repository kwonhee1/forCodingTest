class Solution {
    public int[] solution(String[] wallpaper) {
        int widthMin=0, widthMax=0, heightMin=0, heightMax=0;
        
        // 비어있지 않은 맨 첫줄을 찾음 
        int y = 0;
        while(!wallpaper[y].contains("#"))
            y++;
        heightMin = heightMax = y;
        
        // 첫번째 줄 강제 입력
        int x = 0;
        for(x = 0; x< wallpaper[y].length(); x++){
            if(wallpaper[y].charAt(x) == '#'){
                widthMin = widthMax = x;
                break;
            }
        }
        
        for(x++; x< wallpaper[y].length(); x++){
            if(wallpaper[y].charAt(x) == '#'){
                widthMax = x;
            }
        }
        
        System.out.println(String.format("%d, %d, %d, %d", widthMin, heightMin, widthMax, heightMax));
        
        // 한줄씩 입력 받으면서 위 4개 변수를 넓혀 간다
        for(y++; y<wallpaper.length; y++){
            String str = wallpaper[y];
            
            if(!str.contains("#")){
                continue;
            }
            heightMax = y;
            
            for(int x_ = 0; x_<widthMin; x_++){
                if(str.charAt(x_) == '#'){
                    widthMin = x_;
                    break;
                }
            }
            for(int x_ =str.length()-1; x_>widthMax; x_--){
                if(str.charAt(x_) == '#'){
                    widthMax = x_;
                    break;
                }
            }
            System.out.println(String.format("%d, %d, %d, %d", widthMin, heightMin, widthMax, heightMax));
        }
        
        return new int[] {heightMin, widthMin, heightMax+1, widthMax+1};
    }
}