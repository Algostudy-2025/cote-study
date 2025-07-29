/* P159368 이모티콘 할인행사
 * https://school.programmers.co.kr/learn/courses/30/lessons/150368
 * 백트래킹 잘 기억했으면 풀만했다!
 */

class Solution {
    
    static int[] percent = {10, 20, 30, 40};
    
    static int[][] user; //전역변수로 변환용
    static int[] emoticon; //22
    
    static int[] discountPercents; //중복 순열
    static int userCount; //유저 몇명?
    static int emoticonsCount; //이모티콘 몇개?
    static int maxPeople; //최대 몇명
    static int maxSales; //최대 얼마
    
    public int[] solution(int[][] users, int[] emoticons) {
        
        user = users;
        emoticon = emoticons;
        //입력값 전역으로 변환
        
        userCount = users.length;
        emoticonsCount = emoticons.length;
        //안해도 괜찮은데 편의상 했습니다
        
        
        maxPeople = 0;
        maxSales = 0;
        //최대 몇명, 얼마 = 답
        
        discountPercents = new int[emoticonsCount]; //중복 순열 만들기 위한 배열
        
        bt(0);//백트래킹
        
        int[] answer = new int[]{maxPeople, maxSales}; //정답
        
        return answer;
    }
    
    public static void bt(int idx) {
        if(idx >= emoticonsCount) { //백트래킹 종료조건 
            calculate(); 
            return;
        }
        
        for(int i = 0; i < 4; i++) {
            discountPercents[idx] = percent[i]; //10-40중 하나 선택해서 배열의 현재 넣어야할 인덱스 값에 넣어줌
            bt(idx+1);
            //배열은 덮어쓰기 때문에 list처럼 빼주는 작업 안해줘도 괜찮
            //중복 순열이기때문에 방문처리도 X
        }
    }
    
    public static void calculate() {
        
        int people = 0; //지금 몇명
        int totalSum = 0; //지금 얼마
        
        for(int i = 0; i < userCount; i++) {//유저의 수만큼
            int[] info = user[i]; //유저 정보
            int buyPer = info[0]; //할인율
            int plusBuy = info[1]; //넘어가면 플러스 구독
            
            int sum = 0;
            for(int j = 0; j < emoticonsCount; j++) { //이모티콘의 갯수만큼 반복
                if(discountPercents[j] >= buyPer) { //할인율이 기준 이상일경우
                    sum += emoticon[j] * (1 - 0.01 * discountPercents[j]); //즉시 구매 
                }
            } 
            if(sum >= plusBuy) { //이모티콘 한바퀴 다 돌리고 기준금액보다 높으면 이모티콘 플러스 구독
                people++;
                continue;
            }
            totalSum += sum; //앞에 if문을 못들어가면 그냥 구매한걸로
        }
        
        if(people > maxPeople) { //최고 기록보다 많이 구매한경우
            maxPeople = people;
            maxSales = totalSum;
        } else if(people == maxPeople) { //같은경우는 금액기준
            maxSales = Math.max(totalSum,maxSales);
        }
    }
}