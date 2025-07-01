import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {

        int[] answer = new int[commands.length];
        int idx = 0;
        for(int[] order: commands){
            int[] newArr = Arrays.copyOfRange(array, order[0]-1, order[1]);  //to는 exclusive
            Arrays.sort(newArr);
            answer[idx++] = newArr[order[2]-1];
        }
        
        return answer;
    }
}