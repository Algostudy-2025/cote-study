package changmin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* B14658 하늘에서 별똥별이 빗발친다
 * https://www.acmicpc.net/problem/14658
 */

public class B14658 {

	static int N, M, L, K; //가로, 세로, 트램펄린 한 변의 길이, 별똥별의 수
	static List<int[]> stars;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		stars = new ArrayList<>();
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			stars.add(new int[] {x,y});
		}
		//여기까지 입력
		
		int max = 0; //최댓값
		
		for (int[] s : stars) {
            int x = s[0];
            int y = s[1];

            for (int dx = 0; dx <= L; dx++) {
                for (int dy = 0; dy <= L; dy++) {
                    int tx = x - dx;
                    int ty = y - dy;

                    if (tx < 0 || ty < 0 || tx > N || ty > M) continue;

                    int count = 0;
                    
                    for (int[] star : stars) {
                        int sx = star[0];
                        int sy = star[1];
                        if (tx <= sx && sx <= tx + L && ty <= sy && sy <= ty + L) {
                            count++;
                        }
                    }

                    max = Math.max(max, count);
                }
            }
        }
		
		bw.write((K-max) + "");
		bw.flush();
		bw.close();
		br.close();
	}
}
