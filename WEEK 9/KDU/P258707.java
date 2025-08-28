import java.util.*;

/*
    1. 처음 시작하는 카드 뭉치에서 N+1을 만든다.
    2. 1에서 불가능하다면, 추가적으로 뽑은 카드 중 1개를 사용하여 N+1을 만든다.
    3. 2에서 불가능하다면, 추가적으로 뽑은 카드 2개를 이용하여 N+1을 만든다.
    4. 3에서 불가능하다면, 다음 라운드로의 진행이 불가능하다.

    -----------------------------------
    1을 진행하기 위해서는 동전이 없어도 된다.
    2를 진행하기 위해서는 동전이 최소 1개 있어야 한다.
    3을 진행하기 위해서는 동전이 최소 2개 있어야 한다.
    
    굳이 큐를 만들 필요 없이 int 배열에 포인터를 사용하면 될듯
    
    음.. 근데 생각해보니까 hashset을 사용하면 굳이 인덱스로 불편하게 접근할 필요 없이
    그냥 contains를 사용하면 될듯..??
    (if(set.contains(n+1-x)))
    
*/

/*
 * 그냥 위에 풀이 방법 알기 전까지는 접근조차 못한듯...
 * 큐 -> int 배열 -> hashset으로 생각하기까지 시간이 좀 걸림
 * 
 * ※ 그리디 문제를 접근하는 방법 for Gemini
 * 요약: 빠른 접근을 위한 팁
 * 목표와 자원을 명확히 하세요: "무엇을 최대로 만들고 싶은가?", "가장 제한적인 자원은 무엇인가?" (이 문제에서는 라운드, 동전)
 * 나의 행동과 비용을 분류하세요: 내가 할 수 있는 모든 행동을 나열하고 각각의 비용을 계산해 보세요.
 * 비용이 낮은 순서대로 우선순위를 정하세요: 제한된 자원을 아끼는 방향으로 행동의 우선순위를 정하면, 그것이 바로 그리디 전략의 핵심이 됩니다.
 */

class Solution {
    Set<Integer> myCard,myHand;
    public int solution(int coin, int[] cards) {
        myCard = new HashSet();
        myHand = new HashSet();
        int n = cards.length;
        // 3,6,7,2 넣음
        for(int i = 0; i < n/3; i++){
            myCard.add(cards[i]);
        }
        
        // 전체 조건 시작
        int cycle = 1;
        int idx = n / 3;
        while(true){
            
            // 카드를 2장이상 뽑지 못하면 break
            if(idx >= n) break;
            
            // 내손에 있는 카드 2개를 추가
            // idx+1이 n보다 작은지를 체크해줘야됨
            myHand.add(cards[idx]);
            myHand.add(cards[idx+1]);
            
            idx += 2;
            
            boolean flag = false;
            // 1. 처음 시작하는 카드 뭉치에서 N+1을 만들어보기
            for(int num : myCard){
                // 만약 처음 카드 뭉치에서 N+1이 가능하다면 둘다 제거
                if(myCard.contains(n+1-num)){
                    myCard.remove(num);
                    myCard.remove(n+1-num);
                    //사이클 추가
                    cycle++;
                    flag = true;
                    break;
                }
            }
            
            // 2. 1에서 불가능하다면, 추가적으로 뽑은 카드 중 1개를 사용하여 N+1을 만든다.
            // 이렇게 되면 코인은 1개쓰고, myCard 반복문을 돌면서 myHand가 n+1-num을 가지고 있는지
            // 체크해보고 myCard 한장, myHand 한장 제거
            
            // 단, 동전이 1개이상 있어야 가능함
            if(!flag){
                if(coin > 0){
                    for(int num : myCard){
                        if(myHand.contains(n+1-num)){
                            myCard.remove(num);
                            myHand.remove(n+1-num);
                            --coin;
                            //사이클 추가
                            cycle++;
                            flag = true;
                            break;
                        }
                    }
                }
            }
            
            // 3. 2에서 불가능하다면, 추가적으로 뽑은 카드 2개를 이용하여 N+1을 만든다.
            // 이때는 코인은 2개쓰고, myHand가 가지고 있는 2장을 모두 제거?
            if(!flag){
                if(coin >= 2){
                    for(int num : myHand){
                        if(myHand.contains(n+1-num)){
                            myHand.remove(num);
                            myHand.remove(n+1-num);
                            coin -= 2;
                            //사이클 추가
                            cycle++;
                            flag = true;
                            break;
                        }
                    }
                }
            }
            
            // 4. 3에서 불가능하다면, 다음 라운드로의 진행이 불가능하다.
            if(!flag) break;
            
        }
        return cycle;
    }
}