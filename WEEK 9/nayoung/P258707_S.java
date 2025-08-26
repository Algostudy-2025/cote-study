import java.util.*;

//그리디로 풀이 (GPT...)
//코인 최대한 안쓰는 순서로 그리디 진행
class Solution {
    static int n;

    public int solution(int coin, int[] cards) {
        n = cards.length;

        // 초기 hand: 앞의 n/3 장
        Set<Integer> hand = new HashSet<>();
        for (int i = 0; i < n / 3; i++)
            hand.add(cards[i]);

        // 대기 풀(wait) : 라운드마다 공개되는 카드 선택하기 전 대기 장소
        Set<Integer> wait = new HashSet<>();

        int idx = n / 3; // 다음 공개 카드 인덱스
        int round = 1;

        while (true) {
            // 이번 라운드 공개 2장 -> wait에 누적 (덱 소진 시 패스)
            if (idx < n)
                wait.add(cards[idx++]);
            if (idx < n)
                wait.add(cards[idx++]);

            // 우선순위1) hand + hand (코인 0)
            if (consumePairSameSet(hand)) {
                round++;
                continue;
            }

            // 우선순위2) hand + wait (코인 1)
            if (coin >= 1 && consumePairCross(hand, wait)) {
                coin -= 1;
                round++;
                continue;
            }

            // 우선순위3) wait + wait (코인 2)
            if (coin >= 2 && consumePairSameSet(wait)) {
                coin -= 2;
                round++;
                continue;
            }

            // 어떤 방법으로도 페어를 못 만들면 종료
            break;
        }
        return round;
    }

    // 같은 Set 안에서 (x, n+1-x) 한 쌍 찾고 실제 제거
    static boolean consumePairSameSet(Set<Integer> s) {
        // 스냅샷으로 순회(ConcurrentModification 방지)
        // => new ArrayList<>(s)로 **현재 시점의 복사본(스냅샷)**을 만들어서, 그 복사본을 순회하면서 실제 제거는 원본 s에서
        // 수행한다!!
        for (int x : new ArrayList<>(s)) {
            int y = n + 1 - x;
            if (y == x)
                continue; // 덱 값은 유니크가 사실상 불필요
            if (s.contains(y)) {
                s.remove(x);
                s.remove(y);
                return true;
            }
        }
        return false;
    }

    // 서로 다른 Set(hand, wait)에서 (x in hand, y in wait)로 한 쌍을 찾아 제거
    static boolean consumePairCross(Set<Integer> hand, Set<Integer> wait) {
        for (int x : new ArrayList<>(hand)) {
            int y = n + 1 - x;
            if (y == x)
                continue; // 유니크 덱이라 사실상 불필요
            if (wait.contains(y)) {
                hand.remove(x);
                wait.remove(y);
                return true;
            }
        }
        return false;
    }
}