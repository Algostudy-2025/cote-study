/*
새로운 배열 arr를 만들어서 가운데에 lock을 위치시킨다.
key를 한칸씩 움직이면서, 돌기가 서로 만나지 않고 자물쇠의 모든 홈을 채울 수 있는지 판단한다.


*/

class Solution {
    
    int N, M;//자물쇠, 열쇠 크기
    int len;//arr 크기
    
    public boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;
        len = N + 2 * (M - 1);
        
        int[][] arr = new int[len][len];
        
        //lock을 중앙에 위치시킴
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                arr[i + M - 1][j + M - 1] = lock[i][j];
            }
        }
        
        //4번 회전하면서 검사
        for(int d = 0; d < 4; d++){
            
            //key가 arr에서 이동 가능한 모든 위치를 시도
            for(int i = 0; i <= len - M; i++){
                for(int j = 0; j <= len - M; j++){
                    
                    //key를 arr에 더함
                    for(int k = 0; k < M; k++){
                        for(int l = 0; l < M; l++){
                            arr[k + i][l + j] += key[k][l];
                        }
                    }
                    
                    //자물쇠 영역이 모두 1이면 결과는 true
                    if(check(arr)){
                        return true;
                    }
                    
                    //key를 arr에서 뺌(원상복구)
                    for(int k = 0; k < M; k++){
                        for(int l = 0; l < M; l++){
                            arr[k + i][l + j] -= key[k][l];
                        }
                    }
                }
            }
            //key 90도 회전
            key = rotate(key);
        }
        //자물쇠 영역이 모두 1이 될 수 없으면 결과는 false
        return false;
    }
    
    //90도 회전
    int[][] rotate(int[][] arr){
        int n = arr.length;
        int[][] rotate = new int[n][n];
        
        for(int i = 0; i < rotate.length; i++){
            for(int j = 0; j < rotate.length; j++){
                rotate[j][n - 1 - i] = arr[i][j];
            }
        }
        return rotate;
    }
    
    //자물쇠 영역이 모두 1인지 확인
    boolean check(int[][] arr){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(arr[i + M - 1][j + M - 1] != 1){
                    return false;
                }
            }
        }
        return true;
    }
}