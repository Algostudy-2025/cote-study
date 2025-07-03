import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14658 {
	static int N, M, L, K;
	static int[] X;// 별똥별 X 좌표
	static int[] Y;// 별똥별 Y 좌표

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken()); // 별똥별이 떨어지는 구역의 가로 길이
		M = Integer.parseInt(st.nextToken()); // 별똥별이 떨어지는 구역의 세로 길이
		L = Integer.parseInt(st.nextToken()); // 트램펄린 한 변의 길이
		K = Integer.parseInt(st.nextToken()); // 별똥별의 개수

		X = new int[K];
		Y = new int[K];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			X[i] = Integer.parseInt(st.nextToken());
			Y[i] = Integer.parseInt(st.nextToken());
		}

		int max = 0; // 잡을 수 있는 최대 별똥별 수

		/*
		 별똥별의 좌표를 기준으로 트램펄린의 왼쪽 위 모서리를 설정함
		 모든 별똥별 좌표쌍을 트램펄린의 좌상단으로 사용
		 해당 위치에서 잡을 수 있는 별똥별 수를 카운트함
		 */
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < K; j++) {
				int x = X[i];//트램펄린 왼쪽 위 x좌표
				int y = Y[j];//틀램펄린 왼쪽 위 y좌표
				int cnt = 0;//현재 위치에서 잡을 수 있는 별똥별 개수

				for (int k = 0; k < K; k++) {
					//별똥별이 트램펄린 영역 안에 있으면 잡힘
					if (X[k] >= x && X[k] <= x + L && Y[k] >= y && Y[k] <= y + L) {
						cnt++;
					}
				}

				//잡을 수 있는 최대 별똥별 개수 갱신
				max = Math.max(max, cnt);
			}
		}

		// 못 잡는 개수 : 별똥별 개수 - 잡은 별똥별 개수
		int res = K - max;
		sb.append(res);

		System.out.println(sb);
	}// main

}// class
