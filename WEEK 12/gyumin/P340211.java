class Solution {
    public int solution(int[][] points, int[][] routes) {

        int answer = 0;

        int[][] map = new int [101][101];
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int robot_amounts = routes.length; 
        int[][] robots = new int[robot_amounts][];

        // 초기 세팅
        for(int i = 0; i < robot_amounts; i++) {
            int r = points[routes[i][0] - 1][0];
            int c = points[routes[i][0] - 1][1];
            robots[i] = new int[] {r, c, 0};
            map[r][c]++;

            // 2를 넘어가는 경우는 이미 위험 지역에 들어가는 거니까
            if(map[r][c] == 2) answer++;
        }

        int fin_cnt = 0;

        while(fin_cnt != robot_amounts) {
            // 한 틱이 돌았으니 어디론가 이동을 할 것
            for(int i = 0; i < robot_amounts; i++) {
                int r = robots[i][0];
                int c = robots[i][1];
                map[r][c]--;
            }
            for(int i = 0; i < robot_amounts; i++) {
                int r = robots[i][0];
                int c = robots[i][1];
                int job = robots[i][2];

                if(job >= routes[i].length) continue;

                int nr = 0;
                int nc = 0;

                int target = routes[i][job] - 1;
                int target_r = points[target][0];
                int target_c = points[target][1];

                double dist = Double.MAX_VALUE;

                for(int j = 0; j < 4; j++) {
                    int tr = r + dr[j];
                    int tc = c + dc[j];

                    if(tr < 1 || tr > 100 || tc < 1 || tc > 100) continue;

                    double td = Math.sqrt(Math.pow(target_r - tr, 2) + Math.pow(target_c - tc, 2));

                    if(dist > td) {
                        nr = tr;
                        nc = tc;
                        dist = td;
                    }
                }

                robots[i][0] = nr;
                robots[i][1] = nc;
                map[nr][nc]++;

                if(target_r == nr && target_c == nc) robots[i][2]++;
                if(robots[i][2] >= routes[i].length) fin_cnt++;
            }
            for(int r = 1; r <= 100 ; r++) {
                for(int c = 1; c <= 100; c++){
                    if(map[r][c] >= 2) answer++;
                }
            }
        }

        return answer;
    }
}

