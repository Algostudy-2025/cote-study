import java.io.*;
import java.util.*;


/**
 - (0,0), (0,1) 에서 시작 -> (N, N)까지
 - 로봇이 가로로 놓여있음 : 이동하는 로봇 상 또는 하에 벽이 있으면 회전 불가
 - 로봇이 세로로 놓여있음 : 이동하는 로봇 좌 또는 우에 벽이 있으면 회전 불가
 - 상, 하, 좌, 우, 회전
 - 두 칸 방문 체크를 어떻게 ??????????????
 => 가로로 있을때 배열, 세로로 있을 때 방문 배열을 따로 만들기?
 => 따로 만들면
 */


class Solution {

    static class Pos{
        int x1;
        int y1;
        int x2;
        int y2;
        boolean garo; // 로봇이 가로로 있으면 true

        public Pos(int x1, int y1, int x2, int y2, boolean garo){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.garo = garo;
        }
    }

    public int solution(int[][] board) {
        int N = board.length;

        int time = 0;

        boolean[][] hori = new boolean[N][N-1]; // 로봇이 가로로 놓여있을때
        boolean[][] verti = new boolean[N-1][N]; // 로봇이 세로로 놓여있을 때

        int[] d = {-1, 1};

        Queue<Pos> qu = new LinkedList<>();

        qu.add(new Pos(0, 0, 0, 1, true));
        hori[0][0] = true;

        while(!qu.isEmpty()){
            int size = qu.size();

            time++;

            for(int i = 0; i<size; i++){

                Pos p = qu.poll();
                int px1 = p.x1;
                int py1 = p.y1;
                int px2 = p.x2;
                int py2 = p.y2;
                boolean garo = p.garo;

                // 앞, 뒤로 움직이는 경우
                for(int k = 0; k<d.length; k++){

                    int nx1 = garo ? px1+d[k] : px1;
                    int ny1 = garo ? py1 : py1+d[k];

                    int nx2 = garo ? px2+d[k] : px2;
                    int ny2 = garo ? py2 : py2+d[k];


                    if(nx1>=N || nx1<0 || ny1>=N || ny1<0 ||
                            nx2>=N || nx2<0 || ny2>=N || ny2<0 ||
                            board[nx1][ny1] == 1 || board[nx2][ny2] == 1){
                        continue;
                    }

                    if(garo){
                        int y = Math.min(ny1, ny2);
                        if(hori[nx1][y]) continue;

                        hori[nx1][y] = true;
                    }else{
                        int x = Math.min(nx1, nx2);
                        if(verti[x][ny1]) continue;

                        verti[x][ny1] = true;
                    }

                    qu.add(new Pos(nx1, ny1, nx2, ny2, p.garo));

                    if( (nx1 == N-1 && ny1 == N-1) || (nx2 == N-1 && ny2 == N-1)){
                        return time;
                    }

                }

                // 회전하는 경우
                if(garo){
                    int nx = px1;
                    int ny1 = Math.min(py1, py2);
                    int ny2 = Math.max(py1, py2);

                    // 위로 이동 (내 위에 벽 있는지 체크 -> 옆친구 위에 벽 있는지 체크)
                    if(nx-1>=0 && board[nx-1][py1] == 0 && board[nx-1][py2] == 0){

                        // p1축
                        if(!verti[nx-1][ny1]){
                            verti[nx-1][ny1]=true;
                            qu.add(new Pos(nx-1, ny1, nx, ny1, false));
                        }


                        //p2축
                        if(!verti[nx-1][py2]){
                            verti[nx-1][py2] = true;
                            qu.add(new Pos(nx, ny2, nx-1, ny2, false));
                        }

                        if( (nx-1 == N-1 && ny1 == N-1) ||
                                (nx == N-1 && ny1 == N-1) ||
                                (nx == N-1 && ny2 == N-1) ||
                                (nx-1 == N-1 && ny2 == N-1)){
                            return time;
                        }
                    }

                    // 아래로 이동
                    if(nx+1<N && board[nx+1][py1] == 0 && board[nx+1][py2]==0){

                        // p1 축
                        if(!verti[nx][ny1]){
                            verti[nx][ny1] = true;
                            qu.add(new Pos(nx, ny1, nx+1, ny1, false));
                        }

                        // p2 축
                        if(!verti[nx][py2]){
                            verti[nx][py2] = true;
                            qu.add(new Pos(nx, ny2, nx+1, ny2, false));
                        }

                        if((nx == N-1 && ny1 == N-1) ||
                                (nx+1 == N-1 && ny1 == N-1) ||
                                (nx == N-1 && ny2 == N-1) ||
                                (nx+1 == N-1 && ny2 == N-1)){
                            return time;
                        }
                    }


                }else{
                    int ny = py1;
                    int nx1 = px1;
                    int nx2 = px2;

                    // 왼쪽으로 이동
                    if(ny-1>=0 && board[nx1][ny-1]==0 && board[nx2][ny-1]==0){

                        //p1 축
                        if(!hori[nx1][ny-1]){
                            hori[nx1][ny-1] = true;
                            qu.add(new Pos(nx1, ny-1, nx1, ny, true));
                        }

                        //p2 축
                        if(!hori[nx2][ny-1]){
                            hori[nx2][ny-1] = true;
                            qu.add(new Pos(nx2, ny-1, nx2, ny, true));
                        }

                        if((nx1 == N-1 && ny-1 == N-1) ||
                                (nx1 == N-1 && ny == N-1) ||
                                (nx2 == N-1 && ny-1 == N-1) ||
                                (nx2 == N-1 && ny == N-1)){
                            return time;
                        }
                    }

                    // 오른쪽으로 이동
                    if(ny+1<N && board[nx1][ny+1]==0 && board[nx2][ny+1]==0){
                        //p1 축
                        if(!hori[nx1][ny]){
                            hori[nx1][ny] = true;
                            qu.add(new Pos(nx1, ny, nx1, ny+1, true));
                        }
                        //p2 축
                        if(!hori[nx2][ny]){
                            hori[nx2][ny] = true;
                            qu.add(new Pos(nx2, ny, nx2, ny+1, true));
                        }
                        if((nx1 == N-1 && ny == N-1) ||
                                (nx1 == N-1 && ny+1 == N-1) ||
                                (nx2 == N-1 && ny == N-1) ||
                                (nx2 == N-1 && ny+1 == N-1)){
                            return time;
                        }
                    }

                }


            }
        }
        return time;
    }




}