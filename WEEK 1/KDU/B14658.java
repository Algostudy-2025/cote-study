import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class B14658 {

    static class Pos {
        int x, y; // x: 가로, y: 세로
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, L, K;
    static List<Pos> stars = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 가로 (x)
        M = Integer.parseInt(st.nextToken()); // 세로 (y)
        L = Integer.parseInt(st.nextToken()); // 트램펄린 한 변 길이
        K = Integer.parseInt(st.nextToken()); // 별똥별 수

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars.add(new Pos(x, y));
        }

        int result = K - findMaxStars();

        sb.append(result);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // 가능한 모든 사각형 위치에서 최대 별 개수 찾기
    // 처음에는 모든 좌표로 돌려고 하였으나, 조건에서 N,M이 50만까지 갈 수 있으므로
    // 50만*50만의 반복이 도므로 시간 내 불가능함..
    // 그 후 두번째로 각 별똥별 좌표별로 트램펄린을 놓는 작업을 시작함 Ex) (2,4), (7,3), (3,1) ..
    // 이렇게 하면 모든 좌표를 돌지 못하는 경우도 발생..
    // (2,1) (2,3), (2,1), (2,6), (2,7), (2,10), (2,6)...
    // 이렇게 하면 별똥별이 있는 모든 좌표에 트램펄린을 놓으면서도 시간내 효율적으로 체크 가능!!
    static int findMaxStars() {
        int max = 0;

        for (Pos p1 : stars) {
            for (Pos p2 : stars) {
                int count = countInBox(p1.x, p2.y);
                max = Math.max(max, count);
            }
        }

        return max;
    }

    // (startX, startY)를 왼쪽 아래로 하는 LxL 정사각형 안의 별 개수 세기
    static int countInBox(int startX, int startY) {
        int count = 0;

        for (Pos star : stars) {
            if (star.x >= startX && star.x <= startX + L &&
                star.y >= startY && star.y <= startY + L) {
                count++;
            }
        }

        return count;
    }
}
