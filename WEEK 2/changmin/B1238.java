package changmin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* B1238 파티
 * https://www.acmicpc.net/problem/1238
 */
public class B1238 {
	
	static class Node implements Comparable<Node>{
		int num;
		int time;
		
		public Node(int num, int time) {
			this.num = num;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return this.time - o.time;
		}
		
	}
	
	
	static final int INF = Integer.MAX_VALUE;
	static int N, M, X; //마을의 개수, 도로의 개수, 모이기로한 마을
	static List<List<Node>> list;
	static List<List<Node>> reverseList;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		reverseList = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
			reverseList.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			list.get(start).add(new Node(end, time));
			reverseList.get(end).add(new Node(start, time));
		}
	}
	
	public static int[] dijkstra() {
		return null;
	}	
}
