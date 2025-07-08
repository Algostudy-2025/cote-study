
/**
 * 최대 2명 
 * 투포인터 -> 정렬 후 돌면서 좌우 끝을 매칭 만약에 안된다 조정
 * 정렬 + 투포인터 
 * 정렬을 안하고 매칭하는 방법으로는 최적의 값(최소무게+최대무게)임을 보장할 수 없다.->반드시 정렬이 필요함
 *  */ 
import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;

        int left = 0;
        int right = people.length - 1;

        Arrays.sort(people);
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
                right--;
            } else {
                right--;
            }
            answer++;
        }

        return answer;
    }
}