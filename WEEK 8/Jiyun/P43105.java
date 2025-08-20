import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int depth = triangle.length;

        // 삼각형 배열 깊은 복사
        int[][] sum = new int[depth][];
        sum[0] = Arrays.copyOf(triangle[0], 1);
        for (int i = 1; i < depth; i++)
            sum[i] = Arrays.copyOf(triangle[i], triangle[i].length);

        for (int i = 0; i < depth - 1; i++) {
            // 현재 위치로부터 오른쪽과 왼쪽을 각각 확인하면서 최댓값으로 갱신
            for (int j = 0; j < triangle[i].length; j++) {
                int left = sum[i][j] + triangle[i + 1][j];
                sum[i + 1][j] = sum[i + 1][j] < left ? left : sum[i + 1][j];
                int right = sum[i][j] + triangle[i + 1][j + 1];
                sum[i + 1][j + 1] = sum[i + 1][j + 1] < right ? right : sum[i + 1][j + 1];
            }
        }

        // 정답(최댓값) 찾기
        int answer = 0;
        for (int s : sum[depth - 1])
            answer = answer < s ? s : answer;
        return answer;
    }
}
