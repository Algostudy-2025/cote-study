import java.util.*;
/*
    1차
    triangle[0][0]
    triangle[1][0], triangle[1][1]
    triangle[2][0], triangle[2][1], triangle[2][2]...
    i를 돌면서 j는 인접한 애들로만 접근할 수 있도록 반복문 돌기
    dp배열 만들고 선택한 배열의 누적합 저장해서 Math.max로 비교하기
    
    2차
    dp배열을 2차원 배열로 만들어서 각 배열값에 위에서부터 내려온 값을 저장하도록 셋팅
    -> 현재 dp배열 값을 기준으로 왼쪽 위(dp[r-1][c-1]), 오른쪽 위(dp[r-1][c])를 체크해서
    큰 값을 저장하게 만듦
    단, 이렇게 만들면 왼쪽 끝(index = 0), 오른쪽 끝(triangle[].length)은 인덱스가 벗어나므로
    이를 예외처리해줌
    
*/
class Solution {
    public int solution(int[][] triangle) {
        int row = triangle.length;
        int[][] dp = new int[row][];
        dp[0] = new int[1];
        dp[0][0] = triangle[0][0];
        
        for(int r = 1; r < row; r++){
            dp[r] = new int[triangle[r].length];
            for(int c = 0; c < triangle[r].length; c++){
                // 예외처리(왼쪽 끝, 오른쪽 끝)
                if(c > 0 && c < triangle[r].length-1){
                    dp[r][c] = Math.max(dp[r-1][c], dp[r-1][c-1]) + triangle[r][c];
                }
                else if(c == 0){
                    dp[r][c] = dp[r-1][c] + triangle[r][c];
                }
                else{
                    dp[r][c] = dp[r-1][c-1] + triangle[r][c];
                }
            }
        }
        
        int answer = 0;
        for(int i = 0; i < triangle[row-1].length; i++){
            answer = Math.max(answer, dp[row-1][i]);
        }
        
        return answer;
    }
}