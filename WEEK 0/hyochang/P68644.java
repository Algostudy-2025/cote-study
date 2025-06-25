/**
 * P68644. 두 개 뽑아서 더하기
 * 2이상 100이하이므로 100*100 = 10000이므로 시간내에 해결가능
 * 중복된 값이 없어야함-> Set
 * 오름차순 정렬 -> TreeSet
 * solution의 리턴타입을 바꾸어서 제출하여도 통과 가능
import java.util.*;

class Solution {
    public Set<Integer> solution(int[] numbers) {
        Set<Integer> combination = new TreeSet();
        for(int i=0;i < numbers.length; i++){
            for(int j=i+1; j<numbers.length; j++){
                combination.add(numbers[i]+numbers[j]);
            }
        }
        return combination;
    }
}
 */
import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = {};
        Set<Integer> combination = new TreeSet();
        for(int i=0;i < numbers.length; i++){
            for(int j=i+1; j<numbers.length; j++){
                combination.add(numbers[i]+numbers[j]);
            }
        }
        answer = new int[combination.size()];
        int index = 0;
        for(int num : combination) {
            answer[index++] = num;
        }
        return answer;
    }
}