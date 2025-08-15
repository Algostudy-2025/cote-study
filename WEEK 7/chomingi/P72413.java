import java.util.*;


// 복잡도는 O(ElogV), 근데 효율성 테스트에서 그래프 형상에 따라서 성능 미세하게 다를듯
class Solution {
    List<Edge>[] graph;
    static final long INF = 1_000_000_000_000_000L;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new ArrayList[n+1];
        for (int i = 1; i<=n; i++) graph[i] = new ArrayList<>();
        for (int[] fare : fares){
            graph[fare[0]].add(new Edge(fare[1],fare[2]));
            graph[fare[1]].add(new Edge(fare[0],fare[2]));
        }
        
        // 아이디어는 다익스트라 or 플루이드워셜(n^3, n 200 이하라 충분) 3번쓰기 
        long[] distS = dijkstra(s,n);
        long[] distA = dijkstra(a,n);
        long[] distB = dijkstra(b,n);
        
        long answer = INF;
        
        for (int i = 1; i<=n; i++) answer = Math.min(answer, distS[i]+distA[i]+distB[i]);
        
        return (int) answer;
    }
    
    public long[] dijkstra(int start, int n){
        long[] dist = new long[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o[0]));
        // 우선순위큐에 시작점 추가
        pq.offer(new long[]{0,start});
        
        // 우선순위큐가 빌때까지
        while (!pq.isEmpty()){
            // 가장 가까운 지점
            long[] cur = pq.poll();
            // 이미 방문했던 경우
            if (cur[0] != dist[(int) cur[1]]) continue;
            
            // 해당 지점과 연결된 노드 방문
            for (Edge next : graph[(int) cur[1]]){
                // 기존 최단거리보다 짧은 거리인 경우
                if (cur[0]+next.cost<dist[next.to]){
                    // 최단거리 갱신
                    dist[next.to] = cur[0]+next.cost;
                    // 해당 노드 우선순위큐 추가
                    pq.offer(new long[]{cur[0]+next.cost, next.to});
                }
            }
        }
        return dist;
    }
    
    class Edge{
        private int to;
        private int cost;
        
        public Edge(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
    }
}
