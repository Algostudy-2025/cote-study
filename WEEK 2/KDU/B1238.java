/*
    이해는 했는데 스터디에서 설명 듣겠습니다...
    알려주세요!!
 */

// import java.util.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class B1238{
    
    static class Node implements Comparable<Node> {
        int to, cost;


        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost; // 오름차순: cost가 작은 게 우선
        }
    }

    static int N, M, X;
    static final int INF = Integer.MAX_VALUE;
    static List<Node>[] graph, Reversegraph; // 인접 그래프

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // 인접 그래프 선언 및 초기화
        graph = new ArrayList[N + 1];
        Reversegraph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            Reversegraph[i] = new ArrayList<>();
        }

        // 인접 그래프 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, time)); // 정방향
            Reversegraph[to].add(new Node(from, time)); // 역방향
        }

        int[] go = dijkstra(Reversegraph, X); // i → X
        int[] back = dijkstra(graph, X); // X → i

        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, go[i] + back[i]);
        }

        sb.append(maxTime).append("\n");
        bw.write(sb.toString());
                
        bw.flush();
        bw.close();
        br.close();

                
    }

    // 다익스트라 함수
    // 1. 거리 배열과 방문 배열 초기화, 시작 노드를 우선순위 큐에 삽입
    // 2. 우선순위 큐가 빌 때까지 반복
    // 2-1. 현재 최단 거리 노드를 큐에서 꺼냄
    // 2-2. 이미 방문한 노드는 건너뜀
    // 2-3. 현재 노드를 통해 인접 노드로 가는 비용이 더 작다면 갱신하고 큐에 삽입
    public static int[] dijkstra(List<Node>[] graph, int start) {
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        // 시작노드 삽입(가중치 0)
                
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int now = curr.to;

            if (visited[now]) {
                continue;
            }
            visited[now] = true;

            for (Node next : graph[now]) {
                if (dist[next.to] > dist[now] + next.cost) {
                    dist[next.to] = dist[now] + next.cost;
                    pq.add(new Node(next.to, dist[next.to]));
                }
            }
        }

        return dist;
    }

}