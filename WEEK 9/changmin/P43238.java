import java.util.*;

class Solution {

    //n 사람의 수
    //times 심사관이 심사하는데 걸리는 시간

    static int N;
    static int[] Times;
    static int counter;

    static class SimSa implements Comparable<SimSa> {
        int num;
        Long endTime;

        SimSa (int num, Long endTime) {
            this.num = num;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(SimSa o) {
            return (int) (endTime - o.endTime);
        }
    }

    public long solution(int n, int[] times) {
        long answer = 0;
        N = n;
        Times = times;
        counter = times.length;

        PriorityQueue<SimSa> pq = new PriorityQueue<>();

        Arrays.sort(Times);

        for(int i = 0; i < counter; i++) {
            if(N == 0) break;
            pq.add(new SimSa(Times[i], (long) Times[i]));
            N--;
        }

        Long time = 0L;
        while(true) {

            List<Integer> end = new ArrayList<>();
            time = pq.peek().endTime;

            while(!pq.isEmpty() && pq.peek().endTime == time ) {
                end.add(pq.peek().num);
                pq.poll();
            }

            Collections.sort(end);

            for(int i = 0; i < end.size(); i++) {
                if(N == 0) break;
                pq.add(new SimSa(end.get(i), (time+ end.get(i))));
                N--;
            }


            if(pq.isEmpty()) {
                break;
            }
        }

        answer = time;

        return answer;
    }
}