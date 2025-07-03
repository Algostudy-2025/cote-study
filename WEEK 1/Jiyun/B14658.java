/*

    (실패) 1차 시도: 각 별을 좌상단 꼭짓점으로 두고 트램펄린을 배치했으나, 모든 경우를 커버하지 못해 실패
    (실패) 2차 시도: 각 별을 트램펄린 안에 넣을 수 있는 모든 경우를 확인했으나, 시간 초과로 실패
    (성공) 3차 시도: 트램펄린을 배치하는 좌표(x, y) 범위를 별의 위치(x, y)로 한정

    채점 결과
    메모리: 14636 KB
    시간: 128 ms

*/

import java.io.*;
import java.util.*;

public class B14658 {

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {

        // 입력 받기
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Pos[] stars = new Pos[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 풀이 시작
        int max = 0;
        for (Pos xx : stars) {
            for (Pos yy : stars) {
                int x = xx.x;
                int y = yy.y;

                int count = 0;
                for (Pos star : stars) {
                    int sx = star.x;
                    int sy = star.y;

                    if (x <= sx && sx <= x + L && y <= sy && sy <= y + L)
                        count++;
                }

                max = Math.max(max, count);
            }
        }

        System.out.println(K - max);
    }
}