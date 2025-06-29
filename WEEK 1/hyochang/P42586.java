package hyochang;

/**
 * 기능개발
 * 몇개가 들어갈지 몰라서 List로 변경
 * 완료일을 기준으로 이하갯수 카운트 후 넘겨줌
 */

import java.util.*;

class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        int n = progresses.length;
        List<Integer> answer = new ArrayList<Integer>();

        // 각 기능이 완성되는 날짜를 담을 배열
        int[] complete = new int[n];

        for (int i = 0; i < n; i++) {
            if((100-progresses[i])%speeds[i]!=0){// 나머지가 있으면 +1
                complete[i]=((100-progresses[i])/speeds[i])+1;
            }else{
                complete[i]=(100-progresses[i])/speeds[i];
            }
        }

        // 처음 업무가 끝나야 다음 것들도 등록할 수 있음
        int max = complete[0];
        int completeCnt = 1;

        for (int i = 1; i < n; i++) {
            if (complete[i] <= max) {
                //이미 완료되었거나, 같은 날 완료되는 경우
                completeCnt++;
            } else {
                //시간이 더 걸리는 경우(= 더 이상 배포 불가)
                answer.add(completeCnt);
                max = complete[i];
                completeCnt = 1;
            }
        }
        answer.add(completeCnt);
        return answer;
    }
}
