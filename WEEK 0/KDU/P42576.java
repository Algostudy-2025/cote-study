/*
    complation 배열은 항상 participant 배열보다 항상 작음.
    그렇다는 말은 정렬했을 때도 completion 배열이 participant 배열보다 길이가 하나 작다는 말임.
    
    먼저 정렬을 한 후 completion 배열을 돌면서 participant 배열과 동일한지 비교를 함.
    -> 다르다면? 완주를 못한 선수이므로 바로 return!

    만약 completion 배열을 끝까지 돌았는데 전부 같아서 for문이 끝났다
    -> participant에 남아있는 마지막 선수가 완주하지 못한 선수이므로 이 친구를 return 함!!

    시간복잡도 : O(nlogn)
    -> 정렬 : MergeSort+:InsertSort
    -> 비교 : 1차 반복문

    채점 결과
    정확성: 58.3
    효율성: 41.7
    합계: 100.0 / 100.0
*/



import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        // 1. 정렬
        Arrays.sort(participant);
        Arrays.sort(completion);

        // 2. 앞에서부터 비교
        for (int i = 0; i < completion.length; i++) {
            if (!participant[i].equals(completion[i])) {
                return participant[i];
            }
        }

        // 3. 끝까지 같으면 마지막 참가자가 완주 못함
        return participant[participant.length - 1];
    }
}