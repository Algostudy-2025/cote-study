class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length; 

        boolean[] mycards = new boolean[n+1];   // 내가 가진 카드
        boolean[] newcards = new boolean[n+1];  // 새 카드 저장용

        // 처음에 n/3 장을 손패에 넣음
        for (int i = 0; i < n/3; i++) {
            mycards[cards[i]] = true;
        }

        int life = 0;     // 현재 사용할 수 있는 짝 수
        int templife = 0; // 후보 짝 수 (코인 2개로 살릴 수 있음)

        // 처음 손패에서 life 계산 (짝 = a + b = n+1)
        for (int i = 1; i <= n/2; i++) {
            if (mycards[i] && mycards[n+1 - i]) life++;
        }

        // 턴 진행
        for (int turn = 1; turn <= n/3 + 1; turn++) {
            if (turn == n/3 + 1) return turn; // 마지막 턴까지 생존하면 성공

            // 이번 턴에서 새로 뒤집은 카드 2장
            int card1 = cards[n/3 + 2*(turn-1)];
            int card2 = cards[n/3 + 2*(turn-1) + 1];

            // 새 카드가 손패와 짝이면 → 코인 1개로 life 확보
            if (mycards[n+1 - card1] && coin > 0) {
                coin--;
                life++;
            }
            if (mycards[n+1 - card2] && coin > 0) {
                coin--;
                life++;
            }

            // 새 카드끼리 짝인지 확인
            if (newcards[n+1 - card1]) templife++;
            else newcards[card1] = true;

            if (newcards[n+1 - card2]) templife++;
            else newcards[card2] = true;

            // life가 0인데 후보 짝(templife) 있고, 코인 2개 이상 있으면 꺼내 쓰기
            if (life == 0 && coin >= 2 && templife > 0) {
                templife--;
                coin -= 2;
                life++;
            }

            // 여전히 life 0이면 끝
            if (life == 0) return turn;

            // 턴 종료 → life 하나 소모
            life--;
        }

        return -1; // 여기까지 오면 비정상
    }
}
