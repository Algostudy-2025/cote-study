
/*
n: 입국 심사를 기다리는 사람 수
times: 각 심사관이 한명을 심사하는데 걸리는 시간이 담긴 배열
모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 리턴

왜 이분탐색? => 시간이 t일때, 몇 명을 심사할 수 있냐
t시간 동안 처리 가능한 인원 수 = t시간 동안 각 심사관이 처리한 사람의 수를 더한 것
*/

import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long max = 0; //한 명 심사하는 시간이 가장 긴 심사관의 심사 시간
        for(int t : times){
            max = Math.max(max, t);
        }
        
        long left = 1; //최소 시간
        long right = max * n; // 최대 시간 : 가장 느린 심사관이 모든 사람을 심사하는 경우
        
        long ans = right; //정답 후보
        
        while(left <= right){
            long mid = (left + right) / 2;
            
            long cnt = 0; //mid 시간 동안 심서 가능한 사람의 수
            
            //mid 시간 동안 n명 이상 심사 가능하면 바로 멈춤(불필요한 반복 제거)
            for(int t : times){
                cnt += mid / t;
                if(cnt >= n) break;
            }
            
            //mid 시간 동안 n명 이상 심사 가능하면
            if(cnt >= n){
                ans = mid; //mid가 정답 후보가 됨
                right = mid - 1; //더 적은 시간 동안 n명 심사 가능한지 왼쪽 탐색
            }
            //n명 이상 심사 불가능하면
            else{
                left = mid + 1; // 시간이 더 필요하므로 오른쪽 탐색
            }
        }
        
        return ans;
    }
}