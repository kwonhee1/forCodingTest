package org.example;

import java.util.*;

class Solution {
    int k; // 4
    int n; // 6
    int answer = (int)2e9;
    public int solution(int k, int n, int[][] reqs) {
        this.k = k; this.n = n;

        solution(reqs, k, n);

        return answer;
    }

    private void solution(int[][] reqs, int k, int n) {
        int[] arr = new int[k];
        for(int y = 0; y >= 0; y++){
            if(y == k-1){
                // 마지막 값을 구한다
                arr[k-1] = n - getSum(arr);
                if(arr[k-1] >= 1) solution(reqs, arr);

                y -= 2; continue;
            }
            arr[y]++;
            if(getSum(arr) >= n){
                for(int i = y; i < k; i++) arr[i] = 0;
                y-=2;
            }
        }
    }

    private static int getSum(int[] arr) {
        int sum = 0;
        for(int i = 0; i < arr.length-1; i++){sum += arr[i];}
        return sum;
    }

    private void solution(int[][] reqs, int[] consultants) {
        int totalSum = getTotalWaittedTime(reqs, consultants);
        answer = answer < totalSum ? answer : totalSum;
    }

    private int getTotalWaittedTime(int[][] reqs, int[] consultants) {
        //System.out.println("consultants : " + Arrays.toString(consultants));
        int totalSum = 0;
        List<List<Client>> schedules = new ArrayList<List<Client>>(k);
        for(int i = 0; i < k; i++){
            schedules.add(new ArrayList<Client>());
        }

        for(int i = 0; i < reqs.length; i++) {
            schedules.get(reqs[i][2] - 1).add(new Client(reqs[i][0], reqs[i][1]));
        }

        for(int i = 0; i < k; i++){
            totalSum += new ConsultantPool(schedules.get(i), consultants[i]).getWaittedTime();
            // System.out.println(i + "번 째 유형의 상담 :: " + Arrays.toString(schedules.get(i).toArray()) +" consultant count ="+consultants[i]+" sum=" + totalSum);
        }
        return totalSum;
    }
}

class ConsultantPool {
    private long sum = 0;
    List<Client> clients;
    private static Consultant start = new Consultant((int)2e9, null);
    public ConsultantPool(List<Client> clients, int consultantCount) {
        this.clients = clients;
        Consultant consultant = start;
        for(int i = 0; i < consultantCount; i++){
            consultant.next = new Consultant(0, null);
            consultant = consultant.next;
        }
    }
    public long getWaittedTime() {
        for(Client client : clients){
            // System.out.println(Arrays.toString( getConsultantEndTime()) );

            Consultant consultant = start.next;
            start.next = consultant.next;

            if(consultant.end > client.start){
                sum += consultant.end - client.start;
                appendToList(consultant, client.duration + consultant.end);
            }else{
                appendToList(consultant, client.duration + client.start);
            }
        }
        return sum;
    }
    private void appendToList(Consultant target, int endTime) {
        Consultant consultant = start;
        while(consultant.next != null && consultant.next.end < endTime) { consultant = consultant.next; }

        target.end = endTime;
        target.next = consultant.next;
        consultant.next = target;
    }
    private int[] getConsultantEndTime() {
        ArrayList<Integer> arr = new ArrayList<>();
        Consultant consultant = start.next;
        while(consultant != null) { arr.add(consultant.end); consultant = consultant.next; }
        return arr.stream().mapToInt(Integer::intValue).toArray();
    }
}

class Consultant {
    public int end;
    public Consultant next;
    public Consultant(int end, Consultant next) { this.end = end; this.next = next; }
}

class Client {
    public int start;
    public int duration;
    public Client(int start, int duration) { this.start = start; this.duration = duration; }
    public String toString() { return "Client (" + start + ", " + duration + ")"; }
}

// 15 명을 5 개로 분류함
// 15명을 순서 상관없이 1열로 세움
// 15사이에 4개의 막대를 설치함 (순서 상관없음, 막대로 나누어진 사람들 모임을 순서대로 5개 분류로 고려함)
// 막대를 둘수 있는 경우의 수(15를 5그룹으로 나눌수 있는 경우의 수) : 15*14*13*12*11/4*3*2*1
// (15를 5그룹으로 나눌수 있는 경우의 수) * 300 = 약 1200000 백만번 이내 (충분함)