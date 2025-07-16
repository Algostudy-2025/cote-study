package B;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B14466_2 {

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N, K, R;
	static List<Pos>[][] roads;
	static boolean[][] cowPos;
	static List<Pos> cows;

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(this == obj) {
				return true;
			}
			
			if(obj == null || getClass() != obj.getClass()) {
				return false;
			}
			
			Pos other = (Pos) obj;
			
			return r == other.r && c == other.c;
		}
		
		@Override
		public int hashCode() {
			return 31 * r + c;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		roads = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				roads[i][j] = new ArrayList<>();
			}
		}

		cowPos = new boolean[N][N];
		cows = new ArrayList<>();

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken()) - 1;
			int c1 = Integer.parseInt(st.nextToken()) - 1;
			int r2 = Integer.parseInt(st.nextToken()) - 1;
			int c2 = Integer.parseInt(st.nextToken()) - 1;
			roads[r1][c1].add(new Pos(r2, c2));
			roads[r2][c2].add(new Pos(r1, c1));
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			cows.add(new Pos(r, c));
			cowPos[r][c] = true;
		}

		int totalCount = 0;
		for (Pos cow : cows) {
			int count = bfs(cow.r, cow.c);
			totalCount += count;
		}

		bw.write(totalCount / 2 + "");
		bw.flush();
		bw.close();
		br.close();
	}

	public static int bfs(int r, int c) {
		int count = 0;
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] { r, c });
		visited[r][c] = true;

		while (!que.isEmpty()) {
			int[] cow = que.poll();
			int cowR = cow[0];
			int cowC = cow[1];

			if (cowPos[cowR][cowC]) {
				count++;
			}

			for (int i = 0; i < 4; i++) {
				int nr = cowR + dr[i];
				int nc = cowC + dc[i];

				if (nr >= 0 && nc >= 0 && nr < N && nc < N && !visited[nr][nc]) {
					if (!roads[cowR][cowC].contains(new Pos(nr, nc))) {
						visited[nr][nc] = true;
						que.offer(new int[] { nr, nc });
					}
				}
			}

		}

		return K - count;
	}
}
