class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int w = n;
        int maxD = 0, maxP = 0;
        int nowCap;
        int d, p;

        for(int i = 0; i < n; i++){
            maxD += deliveries[i];
            maxP += pickups[i];
        }
        //System.out.println("left: "+ maxD +", "+maxP);
        // 전채 수거 개수, 배달 개수 값 저장

        // 모든 수거 배달이 완료되면 종료 while문
        while(maxD > 0 || maxP > 0){
            maxD -= cap;
            maxP -= cap;
            //System.out.println("left: "+ maxD +", "+maxP);
            // 현재 마지막 집 번호 answer += ;
            answer += w*2;
            // 최대 배달 가능 개수  최신화
            nowCap = cap;
            for(d = w; d>=1; d--){ // 마지막 집부터 처음 집까지
                if(nowCap < deliveries[d-1]){
                    deliveries[d-1] -= nowCap;
                    break;
                }else{
                    nowCap -= deliveries[d-1];
                    deliveries[d-1] = 0;
                }
            }

            // 최대 수거 가능 개수 최신화
            nowCap = cap;
            for(p = w; p>=1; p--){ // 마지막 집부터 처음 집까지
                if(nowCap < pickups[p-1]){
                    pickups[p-1] -= nowCap;
                    break;
                }else{
                    nowCap -= pickups[p-1];
                    pickups[p-1] = 0;
                }
            }

            w = d > p ? d: p;

            // for(int i = 0; i < n; i++){
            //     System.out.print(String.format("(%d, %d) ", deliveries[i], pickups[i]));   
            // }
            // System.out.println("");
        }
        return answer;
    }
}