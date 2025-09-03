import java.util.*;
/*
    bridge 큐를 bridge_length만큼의 길이를 갖는 고정 큐라고 생각하고
    1. 큐가 비어 있는 경우 : addLast()를 통해서 집어넣기
    2. 큐가 가득차지 않은 경우
        1) bridge 큐의 합 > weight : addLast(0)
        2) bridge 큐의 합 > weight : addLast()
    3. 큐가 가득찬 경우 : pollFirst()
*/

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> wait = new ArrayDeque<>();
        Queue<Integer> bridge = new ArrayDeque<>();
        
        // wait 큐에 대기 트럭 채우기
        for(int n : truck_weights){
            wait.add(n);
        }
        
        // bridge 큐에 0 채워넣기 -> why?
        for (int i = 0; i < bridge_length; i++) {
            bridge.add(0);
        }
        
        int count = 0;
        int bridgesum = 0;
        
        while(!wait.isEmpty()){
            count++;
            
            // bridge 큐에서 맨 앞놈을 삭제 -> bridgesum도 갱신해줘야됨
            bridgesum -= bridge.poll();
            
            int temp = wait.peek();
            
            if(bridgesum + temp <= weight){ // weight 안넘어서 추가 가능
                bridgesum += wait.poll();
                bridge.add(temp);
            }else{ // weight 넘으면 0 넣자
                bridge.add(0);
            }
        }
        
        // 위 루프가 끝나면 bridge에는 마지막 트럭이 가장 끝에 실려있는 상태임
        // bridge_length만큼 count를 추가시켜줘야 bridge 큐까지 모두 비우는 사이클이 됨
        count += bridge_length;

        return count;
    }
}