import java.util.*;

class Solution {
    /*
     * 
     * coin -> 동전 개수
     * n = cards.length -> 카드 개수
     * cards -> 카드 순서 (1 ~ n 까지 숫자 범위)
     * 
     * 처음에 n/3장 들고있음
     * 다음 라운드로 가기 위한 합 n+1
     * 
     * 최대 라운드는?
     * 
     * 손패 관리
     * 뽑은 카드 관리(코인관리)
     * 합 관리
     * 
     * 시작
     * 
     * 뽑으면 어떤 카드를 가져갈지 선택(둘다, 하나만(1,2) 둘다버림) 최대 4개
     * 
     * 숫자합이 n+1이 되는 조합을 모두 만듬
     * 
     * 그 중 한개의 조합을 제거
     * 
     * 백트래킹으로 다시 처음으로 돌아가서.. 반복
     * 
     * 주의 사항
     * 코인 개수 관리
     * 들고 있는 카드 관리
     * 
     */
    static class Card implements Comparable<Card> {
        int num, idx;

        Card(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }

        @Override
        public int compareTo(Card o) {
            return o.idx - idx;
        }
    }

    static int N; // 카드의 수
    static int[] cards;
    static int coin;
    static int maxRound;

    public int solution(int inputCoin, int[] inputCards) {

        maxRound = 1;
        N = inputCards.length;
        cards = inputCards;
        coin = inputCoin;

        List<Integer> hands = new ArrayList<>();

        for (int i = 0; i < N / 3; i++) {
            hands.add(cards[i]);
        }
        play(1, hands);

        return maxRound;
    }

    public static void play(int round, List<Integer> hands) {
        maxRound = Math.max(maxRound, round);

        if (N < N / 3 + round * 2) {
            maxRound = Math.max(maxRound, round);
            return;
        }

        List<Integer>[] selectCards = select(round); // 뽑을 수 있는 카드가 없는경우 종료 로직을 만들어야함

        for (List<Integer> selectCard : selectCards) {
            if (coin - selectCard.size() < 0) { // 코인이 없을 경우
                continue;
            }
            int selectCardSize = selectCard.size();
            coin -= selectCardSize;

            // 선택한 카드를 핸드에 추가하고
            int handSize = hands.size();
            List<Card> ifCard = new ArrayList<>();
            for (int addNum : selectCard) {
                ifCard.add(new Card(addNum, hands.size()));
                hands.add(addNum);
            }

            List<int[]> combs = makeComb(hands);
            for (int[] comb : combs) {
                // 합쳐서 n이 되는 숫자들을 제거
                List<Card> removeCard = new ArrayList<>();
                List<Card> addCard = new ArrayList<>();

                for (int i = 0; i < hands.size(); i++) {
                    if (hands.get(i) == comb[0] || hands.get(i) == comb[1]) {
                        removeCard.add(new Card(hands.get(i), i));
                        addCard.add(new Card(hands.get(i), -i));
                    }
                }

                Collections.sort(removeCard);
                Collections.sort(addCard);
                // 큰숫자 부터 제거해야 인덱스 오류 안남
                for (Card c : removeCard) {
                    hands.remove(c.idx);
                }
                play(round + 1, hands);

                // 다시 그 숫자들 추가 (작은 숫자부터 넣어줘야함)
                for (Card c : addCard) {
                    hands.add(-c.idx, c.num);
                }
            }
            // 핸드에 넣었던 카드 제거
            Collections.sort(ifCard);
            for (Card c : ifCard) {
                hands.remove(c.idx);
            }

            coin += selectCardSize;
        }
    }

    public static List<Integer>[] select(int round) { // 사용할 카드 선택
        List<Integer>[] comb = new ArrayList[4];
        int num1 = cards[N / 3 + (2 * round - 2)];
        int num2 = cards[N / 3 + (2 * round - 1)];

        for (int i = 0; i < 4; i++) {
            comb[i] = new ArrayList<>();
        }

        comb[0].add(num1);
        comb[1].add(num2);
        comb[2].add(num1);
        comb[2].add(num2);

        return comb;
    }

    public static List<int[]> makeComb(List<Integer> hands) { // 더해서 n+1 이 되는 숫자들의 조합

        List<int[]> comb = new ArrayList<>();
        Set<String> seen = new HashSet<>();

        for (int num1 : hands) {
            for (int num2 : hands) {
                if (num1 == num2) {
                    continue;
                }
                if (num1 + num2 == N + 1) {
                    int x = Math.min(num1, num2);
                    int y = Math.max(num1, num2);
                    String key = x + " : " + y;
                    if (seen.add(key)) {
                        comb.add(new int[] { x, y }); // 이렇게 하면 num2, num1로도 들어갈 수 있음 그러니까 중복 검사 함 해야함 or set을 사용해서 중복
                                                      // 제거
                    }
                }
            }
        }

        return comb;
    }

}