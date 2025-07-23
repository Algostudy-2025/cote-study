
import java.util.*;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int M = key.length;
        int N = lock.length;
        int size = M + 2 * N - 2;

        int[][] extend = new int[size][size];

        int lockCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (lock[i][j] == 0) {
                    lockCnt++;
                }
            }
        }

        int[][] rotatedKey = key;

        for (int k = 0; k < 4; k++) {
            rotatedKey = keyRotation(rotatedKey); // 회전된 키 사용

            // 확장 배열에 키 삽입
            setExtend(extend, rotatedKey, N);

            // 확장 배열 순회하면서 자물쇠 확인
            for (int i = 0; i <= size - N; i++) {
                for (int j = 0; j <= size - N; j++) {
                    int cnt = lockCnt;
                    boolean fail = false;

                    for (int r = 0; r < N; r++) {
                        for (int c = 0; c < N; c++) {
                            int lockVal = lock[r][c];
                            int keyVal = extend[i + r][j + c];

                            if (lockVal == 0 && keyVal == 1) {
                                cnt--;
                            } else if (lockVal == 1 && keyVal == 1) {
                                fail = true; // 돌기 겹침
                            }
                        }
                    }

                    if (!fail && cnt == 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // 시계방향 90도 회전
    static int[][] keyRotation(int[][] key) {
        int L = key.length;
        int[][] rotated = new int[L][L];
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                rotated[j][L - i - 1] = key[i][j];
            }
        }
        return rotated;
    }

    // 확장 배열에 key 삽입
    static void setExtend(int[][] extend, int[][] key, int N) {
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                extend[N - 1 + i][N - 1 + j] = key[i][j];
            }
        }
    }
}








/** 


class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        
        int M = key.length; // key 배열 크기
        int N = lock.length; // lock 배열 크기
        
        int[][] extend = new int[M+2*N-2][M+2*N-2]; // 확장 배열
        
        int lockCnt = 0;
        
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(lock[i][j]==0){
                    lockCnt++;
                }
            }
        }
        boolean ans = false;
        label:for(int k = 0; k<4; k++){ // 키 배열 4번 회전
            int[][] newKey = keyRotation(key); // 키 회전 배열

            
            setExtend(extend, newKey, N);
            for(int i = 0; i<extend.length-N+1; i++){
                for(int j = 0; j<extend.length-N+1; j++){ // 확장 배열 순회
                    
                    int cnt = lockCnt;
                    
                    inner:for(int r = 0; r<N; r++){
                        for(int c = 0; c<N; c++){ // 자물쇠 배열 순회
                            if(lock[r][c]==0 && extend[i+r][j+c]==1){
                                cnt--;
                            }
                            if(lock[r][c]==1 && extend[i+r][j+c]==1){
                                continue inner;
                            }
                        }
                    }
                    
                    if(cnt==0){
                        ans = true;
                        break label;
                    }
                }
            }
            for(int i = 0; i<key.length; i++){
                key[i] = newKey[i];
            }
        }
        return ans;
    }
    
    static int[][] keyRotation(int[][] key){
        int L = key.length;
        int[][] keyRotation = new int[L][L];
        for(int i = 0; i<L; i++){
            for(int j = 0; j<L; j++){
                keyRotation[L-i-1][j] = key[i][j];
            }
        }
        return keyRotation;
    }
    
    static void setExtend(int[][] extend, int[][] key, int N){
        for(int i = 0; i<key.length; i++){
            for(int j = 0; j<key.length; j++){
                extend[N-1+i][N-1+j] = key[i][j]; 
            }
        }
    }
}

*/