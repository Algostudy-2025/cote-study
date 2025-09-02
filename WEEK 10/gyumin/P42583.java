import java.util.*;

class P42583 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        Deque<Integer> bridge = new LinkedList<>();
        int answer = 0;
        int total_weights = 0;
        int idx = 0;
        int counter = 0;
        int truck_amount = truck_weights.length;
        
        while(counter < truck_amount) {
            answer++;
            
            if(bridge.size() >= bridge_length) {
                int temp = bridge.poll();
                total_weights -= temp;
                if(temp > 0) {
            	    counter++;
                }
            }
            
            if(idx < truck_amount && total_weights + truck_weights[idx] <= weight) {
                bridge.offer(truck_weights[idx]);
                total_weights += truck_weights[idx];
                idx++;
            } else {
                bridge.offer(0);
            }
        }
        
        return answer;
    }
}

