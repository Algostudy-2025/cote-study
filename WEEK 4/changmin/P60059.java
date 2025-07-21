class Solution {
	/* P60059 자물쇠와 열쇠
	 * https://school.programmers.co.kr/learn/courses/30/lessons/60059
	 */
	
	/*
	 * 헷갈리지만 않으면 완탐으로 금방 풀만한데
	 * 자물쇠 길이랑 열쇠 길이가 같다고 생각하면서 풀어서 좀 삽질좀 했습니다
	 * 최대 20*20이여서 완탐으로 풀만하고 생각해서 완탐돌렸고
	 * 조건문으로 탈출하는 경우가 많을거라 넉넉했을 듯?
	 */
    public boolean solution(int[][] key, int[][] lock) {
    	
        int N = key.length; //열쇠의길이
        int M = lock.length; //자물쇠의 길이
        int[][] rKey = key; //회전용 키
        for(int i = 0 ; i < 4; i++) { //0 90 180 270 이렇게 4번만 할거임
            
            if(i > 0) { //첫번째는 회전 안해도 ㄱㅊ
                rKey = rotate(rKey);
            }
            
            for(int k = -(M-1) ; k < M; k++) { //키의 가장 오른쪽부터 출발해서 왼쪽 끝까지 돔
                for(int j = -(M-1) ; j < M; j++) {
                    int[][] nKey = makeKey(rKey, M, k, j); // 이동된 키
                    
                    boolean ok = isOk(nKey, lock); //자물쇠와 비교
                    if(ok) {
                        return true;
                    }
                }
            }   
        } 
        return false;
    }

    public static boolean isOk(int[][] key, int[][] lock) { //검증
        int N = lock.length; 
        
        int[][] tmpLock = new int[N][N]; //자물쇠 복사
        
        for(int i = 0 ; i < N; i++) {
            for(int j = 0; j < N; j++) {
                tmpLock[i][j] = lock[i][j];
            }
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(key[i][j] == 1 && tmpLock[i][j] == 1) return false; //열쇠랑 자물쇠랑 둘다 돌기면 실패
                if(key[i][j] == 1 && tmpLock[i][j] == 0) tmpLock[i][j] = 1; //열쇠 돌기, 자물쇠 홈이면 채워줌
            }
        }
        
        for(int i = 0 ; i < N; i++) { //자물쇠가 모두 1인지 검사(딱 맞는지)
            for(int j = 0; j < N; j++){
                if(tmpLock[i][j] == 0) return false; //한칸이라도 안맞으면 실패
            }
        }
        
        return true;
    }
    
    public static int[][] makeKey(int[][] key, int lockLength,int m, int n) { //key -> 원본 키, mn -> 변화량, lockLength -> 자물쇠 길이
        int N = key.length; 
        int[][] nKey = new int[lockLength][lockLength]; //자물쇠 길이만큼 만들어놔야함 1:1 비교할거라서 
        
        for(int i = 0 ; i < lockLength; i++) {
            for(int j = 0 ; j < lockLength; j++) {
                if(i-m < 0 || j-n < 0 || i-m >= N || j-n >= N) { // 밖으로 나가는 경우 홈 처리
                    nKey[i][j] = 0;
                    continue;
                }
                nKey[i][j] = key[i-m][j-n]; //아닐경우 변화 전값 복사
            }
        }
        return nKey;        
    }
    
    public static int[][] rotate(int[][] key) { //2차원 배열 회전
        int N = key.length;
        int[][] rotateKey = new int[N][N];
        
        for(int i = 0 ; i < N; i++) {
            for(int j = 0; j < N; j++) {
                rotateKey[j][N-1-i] = key[i][j];
            }
        }
        return rotateKey;
    }
}
