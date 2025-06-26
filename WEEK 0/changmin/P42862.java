
/* P42862 체육복
 * https://school.programmers.co.kr/learn/courses/30/lessons/42862
 */
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
    	
        int[] uniform = new int[n+1]; //각 번호 학생이 체육복을 몇벌 들고 있는지
        
        for(int i = 1; i < n+1; i++) { // 기본 1개로 채워주고
            uniform[i] = 1;
        }
        
        for(int num : lost) { //잃어 버린 학생들 -1
            uniform[num] -= 1;
        }
        
        for(int num : reserve) { //여분있는 학생들 +1
            uniform[num] += 1;
        }
        
        //체육복이 없는 학생이 앞뒤 학생에게 빌릴 수 있는지 확인
        for(int i = 1; i < n+1; i++) { 
            if(uniform[i] == 0) { // 없는경우
                if(uniform[i-1] == 2) { //앞 학생이 더 있는경우
                    uniform[i] = 1;
                    uniform[i-1] -= 1;
                    continue;
                } else if(i+1 <= n && uniform[i+1] == 2) { //뒤 학생이 더 있는경우
                    uniform[i] = 1;
                    uniform[i+1] -= 1;
                    continue;
                }
            }
        }
        
        int ans = 0; //최댓값
        //체육복 배열을 돌면서 1이상인 경우를 찾음
        for(int i = 1; i < n+1; i++) { 
            if(uniform[i] > 0) { 
                ans++;
            }
        }
        
        return ans;
        
    }
}