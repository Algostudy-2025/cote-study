import java.util.*;
/*

P42885 구명보트
한 구명보트당 최대 2명만 탑승 가능.
덱에 내림차순 정렬한 상태로 넣고 앞뒤로 꺼낸다.
앞+뒤 합한게 무게제한보다 작으면 둘 다 뺀다 (구명보트 완성)
아니라면 가장 무거운 앞사람만 뺀다 (구명보트 완성)

정수의 내림차순 정렬하는 법에 대한 고민
-> Collections.reverseOrder()는 Wrapper 클래스만 가능하다고 하는데, 그럼 int를 무조건 Integer로 바꾸는 방법뿐인가??

정확성: 81.5
효율성: 18.5 (효율성이 똥이다... 왤까?)

*/
class P42885 {
    public int solution(int[] people, int limit) {
        int N = people.length;
        Arrays.sort(people);
        Deque<Integer> deque = new LinkedList();
        for(int i=N-1;i>=0;i--){
            deque.add(people[i]);
        }
        
        int answer = 0;
        while(!deque.isEmpty()){
            int first = deque.peekFirst();
            int last = deque.peekLast();
            if(first+last<=limit){
                deque.pollFirst();
                deque.pollLast();
                answer++;
            }else{
                deque.pollFirst();
                answer++;
            }
        }
        
        return answer;
    }
}