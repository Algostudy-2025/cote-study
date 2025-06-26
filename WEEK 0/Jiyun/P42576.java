/*

    문제 조건: 두 선수 명단을 비교하여 완주하지 못한 사람 return
    풀이: 
        > 동명이인이 있을 수 있기 때문에, HashMap을 사용하여 각 이름의 참가자가 몇 명 나왔는지 기록
        > 완주자의 이름과 동일한 참가자에 -1
        > 최종적으로 숫자가 0이 아닌 참가자의 이름을 return

    채점 결과
    정확성: 58.3
    효율성: 41.7
    합계: 100.0 / 100.0

*/

import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        
        for (String player: participant) {
            map.put(player, map.getOrDefault(player, 0) + 1);
        }
        
        for (String player: completion) {
            map.put(player, map.get(player) - 1);
        }
        
        for (String player: map.keySet()) {
            if (map.get(player) != 0) {
                return player;
            }
        }
        
        String answer = "";
        return answer;
    }
}