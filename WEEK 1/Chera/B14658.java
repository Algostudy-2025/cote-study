import java.io.*;
import java.util.*;

public class B14658 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 별똥별 구역 가로
        int M = Integer.parseInt(st.nextToken()); // 별똥별 구역 세로
        int L = Integer.parseInt(st.nextToken()); // 트램펄린 길이
        int K = Integer.parseInt(st.nextToken()); // 별똥별의 수

        int[][] stars = new int[K][2]; //[0]:x, [1]:y
        for(int i = 0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Integer.parseInt(st.nextToken());
            stars[i][1] = Integer.parseInt(st.nextToken());
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                int x = stars[i][0]; // 기준 x
                int y = stars[j][1]; // 기준 y

                int cnt = 0;
                for (int k = 0; k < K; k++) {
                    int sx = stars[k][0];
                    int sy = stars[k][1];

                    if (sx >= x && sx <= x + L &&
                            sy >= y && sy <= y + L) {
                        cnt++;
                    }
                }
                max = Math.max(max, cnt);
            }
        }
        List<Integer> list = new ArrayList<>();

        System.out.println(K - max);

    }
}
