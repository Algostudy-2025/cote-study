import java.io.*;
import java.util.*;

/**
- 자신과 같은 인덱스 or -1 인덱스
- 왼쪽 끝 => j / 중간 => j, j-1 / 오른쪽 끝 => j-1

*/

class Solution {
    public int solution(int[][] triangle) {
        int N = triangle.length;
        int answer = 0;

        for(int i = 1; i<N; i++){ // 두번째 줄부터 시작
            for(int j = 0; j<=i; j++){ // 0~i 까지
                if(j==0){
                    triangle[i][j] += triangle[i-1][j];
                }else if(j==i){
                    triangle[i][j] += triangle[i-1][j-1];
                }else{
                    triangle[i][j] += Math.max(triangle[i-1][j], triangle[i-1][j-1]);
                }
                
                if(i==N-1){
                    answer = Math.max(answer, triangle[i][j]);
                }
            }
        }
        
        return answer;
    }
}