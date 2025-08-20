/*
P43105 (https://school.programmers.co.kr/learn/courses/30/lessons/43105)
그냥 DP 했습니다
풀고 이제 다른 사람 풀이 보고 생각해보니까
배열을 새로 안만들고 그냥 덮어 써도 상관없었다~
아래에서 위로
위에서 아래로 두개의 방법이 있다
 */

class Solution {
    public int solution(int[][] triangle) {

        int[][] dp = new int[triangle.length][]; //dp 배열 초기화1

        for(int i = 0; i < triangle.length; i++) { //dp 배열 초기화2
            dp[i] = new int[triangle[i].length];
        }

        dp[0][0] = triangle[0][0];

        for(int i = 1; i < triangle.length; i++) {
            for(int j = 0; j < dp[i].length; j++) { //dp[i][j] 번째를 채울거임

                int n1 = j - 1; //왼쪽 위
                int n2 = j; //오른쪽 위

                if(n1 >= 0) {
                    int sum = triangle[i][j] + dp[i-1][n1];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }

                if(n2 < triangle[i-1].length) {
                    int sum = triangle[i][j] + dp[i-1][n2];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }

        int max = dp[0][0];

        for(int i = 1; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }
}