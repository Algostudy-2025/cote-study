import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {

        int len = progresses.length;
        int[] endDay = new int[len];

        for(int i = 0; i<len; i++){
            endDay[i] = (int) Math.ceil((100-progresses[i])/(double)speeds[i]);
        }
        List<Integer> list = new ArrayList<>(); // 배포하는 기능 개수
        for(int i = 0; i<len; i++){
            int cnt = 1;
            for(int j = i+1; j<len; j++){
                if(endDay[i]>=endDay[j]){
                    cnt++;
                }else{
                    break;
                }
            }
            list.add(cnt);
            i += cnt-1;
        }


        int[] answer = list.stream().mapToInt(i -> i.intValue()).toArray();


        return answer;
    }
}
