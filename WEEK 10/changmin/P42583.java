import java.util.*;

class Solution {

    static class Truck {
        int weight;
        int endTime;

        Truck(int weight, int endTime) {
            this.weight = weight;
            this.endTime = endTime;
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Queue<Truck> bridge = new LinkedList<>();

        int time = 0;
        int bridgeWeight = 0;
        int idx = 0;

        while (true) {
            time++;

            if (!bridge.isEmpty() && bridge.peek().endTime == time) { // 다리에 트럭이 있고, 나가는 트럭이 있는 경우

                bridgeWeight -= bridge.peek().weight;
                bridge.poll();
            }

            if (bridge.size() < bridge_length && idx < truck_weights.length
                    && bridgeWeight + truck_weights[idx] <= weight) { // 이제 다음 트럭이 갈 수 있는 경우D

                bridge.offer(new Truck(truck_weights[idx], time + bridge_length));
                bridgeWeight += truck_weights[idx];
                idx++;
            }

            if (bridge.isEmpty()) {
                break;
            }

        }

        return time;
    }
}