import java.util.ArrayList;

class P42586 {
    public int[] solution(int[] progresses, int[] speeds) {
        int len = progresses.length;
        int[] daysForWork = new int[len];
        for(int i=0;i<len;i++){
            if((100-progresses[i])%speeds[i]==0){
                daysForWork[i] = (100-progresses[i])/speeds[i];
            }else{
                daysForWork[i] = (100-progresses[i])/speeds[i] + 1;
            }
        }
        
        int maxDay = daysForWork[0];
        int cnt = 1; //첫 배포 시 첫번째 기능은 반드시 포함됨
        ArrayList<Integer> arrList = new ArrayList<>();
        for(int i=1;i<len;i++){
            if(daysForWork[i]>maxDay){
                arrList.add(cnt);
                cnt = 1; //초기화
                maxDay = daysForWork[i];
            }else{
                cnt++;
            }
        }
        arrList.add(cnt); //마지막 배포에서 배포되는 기능 개수 추가
        
        int[] answer = new int[arrList.size()];
        for(int i=0;i<arrList.size();i++){
            answer[i] = arrList.get(i);
        }
        
        return answer;
    }
}