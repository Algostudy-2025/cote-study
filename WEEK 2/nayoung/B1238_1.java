import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//파티 - 방2
public class Main {

	static int N, M, X;
	static final int INF = Integer.MAX_VALUE;
	static int[][] dist;
	static ArrayList<Edge>[] arrList;

	public static class Edge implements Comparable<Edge> {
		int to;
		int cost;
		
		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost-o.cost;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 1 ≤ N ≤ 1,000
		M = Integer.parseInt(st.nextToken()); // 1 ≤ M ≤ 10,000
		X = Integer.parseInt(st.nextToken()); // 1 ≤ X ≤ N

		arrList = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			arrList[i] = new ArrayList<Edge>();
		}
		
		for(int i=1;i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			arrList[from].add(new Edge(to, cost));
		}
		
		dist = new int[N+1][N+1];
		for(int i=1;i<=N;i++) {
			Arrays.fill(dist[i], INF);
		}
		for(int i=1;i<=N;i++) {
			dijkstra(i);
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=1;i<=N;i++) {
			max = Math.max(max, dist[i][X]+dist[X][i]);
		}
		
		System.out.println(max);

	}

	private static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue();
		
		dist[start][start] = 0;
		pq.add(new Edge(start,0));
		
		while(!pq.isEmpty()) {
			Edge curr = pq.poll();
			
			for(Edge e: arrList[curr.to]) {
				if(dist[start][e.to] > dist[start][curr.to]+ e.cost) {
					dist[start][e.to] = dist[start][curr.to]+ e.cost;
					pq.add(new Edge(e.to, dist[start][e.to]));
				}
			}
			
		}
		
		
	}
}
