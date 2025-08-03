import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {

        int answer = 0;
        int N = players.length;
        int[] server = new int[N];

        // 증설 없이 수용 가능한 인원으로 서버 채우기
        Arrays.fill(server, m - 1); 

        for (int i = 0; i < N; i++) {

            // 서버가 수용 가능하면 넘기기
            if (players[i] <= server[i]) continue;

            // 증설해야 하는 서버 수 확인
            int lack = players[i] - server[i];
            int addServer = lack % m == 0 ? lack / m : lack / m + 1;
            
            // 서버 증설
            answer += addServer;
            for (int time = i; time < i + k; time++) {
                if (time >= N) break;
                server[time] += m * addServer;
            }
        }

        return answer;
    }
}