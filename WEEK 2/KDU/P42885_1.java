/*  
    - 처음 작성한 코드
    사람들을 정렬한 후에 왼쪽부터 두명씩 더한 값을 변수에 저장하고 limit를 넘는지 체크해줌
    두명을 더한 값이 limit를 넘지 않으면 2명을 땡겨주고(제외) 넘으면 1명(처음인원)만 땡겨줌
    i가 n-1일 경우 마지막 사람이므로 무조건 그사람만 혼자 타야되서 count 증가시켜줌

    채점 결과
    정확성: 40.7
    효율성: 7.4
    합계: 48.1 / 100.0
 */

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int count = 0;
        int i = 0;              // 앞쪽(가벼운 사람)
        int n = people.length;

        while (i < n) {
            // 마지막 사람일 경우
            if (i == n - 1) {
                count++; // 혼자 타야 함
                break;
            }

            // 두 명이 limit 이하면 같이 태움
            if (people[i] + people[i + 1] <= limit) {
                i += 2; // 둘 다 처리
            } else {
                i += 1; // 혼자 타야 함
            }

            count++;
        }

        return count;
    }
}
