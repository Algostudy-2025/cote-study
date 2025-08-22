import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {

        int N = cards.length;   // 카드 개수

        Set<Integer> hand = new HashSet<>(); // 손에 든 카드
        Set<Integer> temp = new HashSet<>(); // 아직 구매하지 않은 카드

        // 초기 카드 드로우
        for (int i = 0; i < N / 3; i++)
            hand.add(cards[i]);

        int index = N / 3;  // 맨 윗장 카드의 인덱스
        int round = 1;      // 라운드

        // 게임 진행
        while (index < N) {
            boolean next = false; // 다음 라운드로 넘어갈 수 있는지를 판단

            // 카드 2장 드로우
            for (int i = 0; i < 2; i++) {
                int draw = cards[index++];
                // 만약 카드의 쌍이 손 안에 있으면 구매
                if (hand.contains(N + 1 - draw) && coin >= 1) {
                    hand.add(draw);
                    coin--;
                } else {
                    temp.add(draw);
                }
            }

            // 카드 제출
            for (Integer card : hand) {
                if (hand.contains(N + 1 - card)) {
                    hand.remove(card);
                    hand.remove(N + 1 - card);
                    next = true;
                    break;
                }
            }

            // 구매하지 않았던 카드에서 제출 가능한지 확인
            if (!next && coin >= 2) {
                for (Integer card : temp) {
                    if (temp.contains(N + 1 - card)) {
                        temp.remove(card);
                        temp.remove(N + 1 - card);
                        coin -= 2;
                        next = true;
                        break;
                    }
                }
            }

            if (!next) break;
            round++;
        }

        return round;
    }
}