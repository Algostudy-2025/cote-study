/**
 * P42748. K번째 수
 * i번째부터 j번째까지의 수를 오름차순 정렬하고 k 번째 수를 추출하는 메서드 생성
 * commands 배열의 길이만큼 반복문을 돌면서 해당 메서드를 호출
 * System.arraycopy,Arrays.copyOfRange를 사용하여 배열을 복사하는 방법도 있음
 * 시간복잡도는 O(nlogn)으로 충분히 시간내에 해결 가능
 */

import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for(int i=0;i<commands.length;i++){
            answer[i] = calculate(array,commands[i]);
        }
        return answer;
    }
    public int calculate(int[] array,int[] command){
        int start = command[0]-1;
        int end = command[1]-1;
        int idx = command[2]-1;
        int[] temp = new int[end-start+1];
        int index = 0;
        for(int i = start; i<=end; i++){
            temp[index++]=array[i];
        }
        Arrays.sort(temp);
        return temp[idx];
    }
}