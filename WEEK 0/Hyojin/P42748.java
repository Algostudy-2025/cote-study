import java.util.*;

/*
command의 갯수 확인 후 횟수만큼 반복하며 조건에 맞는 값을 찾음
*/

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        
        //command 갯수
       int count = commands.length; 
    
        //command 갯수만큼의 정답 배열 생성
        int[] result = new int[count];
        
        
        //command 횟수만큼 반복
        for(int i = 0; i < count; i++){
            
            //index니까 -1만큼 해줌
            int first = commands[i][0] - 1;
            int last = commands[i][1] - 1;
            int loc = commands[i][2] - 1;
            
            //잘라진 배열을 저장할 새로운 배열 cut
            int[] cut = new int[last-first+1];
            
            //잘라진 배열 저장
            int idx = 0;
            for(int j = first; j <= last ; j++){
                cut[idx] = array[j];
                idx++;
            }
            
            //잘라진 배열 정렬
            Arrays.sort(cut);
                
            //k번째에 있는 수 구하여 정답 배열에 넣음
            result[i] = cut[loc];
            
        }
        
        //출력
        return result;
    
    }
}