 import java.util.*;

/*
배포까지 몇일 남았는지 계산해서 큐에 넣음
큐에다가 넣어서 앞부터 출력
꺼낸 값이 다음값 이상일때 같이 꺼냄(꺼낸 값의 갯수 계산)
배열에 저장하여 return

시간복잡도 O(N)
*/

public class P42586 {
    public int[] solution(int[] progresses, int[] speeds) {
        
        Queue<Integer> que = new LinkedList<>();
        
        //배포까지 남은 일수를 계산하여 저장
        for(int i = 0; i < progresses.length; i++){
            int left = (100 - progresses[i])/speeds[i];
            //day = 배포까지 남은 일자
            int day;
            if(progresses[i]%speeds[i] == 0){
                day = left;
            }else{
                day = left+1;
            }
            
            que.offer(day);
        }
        
        //결과값 출력용 res
        ArrayList<Integer> res = new ArrayList<>();
        
        //큐 꺼내면서 뒤의 값과 비교
        while(!que.isEmpty()){
            int now = que.poll();
            int count = 1;
            
            //꺼낸 값이 다음값 이상일때 같이 꺼냄
            while(!que.isEmpty() && now >= que.peek()){
                que.poll();
                count++;
            }
            
            
            res.add(count);
        }//while
        
        
        //List에서 배열로 변환하여 출력해야함

        /* 
        int[] result = new int[res.size()];
        for(int i = 0; i < res.size(); i++){
            result[i] = res.get(i);
        }
        */

        return res.stream().mapToInt(i -> i).toArray();
        
    }
}
