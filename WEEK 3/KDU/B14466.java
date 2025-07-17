import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;


public class B14466 {

    static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, K, R;
    static boolean[][][] blocked;
    static List<Pos> cows = new ArrayList<>();
    static int[] dx = { -1, 0, 1, 0 }; // 북 동 남 서
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        blocked = new boolean[N + 1][N + 1][4]; // [x][y][방향]

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            int dir1 = getDirection(r1, c1, r2, c2);
            int dir2 = getDirection(r2, c2, r1, c1);

            blocked[r1][c1][dir1] = true;
            blocked[r2][c2][dir2] = true;
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            cows.add(new Pos(r, c));
        }

        int count = 0;

        for (int i = 0; i < K; i++) {
            for (int j = i + 1; j < K; j++) {
                if (!canReach(cows.get(i), cows.get(j))) {
                    count++;
                }
            }
        }

        sb.append(count).append("\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int getDirection(int r1, int c1, int r2, int c2) {
        if (r2 == r1 - 1)
            return 0; // 북
        if (c2 == c1 + 1)
            return 1; // 동
        if (r2 == r1 + 1)
            return 2; // 남
        if (c2 == c1 - 1)
            return 3; // 서
        return -1;
    }

    static boolean canReach(Pos from, Pos to) {
        boolean[][] visited = new boolean[N + 1][N + 1];
        Queue<Pos> q = new LinkedList<>();
        q.add(from);
        visited[from.x][from.y] = true;

        while (!q.isEmpty()) {
            Pos cur = q.poll();

            if (cur.x == to.x && cur.y == to.y)
                return true;

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx < 1 || ny < 1 || nx > N || ny > N)
                    continue;
                if (visited[nx][ny])
                    continue;
                if (blocked[cur.x][cur.y][d])
                    continue;

                visited[nx][ny] = true;
                q.offer(new Pos(nx, ny));
            }
        }

        return false;
    }

}
