/*

    다익스트라를 이용한 최단거리 탐색 문제
    - 역방향 그래프 : 각 마을에서 파티로 가는 최단거리를 찾는데 사용
    - 정방향 그래프 : 파티에서 각 마을로 돌아오는 최단거리 찾는데 사용
    두 최단거리를 더하여 가장 오래 걸리는 경로를 찾음

    참고링크: https://meojiktard.tistory.com/13
    ㄴ 다익스트라에 대한 설명이 정말 잘 되어 있음 bb
    

    채점 결과
    메모리: 18908 KB
    시간: 188 ms

*/


import java.io.*;
import java.util.*;

public class Main {

    static class Road {
        int from, to, time;

        public Road(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 마을
        int M = Integer.parseInt(st.nextToken()); // 도로
        int X = Integer.parseInt(st.nextToken()); // 파티

        // 그래프 초기화
        List<List<Road>> graph = new ArrayList<>();
        List<List<Road>> graphReverse = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
            graphReverse.add(new ArrayList<>());
        }

        // 그래프 생성
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int time = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Road(from, to, time));
            graphReverse.get(to).add(new Road(to, from, time));
        }

        int[] go = new int[N]; // 파티에 가는 최단거리
        int[] back = new int[N]; // 파티에서 돌아오는 최단거리

        // 최대값으로 초기화
        Arrays.fill(go, Integer.MAX_VALUE);
        Arrays.fill(back, Integer.MAX_VALUE);
        go[X - 1] = 0;
        back[X - 1] = 0;

        // 최단거리 탐색
        PriorityQueue<Road> pq = new PriorityQueue<>(Comparator.comparing(road -> road.time));

        boolean[] visited = new boolean[N];
        pq.add(new Road(-1, X - 1, 0));
        while (!pq.isEmpty()) {
            Road curr = pq.poll();
            if (visited[curr.to]) continue;
            visited[curr.to] = true;

            for (Road next : graphReverse.get(curr.to)) {
                if (go[next.to] > go[curr.to] + next.time) {
                    go[next.to] = go[curr.to] + next.time;
                    pq.add(new Road(curr.to, next.to, go[next.to]));
                }
            }
        }

        visited = new boolean[N];
        pq.add(new Road(-1, X - 1, 0));
        while (!pq.isEmpty()) {
            Road curr = pq.poll();
            if (visited[curr.to]) continue;
            visited[curr.to] = true;

            for (Road next : graph.get(curr.to)) {
                if (back[next.to] > back[curr.to] + next.time) {
                    back[next.to] = back[curr.to] + next.time;
                    pq.add(new Road(curr.to, next.to, back[next.to]));
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, go[i] + back[i]);
        }
        System.out.println(answer);
    }
}