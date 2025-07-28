import java.util.*;

class Solution {
    static int[] discounts, res;
    static int n, m, maxPlus, maxPrice;
    
    public int[] solution(int[][] users, int[] emoticons) {
        
        discounts = new int[]{10, 20, 30, 40};
        
        n = users.length; // 사용자 수 
        m = emoticons.length; // 이모티콘 수
        maxPlus = Integer.MIN_VALUE; // 임티플 가입자 수(최대)
        maxPrice = Integer.MIN_VALUE; // 최대 판매액

        res = new int[m]; // 할인율 순열 저장 배열
        
        perm(users, emoticons, 0);
        
        int[] answer = {maxPlus, maxPrice};
        return answer;
    }
    
    static void perm(int[][] users, int[] emoticons, int depth){
        if(depth==m){
            
            int salesAmount = 0; // 이모티콘 판매액
            int cntPlus = 0; // 특정 할인율을 적용했을때 임티플 가입자 수
            
            for(int j = 0; j<n; j++){
                
                int sumPrice = 0; // 할인된 이모티콘 중 구입하는 가격의 합
                
                for(int i = 0; i<m; i++){
                    
                    if(res[i]<users[j][0]){ // 할인율이 원하는 비율보다 낮으면 continue
                        continue;
                    }
                    sumPrice += emoticons[i]*(1 - (float)res[i]/100); // 할인된 가격으로 구매
                }// for문 끝(i: 임티 순회)
                if(sumPrice>=users[j][1]){
                    cntPlus++;
                }else{
                    salesAmount += sumPrice;
                }
            }// for문 끝(j: 유저 순회)
            if(maxPlus<cntPlus){ 
                maxPlus = cntPlus;
                maxPrice = salesAmount;
            }else if(maxPlus == cntPlus){
                maxPrice = Math.max(salesAmount, maxPrice);
            }
            return;     
        }
        
        for(int i = 0; i<4; i++){
            res[depth] = discounts[i];
            perm(users, emoticons, depth+1);
        }
        
    }
}