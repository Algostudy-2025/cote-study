/*
participant, completion 두 배열을 정렬하고 앞에서부터 비교.

participant가 completion보다 길이가 1 길다.
- completion의 길이만큼 반복.
1) 서로 일치하면 패스, 일치하지 않으면 해당 인덱스에 위치한 이름이 완주하지 못한 선수의 이름이므로 리턴.
2) 다 비교했는데 모두 같다면 participant의 마지막 인덱스에 위치한 이름이 완주하지 못한 선수의 이름이므로 리턴



채점 결과
정확성: 58.3
효율성: 41.7
합계: 100.0 / 100.0
*/



import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        //두 배열 정렬
        Arrays.sort(participant);
        Arrays.sort(completion);
        
        //앞에서부터 비교
        for(int i = 0; i < completion.length; i++){
            //서로 다르면 participant[i] 리턴
            if(!participant[i].equals(completion[i])){
                return participant[i];
            }
        }
        
        //다 같다면 participant의 마지막 리턴
        return participant[participant.length - 1];
    }
}