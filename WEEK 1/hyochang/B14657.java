package hyochang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14657 {

	public static class Star {
		int x, y;

		Star(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		Star[] stars = new Star[k];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			stars[i] = new Star(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		int best = 0;

		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {

				int left = stars[i].x;
				int bottom = stars[j].y;

				left = Math.max(0, Math.min(left, n - l));
				bottom = Math.max(0, Math.min(bottom, m - l));

				int cnt = 0;
				for (Star s : stars) {
					if (left <= s.x && s.x <= left + l && bottom <= s.y && s.y <= bottom + l) {
						cnt++;
					}
				}
				best = Math.max(best, cnt);
			}
		}

		System.out.println(k - best);
	}
}
