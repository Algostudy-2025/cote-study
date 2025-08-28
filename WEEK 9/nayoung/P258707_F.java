import java.util.*;

//백트래킹 이용해보려고 했는데 결국 못품... 근데 어디서 잘못된건지 모르겠음.

//알게된 점 : ArrayList remove() 메서드는 두가지 버전이 있다. (1) 인덱스로 삭제, (2) 객체 값 자체를 삭제 (boolean 리턴)
//-> 참고: https://hianna.tistory.com/564, https://nuheajiohc.tistory.com/59
class Solution {
    static int n, answer;

    public int solution(int coin, int[] cards) {
        n = cards.length;
        answer = 0;

        ArrayList<Integer> hand = new ArrayList<>();
        for (int i = 0; i < n / 3; i++)
            hand.add(cards[i]);

        backTracking(hand, 1, coin, n / 3, cards);
        return answer;
    }

    // hand: 현재 손에 있는 패
    // round: 현재까지 성공한 라운드 수
    // coin: 남은 코인
    // idx: 이번 라운드에서 공개할 첫 카드 인덱스 (idx, idx+1)
    static void backTracking(ArrayList<Integer> hand, int round, int coin, int idx, int[] cards) {
        if (idx >= n) { // 더 이상 공개할 카드가 없으면 라운드 종료
            answer = Math.max(answer, round);
            return;
        }
        // 각 분기: 이번 라운드에서 공개되는 두 장(cards[idx], cards[idx+1])을
        // (1) 둘 다 버림 (2) 첫 장만 획득 (3) 둘째만 획득 (4) 둘 다 획득
        // → 그런 다음, "그 손패"로 페어 1쌍을 실제로 소모하고 다음 라운드로.
        // 분기마다 hand를 수정했다가 반드시 롤백!

        // 1) 둘 다 버림
        tryNextRoundAfterConsumingPair(hand, round, coin, idx, cards);
        // 2) 첫 장만 획득
        if (coin >= 1 && idx < n) {
            hand.add(cards[idx]);
            tryNextRoundAfterConsumingPair(hand, round, coin - 1, idx, cards);
            hand.remove(hand.size() - 1); // 롤백
        }

        // 3) 둘째만 획득
        if (coin >= 1 && idx + 1 < n) {
            hand.add(cards[idx + 1]);
            tryNextRoundAfterConsumingPair(hand, round, coin - 1, idx, cards);
            hand.remove(hand.size() - 1); // 롤백
        }

        // 4) 둘 다 획득
        if (coin >= 2 && idx + 1 < n) {
            hand.add(cards[idx]);
            hand.add(cards[idx + 1]);
            tryNextRoundAfterConsumingPair(hand, round, coin - 2, idx, cards);
            hand.remove(hand.size() - 1); // 롤백(둘째)
            hand.remove(hand.size() - 1); // 롤백(첫째)
        }
    }

    // 분기(버림/획득)까지 적용된 "현재 손패"에서, 페어 한쌍을 실제 제거한 뒤 다음 라운드로 재귀
    static void tryNextRoundAfterConsumingPair(ArrayList<Integer> hand, int round, int coin, int idx, int[] cards) {
        // 현재 손패에서 (x,y) 합이 n+1인 한 쌍 찾기
        int[] pair = findPairValues(hand);
        if (pair == null) { // 결과가 null이면 게임 종료
            answer = Math.max(answer, round);
            return;
        }

        // 페어 실제 소모 (값 기반 제거: Integer.valueOf로 안전 제거)
        hand.remove(Integer.valueOf(pair[0]));
        hand.remove(Integer.valueOf(pair[1]));

        // 다음 라운드로~!
        backTracking(hand, round + 1, coin, idx + 2, cards);

        // 롤백: 제거했던 두 장 복구
        hand.add(pair[0]);
        hand.add(pair[1]);
    }

    static int[] findPairValues(ArrayList<Integer> hand) {
        // resize가 발생하지 않도록 여유 버킷을 미리 확보
        HashSet<Integer> set = new HashSet<>(hand.size() * 2);
        for (int v : hand)
            set.add(v);

        for (int v : hand) {
            int need = n + 1 - v; // 합이 n+1이 되기 위해 필요한 값
            if (need == v)
                continue; // 유니크 덱이면 사실상 불필요
            if (set.contains(need)) {
                return new int[] { v, need };
            }
        }
        return null;
    }

}