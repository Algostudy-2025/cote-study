import java.util.*;

/* P42885 구명보트
 * https://school.programmers.co.kr/learn/courses/30/lessons/42885?language=java
 */

//투포인터로 풀었고, 작은쪽을 먼저 태우다가 삽질한번하고 변경

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);
        
        int left = 0;
        int right = people.length - 1;
        
        while(left <= right) {
            
            if(people[left] + people[right] <= limit) { //작은쪽은 안넘기면 태움
                left++;
            }
            
            right--; //일단 무거운쪽은 탐
            answer++;
        }
        
        return answer;
    }
}
