import java.util.*;

/*
최단거리..BFS냄새가 난다..
근데 1X2 짜리 드론? 어떻게 관리하지?
회전을 어떻게 구현하지?
기준 한칸, 다른 한칸은 방향으로 표현? -> 무슨 말인지 모르겠다...
*/


class Solution {
    static class Drone{
        int r, c; //기준점의 (행,열)
        int dir; //방향(0:가로, 1:세로)
        int t; //걸린 시간
        
        Drone(int r, int c, int dir, int t){
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.t = t;
        }
    }
    
    public int solution(int[][] board) {
        int n = board.length;
        boolean[][][] visited = new boolean[n][n][2];
        Deque<Drone> q = new ArrayDeque<>();
        
        visited[0][0][0] = true;
        q.add(new Drone(0, 0, 0, 0));
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while(!q.isEmpty()){
            Drone cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int dir = cur.dir;
            int t = cur.t;
            
            //두 칸 중 하나가 목표 지점(n-1, n-1)에 도착하면 종료
            if(reached(r, c, dir, n)){
                return t;
            }
            
            // 1. 평행 이동하는 경우
            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(canStay(board, nr, nc, dir) && !visited[nr][nc][dir]){
                    visited[nr][nc][dir] = true;
                    q.add(new Drone(nr, nc, dir, t+1));
                }
            }
            
            // 2. 회전하는 경우
            if(dir == 0){// 가로 -> 세로
                //위쪽으로 회전
                if(in(r-1, c, n) && in(r-1, c+1, n) && board[r-1][c] == 0 && board[r-1][c+1] ==0){
                    //왼쪽 칸을 축으로
                    if(canStay(board, r-1, c, 1) && !visited[r-1][c][1]){
                        visited[r-1][c][1] = true;
                        q.add(new Drone(r-1, c, 1, t+1));
                    }
                    //오른쪽 칸을 축으로
                    if(canStay(board, r-1, c+1, 1) && !visited[r-1][c+1][1]){
                        visited[r-1][c+1][1] = true;
                        q.add(new Drone(r-1, c+1, 1, t+1));
                    }
                }
                //아래쪽으로 회전
                if(in(r+1, c, n) && in(r+1, c+1, n) && board[r+1][c] == 0 && board[r+1][c+1] == 0){
                    //왼쪽 칸을 축으로
                    if(canStay(board, r, c, 1) && !visited[r][c][1]){
                        visited[r][c][1] = true;
                        q.add(new Drone(r, c, 1, t+1));
                    }
                    //오른쪽 칸을 축으로
                    if(canStay(board, r, c+1, 1) && !visited[r][c+1][1]){
                        visited[r][c+1][1] = true;
                        q.add(new Drone(r, c+1, 1, t+1));
                    }
                }
            }
            else{//세로 -> 가로
                //왼쪽으로 회전 
                if(in(r, c-1, n) && in(r+1, c-1, n) && board[r][c-1] == 0 && board[r+1][c-1] == 0){
                    //위쪽 칸을 기준으로
                    if(canStay(board, r, c-1, 0) && !visited[r][c-1][0]){
                        visited[r][c-1][0] = true;
                        q.add(new Drone(r, c-1, 0, t+1));
                    }
                    //아래쪽 칸을 기준으로
                    if(canStay(board, r+1, c-1, 0) && !visited[r+1][c-1][0]){
                        visited[r+1][c-1][0] = true;
                        q.add(new Drone(r+1, c-1, 0, t+1));
                    }
                }
                //오른쪽으로 회전
                if(in(r, c+1, n) && in(r+1, c+1, n) && board[r][c+1] == 0 && board[r+1][c+1] == 0){
                    //위쪽 칸을 기준으로
                    if(canStay(board, r, c, 0) && !visited[r][c][0]){
                        visited[r][c][0] = true;
                        q.add(new Drone(r, c, 0, t+1));
                    }
                    //아래쪽 칸을 기준으로
                    if(canStay(board, r+1, c, 0) && !visited[r+1][c][0]){
                        visited[r+1][c][0] = true;
                        q.add(new Drone(r+1, c, 0, t+1));
                    }
                }
            }
        }
        return -1;
    }
    
    
    
    //두 칸 모두 보드 안에 있고 0인지 확인
    private boolean canStay(int[][] b, int r, int c, int dir){
        int n = b.length;
        if(!in(r, c, n)){
            return false;
        }
        if(dir == 0){
            return in(r, c+1, n) && b[r][c] == 0 && b[r][c+1] == 0;
        }
        else{
            return in(r+1, c, n) && b[r][c] == 0 && b[r+1][c] == 0;
        }
    }
    
    
    // 목표 지점 도착 여부
    private boolean reached(int r, int c, int dir, int n) {
        if (dir == 0) { // 가로
            return (r == n-1 && c == n-1) || (r == n-1 && c+1 == n-1);
        } else {        // 세로
            return (r == n-1 && c == n-1) || (r+1 == n-1 && c == n-1);
        }
    }

    
    // 범위 체크
    private boolean in(int r, int c, int n) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}