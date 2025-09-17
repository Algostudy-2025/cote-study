class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int[][] map = new int[101][101];
        int robot_amounts = routes.length;
        
        // 로봇 상태: [r, c, 현재_목표_인덱스, 완료여부]
        int[][] robots = new int[robot_amounts][4];
        
        // 초기 세팅
        for(int i = 0; i < robot_amounts; i++) {
            int r = points[routes[i][0] - 1][0];
            int c = points[routes[i][0] - 1][1];
            robots[i][0] = r; // 현재 r
            robots[i][1] = c; // 현재 c
            robots[i][2] = 1; // 다음 목표 인덱스 (0번은 시작점이므로 1부터)
            robots[i][3] = 0; // 완료 여부
            
            map[r][c]++;
        }
        
        // 초기 충돌 체크
        for(int r = 1; r <= 100; r++) {
            for(int c = 1; c <= 100; c++) {
                if(map[r][c] >= 2) answer++;
            }
        }
        
        // 시뮬레이션
        while(true) {
            boolean allFinished = true;
            
            // 모든 로봇이 완료되었는지 체크
            for(int i = 0; i < robot_amounts; i++) {
                if(robots[i][3] == 0) { // 아직 완료되지 않은 로봇이 있음
                    allFinished = false;
                    break;
                }
            }
            
            if(allFinished) break;
            
            // 현재 위치에서 로봇들 제거
            for(int i = 0; i < robot_amounts; i++) {
                if(robots[i][3] == 0) { // 아직 완료되지 않은 로봇만
                    map[robots[i][0]][robots[i][1]]--;
                }
            }
            
            // 각 로봇 이동
            for(int i = 0; i < robot_amounts; i++) {
                if(robots[i][3] == 1) continue; // 이미 완료된 로봇
                
                int r = robots[i][0];
                int c = robots[i][1];
                int targetIdx = robots[i][2];
                
                if(targetIdx >= routes[i].length) {
                    robots[i][3] = 1; // 완료 표시
                    continue;
                }
                
                int target_r = points[routes[i][targetIdx] - 1][0];
                int target_c = points[routes[i][targetIdx] - 1][1];
                
                // 이동 로직 (행 우선)
                int nr = r, nc = c;
                if(r != target_r) {
                    nr = r + (target_r > r ? 1 : -1);
                } else if(c != target_c) {
                    nc = c + (target_c > c ? 1 : -1);
                }
                
                robots[i][0] = nr;
                robots[i][1] = nc;
                
                // 목표 지점 도달 체크
                if(nr == target_r && nc == target_c) {
                    robots[i][2]++; // 다음 목표로
                }
                
                // 새 위치에 로봇 추가
                map[nr][nc]++;
            }
            
            // 충돌 체크
            for(int r = 1; r <= 100; r++) {
                for(int c = 1; c <= 100; c++) {
                    if(map[r][c] >= 2) answer++;
                }
            }
        }
        
        return answer;
    }
}
