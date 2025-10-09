import java.util.*;
/*
게시판에서 아이디어 보고 Claude와 함께 풀었슴다...
1. 각 로봇당 1씩 움직여서(=1라운드) 목적지까지 도착하는 좌표 리스트를 구함
2. 각 라운드마다 충돌을 확인
*/
/*
자료구조
- List<List<Position>> robotPaths: 각 로봇의 시간별 위치 리스트
- Position: (time, r, c) 정보를 담는 클래스
- Map<String, Integer> collisionMap: "r,c" -> 로봇 수
*/
class Solution {
    public static class Position{
        int time, r, c;
        public Position(int time, int r, int c){
            this.time = time;
            this.r = r;
            this.c = c;
        }
    }
    
    public int solution(int[][] points, int[][] routes) {
        //1. 각 로봇의 전체 경로 생성
        List<List<Position>> robotPaths = new ArrayList<>();
        
        for(int[] route: routes){
            List<Position> path = new ArrayList<>();
            int time = 0;
            int[] currentPos = points[route[0]-1];
            path.add(new Position(time, currentPos[0], currentPos[1]));
            
            //경로상의 다음 포인트들로 이동
            for(int i=1;i<route.length;i++){
                int[] targetPos = points[route[i] - 1];
                List<Position> subPath = generatePath(currentPos, targetPos, time);
                //중복 제거 : subPath의 첫 요소는 이미 추가됨
                path.addAll(subPath.subList(1, subPath.size()));
                
                currentPos = targetPos;
                time = subPath.get(subPath.size()-1).time;
            }
            robotPaths.add(path);
        }
        
        //2. 최대 시간 찾기
        int maxTime = 0;
        for(List<Position> path : robotPaths){
            maxTime = Math.max(maxTime, path.get(path.size() - 1).time);
        }
        
        //3. 각 시간마다 충돌 검사
        
        int dangerCount = 0;
        for(int t=0;t<=maxTime; t++){ //0초부터 검사시작
            Map<String, Integer> positionCount = new HashMap<>();
            
            //각 로봇의 t초 위치 확인
            for(List<Position> path: robotPaths){
                Position pos = findPositionAtTime(path, t);
                if(pos != null){ //종료조건: 로봇이 아직 운행 중
                    String key = pos.r + "," + pos.c;
                    positionCount.put(key, positionCount.getOrDefault(key,0) + 1);
                }
            }
            
            //2대 이상인 좌표 개수 카운트
            // System.out.println("t=" + t + ": " + positionCount);
            for(int count: positionCount.values()){
                if(count >= 2){
                    dangerCount++;
                }
            }
            // System.out.println("  충돌: " + dangerCount + "회");
        }
        
        
        return dangerCount;
    }
    
    //두 포인트 간 최단 경로 생성
    public List<Position> generatePath(int[] start, int[] end, int startTime){
        //종료조건: r 좌표와 c 좌표가 모두 목표에 도달
        List<Position> path = new ArrayList<>();
        int r = start[0], c = start[1];
        int time = startTime;
            
        // 시작점 추가!
        path.add(new Position(time, r, c));
        
        //r좌표 우선 이동
        while(r != end[0]){
            r += (r<end[0]) ? 1 : -1;
            time++;
            path.add(new Position(time, r, c));
        }
        
        //c좌표 이동
        while(c != end[1]){
            c += (c<end[1]) ? 1 : -1;
            time++;
            path.add(new Position(time, r, c));
        }
        
        return path;
    }
    
    //특정 시간의 위치 찾기(이진 탐색 또는 인덱스 직접 접근)
    public Position findPositionAtTime(List<Position> path, int targetTime){
        //종료조건: 로봇의 경로가 targetTime보다 짧으면 null
        if(targetTime >= path.size()) return null;
        return path.get(targetTime); //시간==인덱스
    }
}