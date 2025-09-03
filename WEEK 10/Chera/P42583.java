import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new ArrayDeque<>(); // 다리 현재 올라간 무게들 (칸)
        int time = 0;           // 경과 시간
        int sum = 0;            // 다리 위 총 무게
        int idx = 0;            // 대기 트럭 인덱스

        // 처음엔 다리가 비어 있으므로 0으로 채움
        for (int i = 0; i < bridge_length; i++) bridge.add(0);

        // 대기 트럭이 남아 있거나, 다리 위에 아직 무게가 있으면 계속 진행
        while (idx < truck_weights.length || sum > 0) {
            time++;

            sum -= bridge.poll();

            if (idx < truck_weights.length) {
                int next = truck_weights[idx];
                if (sum + next <= weight) {
                    bridge.add(next);
                    sum += next;
                    idx++;
                } else {
                    bridge.add(0);
                }
            } else {
                // 대기 트럭이 없다면 빈칸 밀기
                bridge.add(0);
            }
        }
        return time;
    }
}
