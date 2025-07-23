class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int M = key.length;
        int N = lock.length;

        // 4방향 회전하면서 모든 위치에 key를 덮어보며 확인
        for (int i = 0; i < 4; i++) {
            for (int m = -M + 1; m < N; m++) {
                for (int n = -M + 1; n < N; n++) {
                    if (check(m, n, key, lock)) return true;
                }
            }
            key = rotate(key); // 90도 회전
        }

        return false;
    }

    // key를 lock 위에 덮어보고 자물쇠가 열리는지 확인
    private boolean check(int m, int n, int[][] key, int[][] lock) {
        int M = key.length;
        int N = lock.length;
        int[][] temp = new int[N][N];

        // lock을 복사해 temp 배열 생성 (원본 유지)
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                temp[i][j] = lock[i][j];

        // key를 (m, n) 위치에 덮기
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < M; x++) {
                int ny = y + m;
                int nx = x + n;

                // lock 영역 안에 들어오는 경우만 적용
                if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
                    temp[ny][nx] += key[y][x];
                }
            }
        }

        // lock이 모두 1로 채워졌는지 확인
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (temp[i][j] != 1) return false; // 0이 남거나, 2 이상이면 실패
            }
        }

        return true;
    }

    // key 배열을 시계 방향으로 90도 회전
    private int[][] rotate(int[][] key) {
        int M = key.length;
        int[][] rotated_key = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                rotated_key[i][j] = key[j][M - 1 - i];
            }
        }
        return rotated_key;
    }
}
