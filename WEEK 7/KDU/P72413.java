/*
    결론 : 다익스트라 3번 돌리면 됌
    why?
    출발지점 s, 도착지점1 a, 도착지점2 b에서 다익스트라를 돌려서 각 지점별로
    최단거리를 구한다면, 이들의 합의 최소값은 결국 둘이서 경유하다가
    각 도착지점(a,b)로 가게 되는 최소 거리를 구할 수 있음
*/

import java.util.*;
class Solution {
    
    static class Node{
        int to, w;
        Node(int to, int w){
            this.to = to;
            this.w = w;
        }
    }
    
    static class Edge{
        
        int to, w;
        
        Edge(int to, int w){
            this.to = to;
            this.w = w;
        }
    }
    
    static final int INF = Integer.MAX_VALUE;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 인접그래프부터...
        List<Edge>[] g = new ArrayList[n+1];
        for(int i = 0; i <= n; i++){
            g[i] = new ArrayList<>();
        }
        for(int i = 0; i < fares.length; i++){
            int u = fares[i][0];
            int v = fares[i][1];
            int w = fares[i][2];
            // 양방향(무방향)
            g[u].add(new Edge(v,w));
            g[v].add(new Edge(u,w));
        }
        
        // 다익스트라 돌리자
        int[] distS = dijkstra(n,s,g);
        int[] distA = dijkstra(n,a,g);
        int[] distB = dijkstra(n,b,g);
        
        int ans = INF;
        for(int i = 1; i <= n; i++){
            if(distS[i] == INF || distA[i] == INF || distB[i] == INF)
                continue;
            ans = Math.min(ans, distS[i]+distA[i]+distB[i]);
        }
        return ans;
    }
    
    static int[] dijkstra(int n, int start, List<Edge>[] g){
        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        
        // 정렬 기준 설정 필요
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(nd -> nd.w));
        // visited[start] = true;
        dist[start] = 0; // 시작지점의 거리는 자기자신이므로 0
        pq.add(new Node(start,0)); // 왜 이렇게...??
        
        while(!pq.isEmpty()){
            Node curr = pq.poll();
            if(visited[curr.to])
                continue;
            visited[curr.to] = true;
            
            for(Edge e : g[curr.to]){
                //
                if(dist[e.to] > dist[curr.to] + e.w){
                    dist[e.to] = dist[curr.to] + e.w;
                    pq.add(new Node(e.to, dist[e.to]));
                }
            }
        }
        return dist;
    }
}