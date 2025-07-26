/*

    중복 순열을 써야 한다는 건 금방 알았으나
    정작 그 방법을 잊어버린 것이다...
    덕분에 재활 성공했습니다...

 */

import java.util.*;

class Solution {

    private static int[][] users;
    private static int[] emoticons;

    private static int[] rates = new int[]{10, 20, 30, 40};
    private static int[] discounts;
    private static int[] answer;
    private static int N;

    public int[] solution(int[][] users, int[] emoticons) {

        Solution.users = users;
        Solution.emoticons = emoticons;
        N = emoticons.length;

        answer = new int[2];
        discounts = new int[N];
        
        perm(0);
        return answer;
    }

    // 중복 순열로 이모티콘별 할인율 결정
    private void perm(int depth) {
        if (depth == N) {
            int[] temp = getTotalSales(discounts);
            // 플러스 가입자가 많음 or 플러스 가입자는 같으나 구매액이 높으면 answer 덮어쓰기
            if (temp[0] > answer[0] || (temp[0] == answer[0] && temp[1] > answer[1]))
                answer = temp;
            return;
        }

        for (int i = 0; i < rates.length; i++) {
            discounts[depth] = rates[i];
            perm(depth + 1);
        }
    }

    // 할인율에 따른 총 판매액 계산
    private int[] getTotalSales(int[] discount) {
        int members = 0;
        int sales = 0;

        for (int[] user : users) {  //각 유저들에 대해서
            int buy = 0;
            for (int i = 0; i < emoticons.length; i++) {
                if (discount[i] >= user[0])     // 할인율이 유저의 기준보다 크거나 같으면
                    buy += emoticons[i] / 100 * (100 - discount[i]);    // 이모티콘 구매
            }

            if (buy >= user[1])     // 구매액이 예산 이상이면
                members++;          // 플러스 가입
            else                    // 그렇지 않으면
                sales += buy;       // 구매액 증가
        }

        int[] result = {members, sales};
        return result;
    }
}