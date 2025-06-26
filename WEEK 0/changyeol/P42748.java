/*
각 command를 반복

    1. 배열을 자른다? 복사해서 새로운 배열을 만들자.
    Arrays.copyOfRange(array, start, end)
    -> array의 start부터 end까지 복사(start는 포함, end는 미포함)

    2. 정렬
    Arrays.sort

    3. 최종 결과를 담을 배열(answer)을 만들고, 앞에서 정렬한 배열의 k번째 숫자를 담음

반복이 끝나면 최종 결과를 리턴.



채점 결과
정확성: 100.0
합계: 100.0 / 100.0
*/



import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        
        //각 command 반복
        for(int n = 0; n < commands.length; n++){
            int i = commands[n][0] - 1;
            int j = commands[n][1];
            int k = commands[n][2] - 1;
            
            //자르기
            int[] sliced = Arrays.copyOfRange(array, i, j);
        
            //정렬
            Arrays.sort(sliced);
            
            //최종 결과를 담을 배열
            int[] answer = new int[commands.length];
        
            //정렬된 배열의 k번째 숫자를 answer 배열에 저장
            answer[n] = sliced[k];
        }
        
        return answer;
        
    }
}