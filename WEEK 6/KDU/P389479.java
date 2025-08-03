import java.util.*;

/*
    반복문을 돌면서 게임 이용자(players[]) 수 체크

    방법 1
    허용가능한 게임 이용자의 수를 저장하는 배열이 필요?(permit[])
    -> players[]를 돌면서 permit[]도 채우기

    방법 2
    players 배열을 돌면서 m보다 커지면 time 배열의 동일한 인덱스에 i+k만큼의 값을 저장함
    server 배열은 players[i]/m을 통해서 값을 저장하되, 
    한번 저장되면 time[i]만큼 값이 지속되도록 셋팅함

    방법 3
    server 배열에는 서버가 감당할 수 있는 양만 저장해야 하며, 단순히 players[i] / m 으로 나눠서
    계산하지 않고 max(0, players[i] - 동작중인 서버 수 x m)으로 초과량을 계산해야 됨

    최종
    ※ times 배열 : i번째에 서버가 종료되는 수를 기록함
    1. for문을 돌면서 runningservers에 times 배열에서 기록된 i번째 값을 뺌(서버 종료된 수를 뺌)
    2. time[i+k]에는 추가되는 서버 수를 저장함
    -> 초과 인원 수 : players[i] - 현재 서버수 * m (음수가 될 수 있으므로 max로 막아줌)
    -> 추가되는 서버 수 : 초과 인원 수 / m의 값을 올림(인원이 한명이라도 많으면 서버가 만들어짐)
    3. 서버수 갱신, 증설 횟수 갱신
 */

class Solution {

    static int[] players;
    static int m, k;
    static int[] times;

    public int solution(int[] players, int m, int k) {
        
        // players배열보다 k만큼 크게 만들어야됨...
        times = new int[players.length + k];

        int answer = 0;

        int runningservers = 0;

        for (int i = 0; i < players.length; i++) {
            // times 배열 : i번째에 서버가 종료되는 수를 기록함
            runningservers -= times[i];

            int exceedplayers = Math.max(0, players[i] - runningservers * m);

            // 배열 범위 벗어나지 않게 조심..!!
            /* 
                if (i + k >= players.length)
                continue;
                times[i + k] += Math.ceil(exceedplayers / m);

                이래버리니까 밑에꺼 전부 생략해버림...
            */
            if (i + k < players.length)
               times[i + k] += Math.ceil(exceedplayers / m);

            runningservers += Math.ceil(exceedplayers / m);

            answer += Math.ceil(exceedplayers / m);
        }

        return answer;
    }
}