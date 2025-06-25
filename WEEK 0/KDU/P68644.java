/*

    numbers 배열을 이중 for문으로 돌면서 전체 가능한 수를 set에 넣어주면 자동으로 중복 제거됨.
    Set에 넣은 값들을 직접 정렬이나 인덱싱된 결과를 다루기 어렵기 때문에, List로 옮겨서 후처리를 함.
    이후 배열로 return해야하기 때문에 list->array 작업을 한번 더 진행함.

    시간복잡도 : O(n^2)
    -> numbers 배열의 길이가 100이하 이므로 문제 X

    채점 결과
    정확성: 100.0
    합계: 100.0 / 100.0
*/


import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        // 중복되는 값은 제외하므로 set이 더 적합함.
        Set<Integer> set = new HashSet<>();
        
        
        for(int i = 0; i < numbers.length; i++){
            for(int j = i + 1; j < numbers.length; j++){
                int sum = numbers[i]+numbers[j];
                set.add(sum);
            }
        }
        
        // 집합 자체로는 사용할 수 없으므로 리스트로 변환
        LinkedList<Integer> list = new LinkedList<>(set);
        Collections.sort(list);
        
        // 리스트 -> 배열
        int[] arr = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            arr[i] = list.get(i);
        }
        
        return arr;
    }
}