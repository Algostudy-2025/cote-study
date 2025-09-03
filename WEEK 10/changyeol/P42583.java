import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new ArrayDeque<>();
		int time = 0, sum = 0, idx = 0; // 시간, 현재 다리 위 트럭 무게, 다음에 올라올 트럭의 인덱스

		while (true) {
			time++;

			// 다리가 꽉 찼으면 맨 앞을 뺌
			if (bridge.size() == bridge_length) {
				sum -= bridge.poll();
			}

			// 아직 안올라간 트럭이 있고, 그 트럭이 올라가도 다리가 버틸 수 있으면 트럭 넣기
			if (idx < truck_weights.length && sum + truck_weights[idx] <= weight) {
				bridge.add(truck_weights[idx]);
				sum += truck_weights[idx];
				idx++;
			}
			// 트럭이 못 올라가면 빈칸(0) 넣어서 시간 흐름 표현: 큐의 사이즈를 일정하게 유지하기 위해
			else {
				bridge.add(0);
			}

			// 모든 트럭이 다리에 올랐고, 다리 위에 남은게 없을 때 종료
			if (idx == truck_weights.length && sum == 0) {
				break;
			}
		}
		return time;
    }
}