package changmin;

import java.util.*;

class P340211 {

    /*

    로봇을 다익스트라를 돌리면 -> 시간 초과

    각 포인트를 미리 다익스트라를 돌려서 point to point 경로를 미리 다 저장해둠

    그리고 시뮬레이션을 하면? 될것

    */

    static class Point implements Comparable<Point> {

        int x, y, length;
        List<int[]> course;

        Point(int x, int y, int length, List<int[]> course) {
            this.x = x;
            this.y = y;
            this.length = length;
            this.course = course;
        }

        @Override
        public int compareTo(Point o) {
            return length - o.length;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static List[] courses;

    public int solution(int[][] points, int[][] routes) {

        int answer = 0;

        courses = new ArrayList[points.length];
        for (int i = 0; i < points.length; i++) {
            courses[i] = new ArrayList<>();
        }

        for(int i = 0; i < points.length; i++) {
            //point를 기준으로 n번 돌림
        }

        int robot = routes.length;

        return answer;
    }

    public static List<int[]> dijkstra(int x, int y) {
        List<int[]> course = null;

        // 다익스트라 돌리면서 class에 경로저장
        // x,y 가 시작 번호
        // 방문처리하면서 돌려야하고, 각 좌표마다 point인지 확인해야함

        return course;
    }
}