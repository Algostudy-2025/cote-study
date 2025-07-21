/*
<chatGPT 보면서 최적화한 코드>

알게 된 점
- 완벽한 깊은 복사는 clone뿐
- Arrays.copyOf, System.arraycopy() 등은 배열의 요소가 참조 자료형일 경우 복사해도 동일한 요소 참조 값을 가리킴
참고하면 좋을 사이트 : https://blog.naver.com/gglee0127/221353415984

**깊은 복사와 얕은 복사
- 깊은 복사 : 내용물의 값을 복사하여 다른 객체를 만듦
- 얕은 복사 : 주소값만들 복사하여 객체를 만듦

*/


class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        
        int M = key[0].length;
        int N = lock[0].length;
        
        int boardSize = N + 2*(M-1);
        int[][] board = new int[boardSize][boardSize];
        
        //자물쇠를 가운데 삽입
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i + M - 1][j + M - 1] = lock[i][j];
            }
        }
        
        // 열쇠를 4방향 회전하며 전체 board 위를 돌면서 맞춰보기
        for(int tc=0;tc<4;tc++){
            for(int r=0;r<boardSize-(M-1);r++){
                for(int c=0;c<boardSize-(M-1);c++){
                    answer = keyCheck(board,key,r,c,M,N); //열쇠를 이동하면서 자물쇠와 맞는지 췤
                    if(answer) return answer;
                }
            }
            key = rotate(key); //90도 회전
        }//90도 회전 3번 + 각 파트마다 check!
        
        
        return answer;
    }
    
        public boolean keyCheck(int[][] board, int[][] key, int r, int c, int M, int N){
            int[][] temp = copy(board); //원본배열 복사
            
            // 열쇠를 덧셈 방식으로 덮어 씌움
            for(int i=0;i<M;i++){
                for(int j=0;j<M;j++){
                    temp[i+r][j+c] += key[i][j];
                }
            }
            
            // 자물쇠 영역이 모두 1인지 확인
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(temp[i + M - 1][j + M - 1]!=1) return false;
                }
            }
        return true;
    }
    
    public int[][] rotate(int[][] arr){ 
        int M = arr[0].length;
        int[][] newArr = new int[M][M];
        for(int r=0;r<M;r++){
            for(int c=0;c<M;c++){
                newArr[c][M-1-r] = arr[r][c];
            }
        }
        return newArr;
    }
    
    public int[][] copy(int[][] arr){
        int N = arr.length;
        int[][] newArr = new int[N][N];
        for(int i=0;i<N;i++){
            newArr[i] = arr[i].clone();
        }
        return newArr;
    }
    
}