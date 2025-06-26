import java.util.*;

/* P68644 두 개 뽑아서 더하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/68644
 */
class Solution {
    public int[] solution(int[] numbers) {
        
    	//주어진 값이 0~100 이하 0~200까지 나올 수 있음
        boolean[] isSum = new boolean[201];
        
        Arrays.sort(numbers); //값을 정렬함
        
        //완전 탐색 그냥 다 더해줌
        for(int i = 0; i < numbers.length; i++) {
            for(int j = 0; j < numbers.length; j++) { //여기 j = i+1 로 최적화 가능
                if(i != j) { //스스로 더하는거만 빼고
                    int sum = numbers[i] + numbers[j];
                    isSum[sum] = true;
                }
            }
        }
        int count = 0; //true인 값만 세줌
        for(int i = 0; i < 201; i++) {
            if(isSum[i]) {
                count++;
            }
        }
        
        int[] ans = new int[count]; //true였던 만큼 배열 길이잡고 생성
        
        int idx = 0; //몇번째 인덱스에 들어가야하는지
        for(int i = 0; i < 201; i++) { //true인 값을 찾아서 정답 배열에 넣어
            if(isSum[i]) {
                ans[idx++] = i;
            }
        }
        
        return ans;
        
  
        
    }
}