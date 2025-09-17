import java.util.*;

/**
 * 충돌 집계 타이밍 수정 버전:
 * - 막 최종 목적지에 "도착한 틱"에는 여전히 존재로 간주하여 집계에 포함.
 * - 집계가 끝난 뒤에 done=true로 바꿔서 다음 틱부터 제외.
 */
class Solution {

    static final class Pos {
        final int r, c;
        Pos(int r, int c) { this.r = r; this.c = c; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pos)) return false;
            Pos other = (Pos) o;
            return r == other.r && c == other.c;
        }
        @Override public int hashCode() { return 31 * r + c; }
    }

    static final class Robot {
        int r, c;             // 현재 좌표
        int idx;              // 다음 목적지 인덱스(0-based)
        final int[] route;    // 1-based 포인트 번호 배열
        boolean done;         // 완전히 떠났는지
        boolean justFinished; // "이번 틱에 최종 목적지에 도착했는지" 표시(집계 후 done으로 전환)

        Robot(int[] route, int[][] points) {
            this.route = route;
            int start = route[0] - 1;
            this.r = points[start][0];
            this.c = points[start][1];
            this.idx = 1;
            this.done = (route.length == 1); // 시작과 동시에 끝나는 경우
            this.justFinished = false;
        }

        /**
         * 1초 동안 r → c 순서로 1칸 이동.
         * 이번 틱에 "최종 목적지"에 도착했다면 justFinished=true로 표시만 하고
         * done=true는 "집계가 끝난 뒤"에 외부에서 설정한다.
         */
        void step(int[][] points) {
            if (done) return;
            justFinished = false;

            int targetPoint = route[idx] - 1;
            int tr = points[targetPoint][0];
            int tc = points[targetPoint][1];

            if (r != tr) r += Integer.compare(tr, r);
            else if (c != tc) c += Integer.compare(tc, c);

            // 목적지 도착 체크
            if (r == tr && c == tc) {
                idx++;
                if (idx == route.length) {
                    // 최종 목적지에 '이번 틱에' 도착 → 이번 틱 집계에는 포함되어야 함
                    justFinished = true; // 집계 후 외부에서 done=true로 전환
                }
            }
        }

        Pos pos() { return new Pos(r, c); }

        /**
         * 이번 틱 집계에 포함할지 여부:
         * - 아직 이동 중인 로봇 (done=false)
         * - 또는 이번 틱에 막 최종 도착한 로봇 (justFinished=true)
         */
        boolean presentThisTick() {
            return !done || justFinished;
        }
    }

    /** 현재 시각의 위험 좌표 수(좌표당 1회) */
    static int countCollisions(List<Robot> robots) {
        Map<Pos, Integer> freq = new HashMap<>();
        for (Robot rb : robots) {
            if (rb.presentThisTick()) { // 수정 포인트: justFinished도 포함
                Pos p = rb.pos();
                freq.put(p, freq.getOrDefault(p, 0) + 1);
            }
        }
        int add = 0;
        for (int cnt : freq.values()) if (cnt >= 2) add++;
        return add;
    }

    public int solution(int[][] points, int[][] routes) {
        List<Robot> robots = new ArrayList<>(routes.length);
        for (int[] route : routes) robots.add(new Robot(route, points));

        int result = 0;

        // t=0 집계 (시작점에서 겹치면 위험)
        result += countCollisions(robots);

        // 시뮬레이션: 모두 떠날 때까지
        while (true) {
            // 1) 모두 한 칸 이동
            boolean anyActiveNext = false;
            for (Robot rb : robots) {
                if (!rb.done) rb.step(points);
            }

            // 2) 이번 틱 집계 (막 도착한 로봇 포함)
            result += countCollisions(robots);

            // 3) 방금 최종 도착한 로봇들을 이제 떠난 것으로 처리
            for (Robot rb : robots) {
                if (rb.justFinished) {
                    rb.done = true;         // 다음 틱부터 제외
                    rb.justFinished = false;
                }
                if (!rb.done) anyActiveNext = true;
            }

            if (!anyActiveNext) break; // 전부 떠났다면 종료
        }

        return result;
    }
}
