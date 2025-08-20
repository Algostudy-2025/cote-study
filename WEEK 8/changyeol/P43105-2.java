import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        
        int[] dp = new int[n];
        
        dp[0] = triangle[0][0];
        
        for(int i= 1; i < n; i++){
            //오른쪽 맨 끝
            dp[i] = dp[i-1] + triangle[i][i];
            
            //가운데
            for(int j = i - 1; j >= 1; j--){
                dp[j] = Math.max(dp[j-1], dp[j]) + triangle[i][j];
            }
            
            //왼쪽 맨 끝
            dp[0] = dp[0] + triangle[i][0];
        }
        
        
        int ans = 0;
        for(int v : dp) ans = Math.max(ans, v);
        return ans;
    }
}