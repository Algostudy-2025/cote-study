import java.util.*;

/*
참여한 선수 - participant
완주한 선수 - completion
participant에서 completion 뺀 선수만 return

한명씩 비교하게 된다면 시간초과, 정렬하여 비교
*/

class Solution {
    
    public String solution(String[] participant, String[] completion) {
        
        //정렬
        Arrays.sort(participant);
        Arrays.sort(completion);
        
        //정렬 후 비교
        for(int i = 0; i < completion.length; i++){
            if(!participant[i].equals(completion[i]))
            //같지 않을 때 참여한 선수 출력
                return participant[i];
        }
        
        //끝까지 비교가 끝난 후, 참여한 선수 중 마지막 출력
        return participant[completion.length];
        
        
    }
}
