import java.util.*;

class P42583 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        // 다리 역할을 할 큐
        Deque<Integer> bridge = new LinkedList<>();
        int answer = 0;
        int total_weights = 0;
        int idx = 0;
        int counter = 0;
        int truck_amount = truck_weights.length;
        
        while(counter < truck_amount) {
            answer++;
            
            // 내릴 트럭이 있으면 먼저 내리기
            if(bridge.size() >= bridge_length) {
                int temp = bridge.poll();
                total_weights -= temp;
                if(temp > 0) {
            	    counter++;
                }
            }

            // 조건에 맞춰 트럭 올리기
            if(idx < truck_amount && total_weights + truck_weights[idx] <= weight) {
                bridge.offer(truck_weights[idx]);
                total_weights += truck_weights[idx];
                idx++;
            } else {
                // 트럭을 못 올리는 경우 빈칸 처리
                bridge.offer(0);
            }
        }
        
        return answer;
    }
}

