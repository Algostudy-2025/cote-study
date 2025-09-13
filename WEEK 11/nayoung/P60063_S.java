import java.util.*;

//참고: https://stritegdc.tistory.com/283
//수평 -> 수직 회전: 로봇의 상 2칸 또는 하 2칸 비어있어야 함
//수직 -> 수평 회전: 로봇의 좌 2칸 또는 우 2칸 비어있어야 함
//방문배열은 수평/수직 여부까지 포함한 3차원 배열로 표시해야 함
//큐에 넣을 때 (로봇1칸, 로봇2칸, 총횟수, 수평/수직) 으로 넣어야 한다.
class Solution {
    static int N;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    
    public class Pos{
        int r,c;
        Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public class Robot{
        Pos p1, p2;
        int t; //times(횟수)
        int vertical; //수직이면 1, 수평이면 0
        Robot(Pos p1, Pos p2, int t, int vertical){
            this.p1 = p1;
            this.p2 = p2;
            this.t = t;
            this.vertical = vertical;
        }
    }
    
    public int solution(int[][] board) {
        N = board.length;
        boolean[][][] visited = new boolean[N][N][2];
        
        Queue<Robot> q = new LinkedList<>();
        q.add(new Robot(new Pos(0,0),new Pos(0,1),0,0)); //수평으로 시작
        
        int answer = 0;
        while(!q.isEmpty()){
            Robot curr = q.poll();
            Pos p1 = curr.p1;
            Pos p2 = curr.p2;
            int r1 = p1.r;
            int c1 = p1.c;
            int r2 = p2.r;
            int c2 = p2.c;
            if((r1==N-1&&c1==N-1)||(r2==N-1&&c2==N-1)){
                answer = curr.t;
                break;
            }
            
            //(1) 수평/수직 유지한 채 이동
            for(int d=0;d<4;d++){
                int nr1 = r1 + dr[d];
                int nc1 = c1 + dc[d];
                int nr2 = r2 + dr[d];
                int nc2 = c2 + dc[d];
                if(outOfRange(nr1,nc1) ||outOfRange(nr2,nc2)) continue;
                if(board[nr1][nc1]==1||board[nr2][nc2]==1) continue; //or로 연결
                if(visited[nr1][nc1][curr.vertical]&&visited[nr2][nc2][curr.vertical]) continue; //and로 연결
                q.add(new Robot(new Pos(nr1, nc1), new Pos(nr2, nc2), curr.t+1, curr.vertical)); //현재 로봇의 수평/수직을 따라감
                visited[nr1][nc1][curr.vertical] = true;
                visited[nr2][nc2][curr.vertical] = true;
            }
            
            // (2) 회전시키기
            if (curr.vertical == 1) { // 수직 -> 수평
                // 왼쪽 두 칸 비어있는지 확인: c1-1, c2-1 모두 0 이상
                if (c1 - 1 >= 0 && c2 - 1 >= 0 && board[r1][c1 - 1] == 0 && board[r2][c2 - 1] == 0) {
                    // 위쪽 블록을 기준으로 좌측 회전: (r1, c1-1) - (r1, c1)
                    if (!(visited[r1][c1 - 1][0] && visited[r1][c1][0])) {
                        q.add(new Robot(new Pos(r1, c1 - 1), new Pos(r1, c1), curr.t + 1, 0));
                        visited[r1][c1 - 1][0] = true;
                        visited[r1][c1][0] = true;
                    }
                    // 아래쪽 블록을 기준으로 좌측 회전: (r2, c2-1) - (r2, c2)
                    if (!(visited[r2][c2 - 1][0] && visited[r2][c2][0])) {
                        q.add(new Robot(new Pos(r2, c2 - 1), new Pos(r2, c2), curr.t + 1, 0));
                        visited[r2][c2 - 1][0] = true;
                        visited[r2][c2][0] = true;
                    }
                }
                // 오른쪽 두 칸 비어있는지 확인: c1+1, c2+1 모두 N 미만
                if (c1 + 1 < N && c2 + 1 < N && board[r1][c1 + 1] == 0 && board[r2][c2 + 1] == 0) {
                    // 위쪽 블록을 기준으로 우측 회전: (r1, c1) - (r1, c1+1)
                    if (!(visited[r1][c1][0] && visited[r1][c1 + 1][0])) {
                        q.add(new Robot(new Pos(r1, c1), new Pos(r1, c1 + 1), curr.t + 1, 0));
                        visited[r1][c1][0] = true;
                        visited[r1][c1 + 1][0] = true;
                    }
                    // 아래쪽 블록을 기준으로 우측 회전: (r2, c2) - (r2, c2+1)
                    if (!(visited[r2][c2][0] && visited[r2][c2 + 1][0])) {
                        q.add(new Robot(new Pos(r2, c2), new Pos(r2, c2 + 1), curr.t + 1, 0));
                        visited[r2][c2][0] = true;
                        visited[r2][c2 + 1][0] = true;
                    }
                }
            } else { // 수평 -> 수직
                // 위쪽 두 칸 비어있는지 확인: r1-1, r2-1 모두 0 이상
                if (r1 - 1 >= 0 && r2 - 1 >= 0 && board[r1 - 1][c1] == 0 && board[r2 - 1][c2] == 0) {
                    // 왼쪽 블록 기준 위로 회전: (r1-1, c1) - (r1, c1)
                    if (!(visited[r1 - 1][c1][1] && visited[r1][c1][1])) {
                        q.add(new Robot(new Pos(r1 - 1, c1), new Pos(r1, c1), curr.t + 1, 1));
                        visited[r1 - 1][c1][1] = true;
                        visited[r1][c1][1] = true;
                    }
                    // 오른쪽 블록 기준 위로 회전: (r2-1, c2) - (r2, c2)
                    if (!(visited[r2 - 1][c2][1] && visited[r2][c2][1])) {
                        q.add(new Robot(new Pos(r2 - 1, c2), new Pos(r2, c2), curr.t + 1, 1));
                        visited[r2 - 1][c2][1] = true;
                        visited[r2][c2][1] = true;
                    }
                }
                // 아래쪽 두 칸 비어있는지 확인: r1+1, r2+1 모두 N 미만
                if (r1 + 1 < N && r2 + 1 < N && board[r1 + 1][c1] == 0 && board[r2 + 1][c2] == 0) {
                    // 왼쪽 블록 기준 아래로 회전: (r1, c1) - (r1+1, c1)
                    if (!(visited[r1][c1][1] && visited[r1 + 1][c1][1])) {
                        q.add(new Robot(new Pos(r1, c1), new Pos(r1 + 1, c1), curr.t + 1, 1));
                        visited[r1][c1][1] = true;
                        visited[r1 + 1][c1][1] = true;
                    }
                    // 오른쪽 블록 기준 아래로 회전: (r2, c2) - (r2+1, c2)
                    if (!(visited[r2][c2][1] && visited[r2 + 1][c2][1])) {
                        q.add(new Robot(new Pos(r2, c2), new Pos(r2 + 1, c2), curr.t + 1, 1));
                        visited[r2][c2][1] = true;
                        visited[r2 + 1][c2][1] = true;
                    }
                }
            }

        }
        
        
        return answer;
    }
    
    public boolean outOfRange(int r, int c){
        if(r<0||c<0||r>=N||c>=N) return true;
        return false;
    }
}