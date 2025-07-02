import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//B14658
//임의로 두 별 a, b를 선택(하나의 별을 두 번 선택도 가능)
//별 a가 트램펄린에 떨어지는 별 중에 x좌표가 가장 작고, 별 b가 트램펄린에 떨어지는 별 중에 y좌표가 가장 작은 별인 상황을 가정
//(이때, 트램펄린의 가장 작은 x,y좌표가 정해지고, 트램펄린 크기에 따라 별 a,b가 트램펄린에 포함되지 못할 수도 있음)
//트램펄린의 가장 작은 x,y좌표와 크기를 통해 트램펄린 범위를 알 수 있음
//별들을 돌아가면서 트램펄린 범위에 포함하는지 확인
class Star {
	int x, y;

	public Star(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Star [x=" + x + ", y=" + y + "]";
	}
	
}

public class B14658 {
	static int N, M, L, K;
	static StringBuilder sb;	
	static Star[] stars;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		stars = new Star[K];
		
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			stars[i] = new Star(x,y);
		}
		
		
		int max = 0; //트램펄린에 부딪히는 최대 별 개수!
		
		for(Star A: stars) {
			for(Star B: stars) {
				int X = A.x;
				int Y = B.y;

				int cnt = 0; //트램펄린에 부딪히는 별 개수
				for(Star e: stars) {
					if(e.x>=X && e.x<=X+L && e.y>=Y && e.y<=Y+L) {
						cnt++;
					}
				}
				
				max = Math.max(max, cnt);
			}
		}
		
		sb.append(K-max);

		
		System.out.println(sb.toString());
	}
}
