import java.util.*;

//dp인걸 알고 풀어서... 금방 풀었다 하하하ㅠㅠ
//시간복잡도 : O(n^2) = 500^2 
//최대 가능한 정수값 : 대략 500*10000 = 5,000,000 -> int 충분!
class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];
        for(int i=1;i<n;i++){
            dp[i][0] = dp[i-1][0] + triangle[i][0]; //맨 왼쪽 따로계산
            dp[i][i] = dp[i-1][i-1] + triangle[i][i]; //맨 오른쪽 따로계산
            for(int j=1;j<i;j++){ //그 중간 부분은 왼위+해당값 or 오위+해당값 중에 최댓값으로 dp배열에 저장
                dp[i][j] = Math.max(dp[i-1][j-1]+triangle[i][j], dp[i-1][j]+triangle[i][j]);
            }
        }
        
        int answer = 0;
        for(int i=0;i<n;i++){
            // System.out.println(Arrays.toString(dp[i]));
            answer = Math.max(dp[n-1][i], answer); //dp배열의 마지막 줄에서 최댓값이 answer!
        }
        
        return answer;
    }
}