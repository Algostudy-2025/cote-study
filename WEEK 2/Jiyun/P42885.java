/*

    - 섬에 있는 사람들을 몸무게 순으로 정렬
    - 몸무게가 가장 가벼운 사람과 가장 무거운 사람을 짝지어 보트에 태울 수 있는지 확인
    - 짝지어 탈 수 있다면 둘 다 제거, 그렇지 않다면 가장 무거운 사람만 제거
    - 이 과정을 반복


    채점 결과
    정확성: 81.5
    효율성: 18.5
    합계: 100.0 / 100.0

*/

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        Deque<Integer> island = new ArrayDeque<>();
        for (int person : people)
            island.add(person);
        
        int answer = 0;
        while (!island.isEmpty()) {
            if (island.size() > 1 && limit >= island.peekFirst() + island.peekLast()) {
                island.pollLast();
                island.pollFirst();
            } else {
                island.pollLast();
            }
            answer++;
        }

        return answer;
    }
}