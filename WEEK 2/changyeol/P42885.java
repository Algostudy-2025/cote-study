/*
최대 2명씩 태울 수 있다.
정렬한 후에 가장 가벼운 사람과 가장 무거운 사람을 가능하면 같이 태울 수 있도록 한다.
무거운 사람은 우선 태우고, 가벼운 사람은 두 사람의 무게 합이 limit보다 낮으면 같이 태울 수 있다.

채점 결과
정확성: 81.5
효율성: 18.5
합계: 100.0 / 100.0
*/

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        
        //정렬
        Arrays.sort(people);
        
        int left = 0;
        int right = people.length - 1;
        int ans = 0;
        
        while(left<=right){
            //가벼운 사람은 같이 태울 수 있으면 태움
            if(people[left] + people[right] <= limit){
                left++;
            }
            right--;//무거운 사람은 무조건 태움
            ans++;
        }
        
        return ans;
    }
}