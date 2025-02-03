class Solution {
    public long solution(int[] sequence) {
        long sum1 = getMax(getPulseSequnce(sequence, 1));
        long sum2 = getMax(getPulseSequnce(sequence, -1));
        
        return sum1 > sum2 ? sum1 : sum2;
    }
    private long getMax(int[] sequence){
        long sum = 0;
        long maxSum = 0;
        
        //sum = maxSum = sequence[sequence.length-1];
        
        for(int i = sequence.length -1; i >= 0; i--){
            sum += sequence[i];
            
            // update maxSum
            if(maxSum < sum)
                maxSum = sum;
            
            // check 
            if(sum < 0)
                sum = 0;
            
            //System.out.println("at : "+ i + " se : " + sequence[i] + " maxSum: " +maxSum + " sum = "+ sum);
        }
        
        return maxSum;
    }
    
    private int[] getPulseSequnce(int[] input, int pulse){
        int[] output = new int [input.length];
        
        for(int i = 0; i < input.length; i++){
            output[i] = input[i] * pulse;
            pulse *= -1;
        }
        
        return output;
    }
}

// 2 -3 -6 -1 3 1 2 -4
// -2 3 6 1 -3 -1 -2 4