import java.util.*;

class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        
        //각 작업별 배포까지 걸리는 일수 계산
        
        int n = progresses.length;//작업 개수
        
        int[] days = new int[n];//작업별 걸리는 날짜
        
        for(int i = 0; i < n; i++){
            int remain = 100 - progresses[i];
            //딱 나눠 떨어지는 경우
            if(remain % speeds[i] == 0){
                days[i] = remain / speeds[i];
            }
            //나머지가 있는 경우 하루 더 필요함
            else{
                days[i] = remain / speeds[i] + 1;
            }
        }
        
        //첫 번째 기능이 완료돼야 배포 가능 -> 첫 번째 작업이 완료되는 날이 기준이 됨
        int prev = days[0];
            
        int cnt = 1;//배포 개수
        
        for(int i = 1; i < n; i++){
            //현재 작업이 이전 기준일보다 빨리 끝나거나 같은 날에 끝나면
            if(days[i] <= prev){
                cnt++;//배포 개수 증가
            }
            //더 늦게 끝나면
            else{
                answer.add(cnt);
                prev = days[i];//배포일 갱신
                cnt = 1;//배포 개수 초기화
            }
        }
        
        //마지막 남은 작업 배포
        answer.add(cnt);
        
        return answer;
    }
}