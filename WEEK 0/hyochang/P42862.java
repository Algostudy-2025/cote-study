/**
 * P42862 체육복
 * 중복되는 학생수가 없으므로 Set(집합)을 사용해서 비교
 * 잃어버린 학생 명단에서 여벌 옷이 있는 학생 명단에 이름이 있으면 제거
 * ->lostSet에서 해결 가능한 애들 제거
 * 이제 나눠줄 수 있는 애들만 남았으므로
 * 최대한 해결을 해봄 (+-1인 번호의 애들에게 나눠줌)
 * 그 후 전체 n명에서 lostSet(여전히 옷이없는 애들)의 크기를 빼면 값이 나옴
 * 
 * boolean 배열로 해결하기
 * boolean 배열로 n개의 배열을 만들고 사용해서 해결하는 방법도 있음 
 * n개를 미리 만들어 메모리적으로 더 많은 비용을 소모한다고 생각할 수 있지만,
 * 문제 특성상 연속된 번호가 나오기 때문에
 * boolean 배열을 사용하면 더 메모리 효율적으로 해결할 수 있음
 * 만약에 번호가 연속이 아니라면 hashSet을 사용하는 것이 더 효율적일 수 있음
 */

import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        Set<Integer> lostSet = new HashSet<>();
        Set<Integer> reserveSet = new HashSet<>();
        
        for(int i =0; i<lost.length; i++){
            lostSet.add(lost[i]);
        }
        for(int i =0;i<reserve.length;i++){
            reserveSet.add(reserve[i]);
        }
        
        for(int r : reserve){
            if(lostSet.contains(r)){
                lostSet.remove(r);
                reserveSet.remove(r);
            }
        }
        
        for(int donate: reserveSet){
            int front = donate - 1;
            int back = donate + 1;
            if(lostSet.contains(front)){
                lostSet.remove(front);
            }else if(lostSet.contains(back)){
                lostSet.remove(back);
            }
        }
        answer = n- lostSet.size();
        return answer;
    }
}