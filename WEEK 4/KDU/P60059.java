import java.util.*;
class Solution {

    // key들을 돌리는 함수(cnt변수 받아서 횟수까지 넣어줌)
    public int[][] rotate(int[][] key, int cnt) {
        int len = key.length;
        int[][] changekey = key; // changekey : 돌린 배열을 저장하는 변수
        // 이중 for문을 통해 회전 구현([j][len-1-i])
        for (int k = 0; k < cnt; k++) {
            int[][] temparr = new int[len][len];
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    temparr[j][len - 1 - i] = changekey[i][j];
                }
            }
            changekey = temparr;
        }

        return changekey;
    }

    // 확장된 lock배열에 키를 넣어보면서 1이 채워졌는지 체크해봄
    // -> 중간에 0이 있으면 false, 다 채워져있으면 true로 반환
    public boolean lockcheck(int[][] extlock, int locksize, int keysize) {
        for (int i = 0; i < locksize; i++) {
            for (int j = 0; j < locksize; j++) {
                if (extlock[i + keysize - 1][j + keysize - 1] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean solution(int[][] key, int[][] lock) {
//		boolean answer = true;
        int l = lock.length;
        int k = key.length;
        int size = l + 2 * k - 1; // 확장 lock 배열 크기

        // 자물쇠 확장 후 기존 자물쇠의 값을 가운데에 넣음
        int[][] extlock = new int[size][size];
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                // 기존 i,j에서 k-1(키 크기)만큼 늘려서 for문을 돌려서 확장 lock의 가운데에 기존 lock값 대입
                extlock[i + k - 1][j + k - 1] = lock[i][j];
            }
        }

        for (int r = 0; r < 4; r++) { // 회전(0,90,180,270도를 위한 반복문)
            int[][] rkey = rotate(key, r);

            for (int i = 0; i < size - k + 1; i++) { // 확장된 lock에서 key만큼 도는 행
                for (int j = 0; j < size - k + 1; j++) {// 확장된 lock에서 key만큼 도는 열
                    // 확장된 lock에 key를 덮기
                    for (int x = 0; x < k; x++) {
                        for (int y = 0; y < k; y++) {
                            extlock[i + x][j + y] += rkey[x][y];
                        }
                    }
                    // 원래 lock 영역(l x l)이 전부 1인지 확인
                    if (lockcheck(extlock, l, k)) return true;

                    // 원상복구...
                    // 이거 안해서 몇번 틀림
                    for (int x = 0; x < k; x++) {
                        for (int y = 0; y < k; y++) {
                            extlock[i + x][j + y] -= rkey[x][y];
                        }
                    }
                }
            }
        }
        return false;
    }
}