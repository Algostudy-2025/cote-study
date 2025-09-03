import java.util.*;

/*
    우선순위 큐를 만들어서 작은 애들부터 뽑자! 뽑은 애들을 보고
    1. 만약 큐에 넣고 큐의 모든 값들을 더했을 때 weight를 초과하지 않으면 큐에 넣고
    2. weight를 초과하면 큐에 넣지 않고 기존에 큐에 있던 값들을 모두 제거함
    
    우선순위 큐 안써도 되네..
*/

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Deque<Integer> wait = new ArrayDeque<>();
        Deque<Integer> bridge = new ArrayDeque<>();
        // 대기 트럭 큐에 truck_weights 배열 값들 추가
        for(int num : truck_weights){
            wait.add(num);
        }
        
        // while 반복문을 돌면서 bridge 큐에 wait 값을 차례대로 넣을 준비를 함
        // bridge 큐의 길이가 bridge_length를 초과하지 못하도록 조건문 추가
        // 반복문을 돌면서 큐에 들어있는 값의 합산 + wait에서 빼는 하나의 합이 weight를 초과하면
        // bridge 큐에 있는 처음 값을 빼고 wait에서 빼는 새로운 애를 큐에 추가함
        // 돌릴때마다 count 세기
        
        int count = 0;
        while(true){
            count++;
            if(wait.isEmpty() && bridge.isEmpty()) break;
            int temp = 0;
            if(!wait.isEmpty())
                temp = wait.poll();
            int sum = 0;
            for(int n : bridge){
                sum += n;
            }
            if(bridge.size() < bridge_length){ // 다리에 제한 길이까지만 받음
                if(sum + temp > weight){ 
                    // bridge 큐의 합 + temp가 weight를 넘는 경우 -> 
                    // bridge 맨앞에 있는 값을 없애고 temp를 큐의 제일 뒤에 넣음
                    bridge.pollFirst();
                    bridge.addLast(temp);
                }else{ // 넘지 않으면 bridge 큐에 저장(addLast)
                    bridge.addLast(temp);
                }
            }
        }
        
        return count;
    }
}