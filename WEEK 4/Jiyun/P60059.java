/*

    중복되는 로직이 엄청 많고 이걸 분명 효율적으로 바꿀 수 있을텐데 생각나지 않음...

    - 열쇠와 자물쇠가 항상 같은 크기인 건 아니다! 이걸 왜 몰랐을까!
    - 비교를 0부터 하면 안된다! 우하단이 넘칠 수 있으면 좌상단도 넘칠 수 있다는 걸 왜 난 생각하지 못했을까!
    - 회전한 걸 어떻게 비교하지... > 회전한 버전의 배열을 새로 만들면... > 그러면 차라리 바로 인덱스 접근하는 게 낫지...
    - 처음엔 회전 고려 안 한 상태로 코드를 작성했고, 그 이후에 그걸 회전한 버전으로 각각 복붙해서 완성..했으나 이렇게 푸는 게 아닌 것 같음

*/

import java.util.*;

class Solution {
    
    class Pos {
        int row, col;

        Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        
        int N = key.length;
        int M = lock.length;

        // 열쇠 위치만 따로 저장
        Set<Pos> keys = new HashSet<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (key[r][c] == 1) {
                    keys.add(new Pos(r, c));
                }
            }
        }

        // 자물쇠 홈 개수 저장
        int hole = 0;
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < M; c++) {
                if (lock[r][c] == 0)
                    hole++;
            }
        }

        // 열쇠가 들어갈 수 있는 모든 위치를 한 칸씩 옮겨가면서 비교
        for (int r = -N + 1; r < M; r++) {
            for (int c = -N + 1; c < M; c++) {

                // 회전 0
                int clear = 0;
                for (Pos k : keys) {
                    int nr = k.row + r;
                    int nc = k.col + c;

                    if (nr < 0 || nc < 0 || nr >= M || nc >= M)
                        continue;

                    if (lock[nr][nc] == 0) {
                        clear++;
                    } else {
                        break;
                    }
                }
                if (clear == hole)
                    return true;


                // 회전 90
                clear = 0;
                for (Pos k : keys) {
                    int nr = k.col + r;
                    int nc = N - 1 - k.row + c;

                    if (nr < 0 || nc < 0 || nr >= M || nc >= M)
                        continue;

                    if (lock[nr][nc] == 0) {
                        clear++;
                    } else {
                        break;
                    }
                }
                if (clear == hole)
                    return true;


                // 회전 180
                clear = 0;
                for (Pos k : keys) {
                    int nr = N - 1 - k.row + r;
                    int nc = N - 1 - k.col + c;

                    if (nr < 0 || nc < 0 || nr >= M || nc >= M)
                        continue;

                    if (lock[nr][nc] == 0) {
                        clear++;
                    } else {
                        break;
                    }
                }
                if (clear == hole)
                    return true;


                // 회전 270
                clear = 0;
                for (Pos k : keys) {
                    int nr = N - 1 - k.col + r;
                    int nc = k.row + c;

                    if (nr < 0 || nc < 0 || nr >= M || nc >= M)
                        continue;

                    if (lock[nr][nc] == 0) {
                        clear++;
                    } else {
                        break;
                    }
                }
                if (clear == hole)
                    return true;
            }
        }

        return false;
    }
}