import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0; //증설 횟수
        
        int currServer = 1; // 현재 서버의 수
        Queue<Integer> server = new LinkedList<>();
        
        for(int time = 0; time < 24; time++) { 
            
            // 1. 꺼져야하는 서버 확인
            if(!server.isEmpty()) {
                while(true) {
                    Integer t = server.peek();
                    if(t == null) { //다음 서버가 없으면 끝
                        break;
                    }
 
                    if(t == time) {
                        server.poll();
                        currServer--;
                    } else { // 종료할 서버가 아니면 while문 끝
                        break;
                    }
                }
            }
            
            //2. 현재 시간대 이용자와 비교
            int timePlayer = players[time];
            int availability = m * currServer;
            int cal = timePlayer - availability;
            
            //3. 늘려야 하는 경우
            while(cal >= 0) {
                cal -= m;
                server.offer(time+k);
                currServer++;
                answer++;
            }
        }
        return answer;
    }
}