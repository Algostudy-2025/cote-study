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

/* B14466 소가 길을 건너간 이유6
 * https://www.acmicpc.net/problem/14466
 */
public class B14466 {
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static int N, K, R;
	static boolean[][] load;
	static boolean[][] cowPos;
	static List<int[]> cows;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //목초지
		K = Integer.parseInt(st.nextToken()); //소
		R = Integer.parseInt(st.nextToken());//길 
		
		
		load = new boolean[N][N];
		cowPos = new boolean[N][N];
		cows = new ArrayList<>();
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) -1;
			int c = Integer.parseInt(st.nextToken()) -1;
			int r2 = Integer.parseInt(st.nextToken()) -1;
			int c2 = Integer.parseInt(st.nextToken()) -1;
			
			if(r == r2) {
				if(c > c2) {
					for(int j = c2; j <= c; j++) {
						load[r][j] = true;
					}
				} else if(c < c2) {
					for(int j = c; j <= c2; j++) {
						load[r][j] = true;
					}
				} else {
					load[r][c] = true;
				}
			} else {
				if(r > r2) {
					for(int j = r2; j <= r; j++) {
						load[j][c] = true;
					}
				} else if(r < r2) {
					for(int j = r; j <= r2; j++) {
						load[j][c] = true;
					}
				} else {
					load[r][c] = true;
				}
			}
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			cows.add(new int[] {r,c});
			cowPos[r][c] = true;
		}
		
		int totalCount = 0;
		
		for(int[] cow : cows) {
			int count = bfs(cow[0],cow[1]);
			totalCount += count;
		}
		
		bw.write(totalCount/2 + "");
		bw.flush();
		bw.close();
		br.close();
		
		
	}
	
	public static int bfs(int r, int c) {
		boolean[][] used = new boolean[N][N];
		int count = 0;
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {r,c});
		used[r][c] = true;
		
		while(!que.isEmpty()) {
			int[] cow = que.poll();
			int cowR = cow[0];
			int cowC = cow[1];
			
			if(cowPos[cowR][cowC]) {
				count++;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = cowR + dr[i];
				int nc = cowC + dc[i];
				
				if(nr >= 0 && nc >= 0 && nr < N && nc < N && !used[nr][nc] && !load[nr][nc]) {
					used[nr][nc] = true;
					que.offer(new int[] {nr,nc});
				}
			}
		}
		
		
		return K-count-1;
	}
}
