import java.util.*;

/* P42576 완주하지 못한 선수
 * https://school.programmers.co.kr/learn/courses/30/lessons/42576
 */
class Solution {
    public String solution(String[] participant, String[] completion) {
        //participant -> 참가선수 , completion -> 완주선수
    	
    	//HashMap을 사용해 이름별 참가자 수 카운트
        HashMap<String, Integer> count = new HashMap<>(); //
        
        //참가자 세기 
        for(String name : participant) {
            if(count.containsKey(name)) {// 이미 있는 이름이면 원래값 + 1
                count.replace(name, count.get(name) + 1);
            } else { //아니면 넣음
                count.put(name, 1);
            }
        }
        
        //완주자 배열에서 이름을 가져와서 참가자수에서 -1 해줌
        for(String name : completion) {
            count.replace(name, count.get(name) - 1);
        }
        
        // 아직 value가 1이면 완주하지 못한것
        for(String name : participant) {
            if(count.get(name) == 1) {
                return name;
            }
        }
        
        return null;
        
        
        //단순 완탐은 시간초과
//         boolean[] used = new boolean[participant.length];
        
//         for(String name : completion) {
//             for(int i = 0; i < participant.length; i++) {
//                 if(name.equals(participant[i]) && !used[i]) {
//                     used[i] = true;
//                     break;
//                 }
//             }
//         }
        
//         for(int i = 0; i < participant.length; i++) {
//             if(!used[i]) {
//                 return participant[i];
//             }
//         }
        
//         return null;
    }
}