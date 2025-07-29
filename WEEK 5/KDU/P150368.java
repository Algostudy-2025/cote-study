import java.util.*;
/*
    for문을 돌면서 emoticons[i]*sale[j]의 값을 누적으로 더해준다 & 플러스 가입 카운트 증가
    재귀 돌면서 중복순열 진행해주고 만약 users[][0] > sale[j]이면 누적값을 더해주지 않고
    플러스 가입 카운트만 증가시켜줌
    => 플러스 가입 카운트와 누적값은 계속 들고가되, 플러스 가입 카운트가 가장 많을때 
    누적값이 가장 큰 값을 각각 result[0], result[1]에 저장해줌
*/

/*
    생각해보니까 굳이 가격에 할인율을 곱한 값을 중복순열을 돌려고 할 필요가 없음...
    할인율 배열만 따로 가져가서 return 조건에 사용하면 됨
    그러면 return 조건은?
    => users[][1]가 discount 배열의 할인율보다 크면 이모티콘 구매 비용에 누적으로 더하지 않음...
    => users[][1] < discount[] 이렇게 되면 누적으로 더해주고 최대값 누적
*/


class Solution {
    
    static int[] sales = {10,20,30,40};
    static int price;
    static int n = sales.length;
    static int[] answer = new int[2];
    static int[] result;
    
    static void dfs(int[] discounts, int[][] users, int[] emoticons, int depth){
        // result 배열 : 중복순열 다 돌린 값을 저장한 배열(이모티콘 플러스 카운트, 할인된 가격)
        // answer 배열 : 비교할 배열
        if(depth == emoticons.length){ // return 조건
            int[] result = purchase_emoticons(discounts, users, emoticons);
            // 이모티콘 계산한 값과 비교해보면 
            // 1. 이모티콘 플러스 가입자 수가 많으면 무조건 갱신
            // 2. 같으면 값이 커야됨
            if(answer[0] < result[0]){ // 이모티콘 플러스 가입자 수가 많으면 result 배열로 카운트와 가격 모두 갱신
                answer[0] = result[0];
                answer[1] = result[1];
            }else if(answer[0] == result[0] && answer[1] < result[1]){ // 이모티콘 플러스 가입자 수가 같으면 가격만 갱신
                answer[1] = result[1];
            }
            // 이모티콘 플러스 가입자 수가 작으면 생각 X
            return;
        }
        
        // 중복순열
        // discounts 배열 : 4개의 할인율 중에 2개만 빼서 저장할 배열
        for(int i = 0; i < n; i++){
            discounts[depth] = sales[i];
            dfs(discounts, users, emoticons, depth + 1);
        }
    }

    static int[] purchase_emoticons(int[] discounts, int[][] users, int[] emoticons){
        /*
            사람별로 반복문을 돌면서 내가 가진 할인율(discount 배열)이 users의 할인율보다
            더 높을때만 계산해서 값을 누적시킴
        */
        
        int count = 0;
        int amount = 0;
        
        for(int i = 0; i < users.length; i++){
            int peoplesales = users[i][0]; // 현재 사용자가 가진 할인율
            int salesamount = users[i][1]; // 현재 사용자가 가진 이모티콘 판매액
            
            int sum = 0;
            
            for(int j = 0; j < emoticons.length; j++){
                if(peoplesales <= discounts[j]){ // 내가 가진 할인율이 더 높으면 값 더하자
                    int discountamount = emoticons[j] * (100 - discounts[j])/100;
                    sum += discountamount;
                }
            }
            
            if(sum >= salesamount){ 
                // 누적 값이 user가 가지고 있는 값보다 크면 -> 이모티콘 플러스 가입
                count++;
            } else{
                // 누적 값이 user가 가지고 있는 값보다 작으면? -> 누적값으로 더하자..
                amount += sum;
            }
        }
        
        // 계산한 값들을 result 배열에 저장하고 return
        int[] result = new int[2];
        result[0] = count;
        result[1] = amount;
        return result;
    }
    
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] discounts = new int[emoticons.length];
        dfs(discounts, users, emoticons, 0);
        return answer;
    }
}