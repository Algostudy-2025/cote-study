import java.util.*;

class Solution {

    static class Pos {
        int x1, y1, x2, y2;
        boolean garo; // true=가로, false=세로
        public Pos(int x1,int y1,int x2,int y2,boolean garo){
            this.x1=x1; this.y1=y1; this.x2=x2; this.y2=y2; this.garo=garo;
        }
    }

    public int solution(int[][] board){
        int N = board.length;

        boolean[][] hori = new boolean[N][N-1];   // (x,y)-(x,y+1)
        boolean[][] verti = new boolean[N-1][N];  // (x,y)-(x+1,y)

        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0,0,0,1,true));
        hori[0][0] = true;

        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        int time = 0;

        while(!q.isEmpty()){
            int size = q.size();
            time++; // 이번 레벨 시작

            for(int s=0;s<size;s++){
                Pos p = q.poll();
                int x1=p.x1,y1=p.y1,x2=p.x2,y2=p.y2;

                // ---- 상하좌우 이동 ----
                for(int i=0;i<4;i++){
                    int nx1=x1+dx[i];
                    int ny1=y1+dy[i];
                    int nx2=x2+dx[i];
                    int ny2=y2+dy[i];

                    if(inRange(nx1,ny1,N)&&inRange(nx2,ny2,N)
                            && board[nx1][ny1]==0 && board[nx2][ny2]==0){
                        if(p.garo){
                            int y=Math.min(ny1,ny2);
                            if(!hori[nx1][y]){
                                hori[nx1][y]=true;
                                if(nx1==N-1&&y==N-1 || nx2==N-1&&ny2==N-1) return time;
                                q.add(new Pos(nx1,ny1,nx2,ny2,true));
                            }
                        }else{
                            int x=Math.min(nx1,nx2);
                            if(!verti[x][ny1]){
                                verti[x][ny1]=true;
                                if(nx1==N-1&&ny1==N-1 || nx2==N-1&&ny2==N-1) return time;
                                q.add(new Pos(nx1,ny1,nx2,ny2,false));
                            }
                        }
                    }
                }

                // ---- 회전 ----
                if(p.garo){
                    // 위쪽 회전
                    if(x1-1>=0 && board[x1-1][y1]==0 && board[x2-1][y2]==0){
                        int yL=Math.min(y1,y2);
                        int yR=Math.max(y1,y2);
                        if(!verti[x1-1][yL]){
                            verti[x1-1][yL]=true;
                            if(x1-1==N-1&&yL==N-1 || x1==N-1&&yL==N-1) return time;
                            q.add(new Pos(x1-1,yL,x1,yL,false));
                        }
                        if(!verti[x1-1][yR]){
                            verti[x1-1][yR]=true;
                            if(x2-1==N-1&&yR==N-1 || x2==N-1&&yR==N-1) return time;
                            q.add(new Pos(x2-1,yR,x2,yR,false));
                        }
                    }
                    // 아래쪽 회전
                    if(x1+1<N && board[x1+1][y1]==0 && board[x2+1][y2]==0){
                        int yL=Math.min(y1,y2);
                        int yR=Math.max(y1,y2);
                        if(!verti[x1][yL]){
                            verti[x1][yL]=true;
                            if(x1==N-1&&yL==N-1 || x1+1==N-1&&yL==N-1) return time;
                            q.add(new Pos(x1,yL,x1+1,yL,false));
                        }
                        if(!verti[x1][yR]){
                            verti[x1][yR]=true;
                            if(x2==N-1&&yR==N-1 || x2+1==N-1&&yR==N-1) return time;
                            q.add(new Pos(x2,yR,x2+1,yR,false));
                        }
                    }
                } else { // 세로
                    // 왼쪽 회전
                    if(y1-1>=0 && board[x1][y1-1]==0 && board[x2][y2-1]==0){
                        int xT=Math.min(x1,x2);
                        int xB=Math.max(x1,x2);
                        if(!hori[xT][y1-1]){
                            hori[xT][y1-1]=true;
                            if(xT==N-1&&y1-1==N-1 || xT==N-1&&y1==N-1) return time;
                            q.add(new Pos(xT,y1-1,xT,y1,true));
                        }
                        if(!hori[xB][y1-1]){
                            hori[xB][y1-1]=true;
                            if(xB==N-1&&y2-1==N-1 || xB==N-1&&y2==N-1) return time;
                            q.add(new Pos(xB,y2-1,xB,y2,true));
                        }
                    }
                    // 오른쪽 회전
                    if(y1+1<N && board[x1][y1+1]==0 && board[x2][y2+1]==0){
                        int xT=Math.min(x1,x2);
                        int xB=Math.max(x1,x2);
                        if(!hori[xT][y1]){
                            hori[xT][y1]=true;
                            if(xT==N-1&&y1==N-1 || xT==N-1&&y1+1==N-1) return time;
                            q.add(new Pos(xT,y1,xT,y1+1,true));
                        }
                        if(!hori[xB][y1]){
                            hori[xB][y1]=true;
                            if(xB==N-1&&y2==N-1 || xB==N-1&&y2+1==N-1) return time;
                            q.add(new Pos(xB,y2,xB,y2+1,true));
                        }
                    }
                }
            }
        }
        return -1;
    }

    private boolean inRange(int x,int y,int N){
        return x>=0 && y>=0 && x<N && y<N;
    }
}
