/*

    길을 벽으로 간주하고 모든 소를 대상으로 BFS 진행

    채점 결과
    메모리: 40604 KB
    시간: 364 ms

*/


import java.io.*;
import java.util.*;

public class Main {

    static class Pos {
        int row, col;

        Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        // 길 입력
        List<Pos>[][] roads = new ArrayList[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                roads[i][j] = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            int r2 = Integer.parseInt(st.nextToken()) - 1;
            int c2 = Integer.parseInt(st.nextToken()) - 1;

            roads[r1][c1].add(new Pos(r2, c2));
            roads[r2][c2].add(new Pos(r1, c1));
        }

        // 소 위치 입력
        Pos[] cows = new Pos[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            cows[i] = new Pos(r, c);
        }

        // 풀이 시작
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        int answer = 0;

        // 각 소마다 BFS 진행
        for (int c = 0; c < K; c++) {
            boolean[][] visited = new boolean[N][N];
            Deque<Pos> queue = new ArrayDeque<>();
            queue.offer(cows[c]);

            while (!queue.isEmpty()) {
                Pos curr = queue.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = dr[d] + curr.row;
                    int nc = dc[d] + curr.col;

                    if (nr >= N || nr < 0 || nc >= N || nc < 0 || visited[nr][nc])
                        continue;

                    boolean isRoad = false;
                    for (Pos road : roads[curr.row][curr.col]) {
                        if (road.row == nr && road.col == nc) {
                            isRoad = true;
                            break;
                        }
                    }
                    if (isRoad) continue;

                    visited[nr][nc] = true;
                    queue.offer(new Pos(nr, nc));
                }
            }

            for (int i = c + 1; i < K; i++) {
                if (!visited[cows[i].row][cows[i].col])
                    answer++;
            }
        }

        System.out.println(answer);
    }
}