import java.util.*;

//무조건 자물쇠 좌상단이 열쇠 좌상단과 만나는 것부터 시작한다고 생각
//자물쇠 좌상단이 열쇠 우하단과 만날 수도 있다...!!ㅜ.ㅜ
class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        
        int M = key[0].length;
        int N = lock[0].length;
        
        int cnt = 0; //자물쇠 홈의 개수
        for(int r=0;r<N;r++){
            for(int c=0;c<N;c++){
                if(lock[r][c]==0) cnt++;
            }
        }
        
        //기존 자물쇠 상태에서 확인
        for(int tc=0;tc<4;tc++){
            for(int r=0;r<N;r++){
                for(int c=0;c<N;c++){
                    answer = check(lock,r,c,key,cnt); //열쇠를 이동하면서 자물쇠와 맞는지 췤
                    if(answer) return answer;
                }
            }
            key = rotate(key); //90도 회전
            // for(int i=0;i<M;i++){
            //     System.out.println(Arrays.toString(key[i]));
            // }
            // System.out.println("-----------");
        }//90도 회전 3번 + 각 파트마다 check!
        
        //180도 회전한 자물쇠 상태에서 확인
        lock = rotate(lock);
        lock = rotate(lock);
        for(int tc=0;tc<4;tc++){
            for(int r=0;r<N;r++){
                for(int c=0;c<N;c++){
                    answer = check(lock,r,c,key,cnt); //열쇠를 이동하면서 자물쇠와 맞는지 췤
                    if(answer) return answer;
                }
            }
            key = rotate(key); //90도 회전
            // for(int i=0;i<M;i++){
            //     System.out.println(Arrays.toString(key[i]));
            // }
            // System.out.println("-----------");
        }//90도 회전 3번 + 각 파트마다 check!
        
        return answer;
    }
    
    public boolean check(int[][] lock, int startR, int startC, int[][] key, int cnt){
        int M = key[0].length;
        int N = lock[0].length;
        // boolean[][] visited = new boolean[N][N]; //자물쇠 중 열쇠와 만나는 부분
        
        int checkCnt = cnt;
        
        int endR = Math.min(startR+M, N);
        int endC = Math.min(startC+M, N);
        // for(int r=startR;r<endR;r++){
        //     for(int c=startC;c<endC;c++){
        //         visitied[r][c] = true;
        //     }
        // }
        for(int r=startR, keyR=0;r<endR;r++, keyR++){
            for(int c=startC, keyC=0;c<endC;c++, keyC++){
                if(key[keyR][keyC]==lock[r][c]) return false;
                if(key[keyR][keyC]==1&&lock[r][c]==0) checkCnt--;
            }
        }
        
        if(checkCnt!=0) return false;
        return true;
    }
    
    //ok!!
    public int[][] rotate(int[][] key){ 
        int M = key[0].length;
        int[][] newKey = new int[M][M];
        for(int r=0;r<M;r++){
            for(int c=0;c<M;c++){
                newKey[c][M-1-r] = key[r][c];
            }
        }
        return newKey;
    }
    
}