import java.util.*;


//요즘 이분탐색에서는 아래처럼 오버플로우 방지하는게 정석이라는게 설명해줄 분...?
//long mid = lo + ((hi - lo) >>> 1);
//또는 long mid = (lo + hi) / 2;
class Solution {
    public long solution(int n, int[] times) {
        // Arrays.sort(times); // upper bound를 더 타이트하게 하려고 정렬 (선택)
        long low = 1;
        long high = (long) times[0]*n; //심사관 한명이 n명의 입국심사를 모두 처리하는 경우가 가장 오랜 시간 (실제로는 훨씬 적음)
        long answer = binarySearch(low,high,times,n);
        
        return answer;
    }
    
    static long binarySearch(long start, long end, int[] times, int n){
        long ans = 0;
        while(start<=end){ //start==end인 경우까지도 검사해야함!!
            long cnt = 0;
            long mid = ((long) start + end)/2; //총 시간 임의로 지정
            for(int e: times){ //각 심사대마다 mid 시간 동안 심사가능한 명수 더해
                cnt += mid/e;
            }
            if(cnt>=n){ //cnt가 n보다 크거나 같으면 더 적은 총 시간이 있을수도 있다.
                ans = mid; //하지만 일단 답 갱신
                end = mid-1;
            }else { //아니라면 총 시간이 더 길어야 한다.
                start = mid+1;
            }
        }
        return ans;
    }
}