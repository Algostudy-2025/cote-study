/*  
    - 코드 설명
    몸무게 오름차순으로 정렬
    가장 가벼운 사람부터 (L)
    가장 무거운 사람부터 (R)
    둘이 함께 탈 수 있으면 → 같이 태우고 L++, R--
    못 타면 → 무거운 사람 혼자 태우고 R--
    위 과정을 L<=R 때까지 반복

    채점 결과
    정확성: 81.5
    효율성: 18.5
    합계: 100.0 / 100.0
 */

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int L = 0;
        int R = people.length-1;
        int count = 0;
        
        while(L<=R){
            if(people[L] + people[R] <= limit){
                L++;
            }
            R--;
            count++;
        }
        
        return count;
    }
}

