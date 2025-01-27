class Solution {
    private int height;
    private int width;
    private int[][] triangle;
    
    public int solution(int[][] triangle) {
        height = triangle.length;
        width = triangle[height-1].length;
        
        this.triangle = new int[height][];
        for(int h = 0; h < height; h++){
            this.triangle[h] = new int[triangle[h].length];
            for(int w = 0; w < triangle[h].length; w++){
                this.triangle[h][w] = triangle[h][w];
            }
        }
        
        updateTriangle();
        // System.out.println("triangle");
        // for(int h = 0; h < height-1; h++){
        //     for(int w = 0; w<=h; w++){
        //         System.out.print(this.triangle[h][w]+ " ");
        //     }
        //     System.out.println("");
        // }
        
        int[] path = getPath(triangle);
        // System.out.println("path");
        // for(int x = 0; x< height; x++){
        //     System.out.print(path[x]);
        // }
        // System.out.println(path);
        
        return this.triangle[0][0];
    }
    
    protected void updateTriangle(){
        for(int h = height-2; h >= 0; h--){
            for(int w = 0; w<=h; w++){
                triangle[h][w] = getWeight(h, w);
            }
        }
    }
    
    protected int getWeight(int h, int w){
        if(h == height-1)
            return triangle[h][w];
        
        return triangle[h][w] + ( triangle[h+1][w] > triangle[h+1][w+1] ? triangle[h+1][w] : triangle[h+1][w+1] );
    }
    
    protected int[] getPath(int[][] origin){
        int[] path = new int[height+1];
        path[0] = origin[0][0];
        
        int w = 0;
        for(int h = 0; h < height-1; h++){
            w = triangle[h+1][w] > triangle[h+1][w+1] ? w : w+1;
            path[h+1] = origin[h+1][w];
        }
        
        return path;
    }
}