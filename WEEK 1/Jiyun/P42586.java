/*

    작업을 모두 마칠 때까지 하나씩 진행하며 풀이
    작업 중인 기능은 idx로 표시
      예) idx = 1의 경우, progresses[0]은 이미 배포되었고, progresses[1]을 작업해야 하는 상황
    idx번째 작업이 완료되면 idx+1으로 다음으로 넘어감
    idx가 size(모든 작업의 수)와 동일해졌을 때 종료

    채점 결과
    정확성: 100.0
    합계: 100.0 / 100.0

*/

import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> ans = new ArrayList<>();

        int size = progresses.length;
        int idx = 0;

        // 모든 작업을 마칠 때까지 진행
        while (idx < size) {

            // 작업 진행사항 갱신
            for (int i = idx; i < size; i++)
                progresses[i] += speeds[i];

            // 배포할 수 있는 작업 확인
            int count = 0;
            while (idx < size && progresses[idx] >= 100) {
                idx++;
                count++;
            }

            // 배포 가능한 게 있다면 배포
            if (count > 0)
                ans.add(count);
        }

        // 리스트를 배열로 변환
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            answer[i] = ans.get(i);

        return answer;
    }
}