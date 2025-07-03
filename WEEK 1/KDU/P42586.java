import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        // result : 각 배포 묶음마다 몇 개의 기능이 배포되는지를 저장하는 리스트
        // days : 각 기능이 며칠 뒤에 완료되는지를 저장하는 큐
        List<Integer> result = new ArrayList<>();
        Queue<Integer> days = new LinkedList<>();

        // 1단계: 각 작업별로 완료까지 남은 일수 계산
        for (int i = 0; i < progresses.length; i++) {
            int remaining = 100 - progresses[i];
            // 처음에 그냥 소수점 놔뒀다가 틀려서 다시 올림처리 함..
            int day = (int) Math.ceil((double) remaining / speeds[i]);
            days.add(day);
        }

        // 2단계: 배포 묶음 계산
        while (!days.isEmpty()) {
            int tmpday = days.poll(); // 현재 배포 기준일
            int count = 1;

            // 그 다음 작업이 기준일 이하에 끝나면 같이 배포(큐가 비면 stop)
            while (!days.isEmpty() && days.peek() <= tmpday) {
                days.poll();
                count++;
            }

            result.add(count);
        }

        // 결과 변환(리스트 -> 배열)
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;

    }
}