
/*
<최적화 X 코드>
그냥 기존에 실패한 코드 참고해서 열쇠뿐만 아니라 자물쇠도 90도씩 3번 추가로 회전하면서 기존 코드로 돌림
=> 중복해서 확인하는 구간 생김. (최적화 안됨...)

열쇠의 돌기를 자물쇠의 홈 부분에 채워라!
- 열쇠 돌기와 자물쇠 돌기 만나면 x
- 자물쇠 모든 홈은 열쇠 돌기와 만나야 함 (열쇠 홈과 자물쇠 홈이 만나면 x)
=> 열쇠값==자물쇠값 같으면 0==0 // 1==1이므로 이 경우는 자물쇠 열수 x
=> 자물쇠 모든 홈의 개수 세고, 열쇠 돌기와 만날 때마다 개수 -1

최대 시간복잡도
4(자물쇠 회전) X 4(열쇠회전) X 20X20(자물쇠크기) X 20X20(열쇠크기) => 브루트포스 충분!

*/
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
        
        //자물쇠 상태 회전하면서 확인
        for(int d=0;d<4;d++){
            answer = lockCheck(lock, key, cnt);
            if(answer) return answer;
            lock = rotate(lock);
        }
        
        return answer;
    }
    
    public boolean lockCheck(int[][] lock, int[][] key, int cnt){
        int checkCnt = cnt;
        boolean answer = false;
        
        int M = key[0].length;
        int N = lock[0].length;
        
        //열쇠 회전하면서 확인
        for(int tc=0;tc<4;tc++){
            for(int r=0;r<N;r++){
                for(int c=0;c<N;c++){
                    answer = keyCheck(lock,r,c,key,cnt); //열쇠를 이동하면서 자물쇠와 맞는지 췤
                    if(answer) return answer;
                }
            }
            key = rotate(key); //90도 회전
        }//90도 회전 3번 + 각 파트마다 check!
        return answer;
    }
    
    public boolean keyCheck(int[][] lock, int startR, int startC, int[][] key, int cnt){
        int M = key[0].length;
        int N = lock[0].length;
        
        int checkCnt = cnt;
        
        int endR = Math.min(startR+M, N);
        int endC = Math.min(startC+M, N);
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
    
}