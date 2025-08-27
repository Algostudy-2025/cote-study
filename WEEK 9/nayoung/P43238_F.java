import java.util.*;

//우선순위큐 이용 (N*logK, K = times.length) => 터짐...
class Solution {
    static class State implements Comparable<State> {
        int originTime;
        long totalTime;

        State(int originTime, long totalTime) {
            this.originTime = originTime;
            this.totalTime = totalTime;
        }

        @Override
        public int compareTo(State o) {
            return this.totalTime < o.totalTime ? -1 : (this.totalTime == o.totalTime ? 0 : 1);
        }
    }

    public long solution(int n, int[] times) {
        PriorityQueue<State> pq = new PriorityQueue<>();
        for (int e : times)
            pq.offer(new State(e, e));
        long answer = 0;
        for (int i = n; i > 0; i--) {
            State curr = pq.poll();
            answer = curr.totalTime;
            // System.out.println(curr.originTime+" "+curr.totalTime);
            pq.offer(new State(curr.originTime, curr.originTime + answer));
        }
        return answer;
    }
}