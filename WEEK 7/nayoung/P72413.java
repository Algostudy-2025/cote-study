import java.util.*;

/*풀이방법: 
(1) 플로이드 워셜로 모든 경로에 대한 최저요금 구하기 -> O(V^3)
(2) s~i(경유지) + i~a + i~b 의 최저요금 구하기 (이때 i가 s||a||b인 경우는 자동으로 해당 경로 요금이 0이 되므로 고려안해도 됨)
*/
class Solution {
    static int[][] dist;
    static int INF = Integer.MAX_VALUE;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        dist = new int[n+1][n+1];   //[출발지][도착지]
        //거리 무한대로, 자기자신은 0으로
        for(int i=1;i<=n;i++){
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        //fares 채우기
        for(int i=0;i<fares.length;i++){
            int v1 = fares[i][0];
            int v2 = fares[i][1];
            int cost = fares[i][2];
            dist[v1][v2] = cost;
            dist[v2][v1] = cost;
        }
        
        //플로이드워셜로 dist 배열 채우기
        for(int i=1;i<=n;i++){ //경유지
            for(int j=1;j<=n;j++){ //출발지
                if(dist[j][i]==INF) continue; //출발->경유지 경로 없다면 패스
                for(int k=1;k<=n;k++){ //도착지
                    if(dist[i][k]==INF) continue; //경유지->도착지 경로 없다면 패스
                    if(dist[j][k] > dist[j][i]+dist[i][k]){
                        dist[j][k] = dist[j][i]+dist[i][k]; //경유지 지나는 게 더 최단경로면 업데이트
                    }
                }
            }
        }
        
        //실제 출발지점 s에서의 최저요금 구하기
        int answer = Integer.MAX_VALUE;
        for(int i=1;i<=n;i++){
            answer = Math.min(dist[s][i]+dist[i][a]+dist[i][b], answer);
        }
        
        
        return answer;
    }
}