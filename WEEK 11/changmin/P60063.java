import java.util.*;

class Solution {

    /*
    단순 완탐

    Set을 써서 왔던 포지션은 다시는 방문 안하게 해야 하지 않나?

    이동은 조건로직을 걸면 해결

    이동할때 고려해야할 점
    1. 어떤걸 움직일지
    2. 움직일 수 있는 조건 확인
    3. 좌표 보정을 꼭 걸어야하나?

    */

    static class Robot {
        int x1,y1;
        int x2,y2;
        int time;

        public Robot(int x1, int y1, int x2, int y2,int time) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.time = time;
        }
    }

    static int[] dr = {-1 ,1 ,0, 0};
    static int[] dc = {0,0, -1 ,1 };

    static int N;

    public int solution(int[][] board) {

        N = board.length;
        int answer = 0;

        Queue<Robot> que = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Robot start = new Robot(0,0,0,1,0);
        que.offer(start);
        visited.add(key(0,0,0,1));

        while(!que.isEmpty()) {
            Robot curr = que.poll();

            if(curr.x1 == N-1 && curr.y1 == N-1 || curr.x2 == N-1 && curr.y2 == N - 1) {
                answer = curr.time;
                break;
            }

            for(int i = 0; i < 4; i++) { //단순 상하좌우 이동
                int dx1 = curr.x1 + dr[i];
                int dy1 = curr.y1 + dc[i];
                int dx2 = curr.x2 + dr[i];
                int dy2 = curr.y2 + dc[i];

                if(dx1 < 0 || dx1 >= N || dy1 < 0 || dy1 >= N || dx2 < 0 || dx2 >= N || dy2 < 0 || dy2 >= N) continue; //범위밖이면 패스

                if(board[dx1][dy1] == 1 || board[dx2][dy2] == 1) continue; //벽이면 패스

                if(visited.add(key(dx1,dy1,dx2,dy2))) { //갔던 곳이 아니라면
                    que.offer(new Robot(dx1,dy1,dx2,dy2,curr.time + 1)); //큐에 넣어줌
                }
            }

            //회전
            /*
            아무튼 구현해야함
            근데 좌표 보정을 해줘야함
            (1,0 0,0) - (0,0,1,0) 이 지금 set에 넣었을때 서로 다른 것으로 처리되고 있어서
            */







        }


        return answer;
    }

    public static String key(int x1, int y1, int x2, int y2) {
        return x1 + "," + y1 + "," + x2 + "," + y2;
    }
}