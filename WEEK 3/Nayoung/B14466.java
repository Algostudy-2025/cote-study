
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간복잡도 hell... 최적화하기!!!
/*✅ 시간복잡도 분석

//✔ 변수 범위
//- N <= 100  → 2N × 2N 확장 격자 사용 → 최대 200 × 200 = 40,000 칸
//- K <= 100  → 소 쌍의 수는 최대 K * (K - 1) / 2 = 4950 쌍

//✔ 각 BFS의 시간복잡도
//- 확장된 격자 내에서 BFS 수행
//- 최악의 경우 모든 칸(200 × 200 = 40,000) 탐색 가능
//- 각 BFS = O(N^2)

//✔ 전체 시간복잡도
//- 총 소 쌍 = O(K^2)
//- 각 쌍마다 BFS 수행 = O(N^2)  (상수생략)
//→ 총 시간복잡도: O(K^2 × N^2)
//→ 최대 연산량 = 100^2 × 100^2 = 10^8 (약 1억 회 연산)
*/

//방1) 표를 확장해서 길을 표시. 이때 확장해서 길을 표시할때는 기존 길의 개수보다 길의 개수가 더 늘어나는 것에 주의.
public class B14466 {
    static int N, K, R;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static boolean[][] visited;
    static boolean[][] map;
    static int[][] cows;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        map = new boolean[2*N][2*N];  //0~2N-1까지. 0은 없다고 생각하기
        visited = new boolean[2*N][2*N];   //0~2N-1까지. 0은 없다고 생각하기
        for(int i=0;i<R;i++) {
            st = new StringTokenizer(br.readLine());
            int r = 2*Integer.parseInt(st.nextToken())-1;
            int c = 2*Integer.parseInt(st.nextToken())-1;
            int r2 = 2*Integer.parseInt(st.nextToken())-1;
            int c2 = 2*Integer.parseInt(st.nextToken())-1;
            map[(r+r2)/2][(c+c2)/2] = true; //길
        }
        
        for(int i=2;i<2*N-1;i+=2) {
            for(int j=2;j<2*N-1;j+=2) {
                int subcnt = 0;
                for(int d=0;d<4;d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if(map[nr][nc]) subcnt++;
                }
                if(subcnt>=2) map[i][j] = true; //길로만들어
            }
        }
        
        cows = new int[K][2]; //0~K-1까지
        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int r = 2*Integer.parseInt(st.nextToken())-1;
            int c = 2*Integer.parseInt(st.nextToken())-1;
            cows[i] = new int[] {r,c};
        }
        
//        System.out.println(Arrays.deepToString(map));
//        System.out.println(Arrays.deepToString(cows));
        
        int cnt = 0;
        for(int i=0;i<K-1;i++) {
            for(int j=i+1;j<K;j++) {
                visited = new boolean[2*N][2*N]; //방문배열 초기화
                boolean res = bfs(cows[i], cows[j]);
                if(!res) cnt++; //길을 건너야만 만날수 있는 소의 쌍
//                System.out.println(cnt);
            }
        }
        
//        bfs(new int[] {3,3}, new int[] {3,5});
        sb.append(cnt);
        System.out.println(sb.toString());
        
    }
    private static boolean bfs(int[] start, int[] end) {
        Queue<int[]> q = new LinkedList();
        q.add(start);
        visited[start[0]][start[1]] = true;
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
//            System.out.println(Arrays.toString(curr));
            if(curr[0]==end[0]&&curr[1]==end[1]) return true; //길을 안건너도 만날 수 있음
            
            for(int d=0;d<4;d++) {
                int nr = curr[0] + dr[d];
                int nc = curr[1] + dc[d];
                if(nr<=0 || nc<=0 || nr>(2*N-1) || nc>(2*N-1)) continue;
                if(visited[nr][nc] || map[nr][nc]) continue;
                visited[nr][nc] = true;
                q.add(new int[] {nr,nc});
            }
        }
        return false;
    }
}