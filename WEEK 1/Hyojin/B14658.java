import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*

 */

public class B_14658 {
	
	static int N, M, L, K; //구역의 가로길이, 세로길이, 트램펄린의 한 변의 길이, 별똥별의 수
	
	static class Star{
		int x, y;
		
		Star(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Star[] star = new Star[K];
		
		//별똥별의 좌표 받음
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			star[i] = new Star(x,y);
		}
		
		//지구에 부딪히는 별똥별의 갯수(최소)
		int min = 0;
		
		for(int i = 0; i < K; i++) {
			
		}
		
	}//main

}
