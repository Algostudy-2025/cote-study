import java.util.*;

class Solution {

  static Deque<Integer> deque;
  public int solution(int[] players, int m, int k) {
  
  int active = 0;
  Queue<Integer> expireQueue = new LinkedList<>();
  
  for (int i = 0; i < players.length; i++) {
    while (!expireQueue.isEmpty() && expireQueue.peek() == i) {
        expireQueue.poll();
        active--;
    }

    int need = players[i] / m;
    while (active < need) {
        answer++;
        expireQueue.add(i + k);
        active++;
    }
  }
}
