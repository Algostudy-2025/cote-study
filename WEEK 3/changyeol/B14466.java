import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14466 {

    static int N, K, R; // N: 목초지 크기, K: 소의 마리수, R: 길의 개수
    static boolean[][] visited; // 방문 처리 배열
    static boolean[][][] road; // 길 정보, [0]:상 [1]:하 [2]:좌 [3]:우
    static Cow[] cows; // 소 객체 배열

    static int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dc = {0, 0, -1, 1};

    // Cow 클래스
    static class Cow {
        int row;
        int col;

        Cow(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // 길 정보 초기화
        road = new boolean[N + 1][N + 1][4];

        // 길 입력
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            // 길이 막힌 방향을 양쪽에서 표시
            if (r1 - r2 == 1) { // r1이 아래, r2가 위
                road[r1][c1][0] = true;
                road[r2][c2][1] = true;
            } else if (r2 - r1 == 1) { // r2가 아래, r1이 위
                road[r1][c1][1] = true;
                road[r2][c2][0] = true;
            } else if (c1 - c2 == 1) { // c1이 오른쪽, c2가 왼쪽
                road[r1][c1][2] = true;
                road[r2][c2][3] = true;
            } else if (c2 - c1 == 1) { // c2가 오른쪽, c1이 왼쪽
                road[r1][c1][3] = true;
                road[r2][c2][2] = true;
            }
        }

        // 소 위치 입력
        cows = new Cow[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(row, col);
        }

        int ans = 0;

        // 모든 소쌍에 대해 카운트
        for (int i = 0; i < K; i++) {
            visited = new boolean[N + 1][N + 1];
            // i번째 소 기준으로 BFS
            bfs(cows[i].row, cows[i].col);

            for (int j = i + 1; j < K; j++) {
                // i번째 소가 길을 건너야 j번째 소에게 갈 수 있다면 카운트
                if (!visited[cows[j].row][cows[j].col]) {
                    ans++;
                }
            }
        }

        sb.append(ans);
        System.out.println(sb);
    }

    // 출발 좌표에서 길을 건너지 않고 갈 수 있는 모든 칸을 방문 처리
    static void bfs(int startRow, int startCol) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dr[d];
                int ny = y + dc[d];

                // 범위 밖인 경우
                if (nx < 1 || nx > N || ny < 1 || ny > N) continue;
                // 길을 건너야 하는 경우
                if (road[x][y][d]) continue;
                // 이미 방문한 경우
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
    }
}
