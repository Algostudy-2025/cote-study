import java.util.*;

/*못풀었어염....!ㅠ*/

//prev/next 배열을 이용해 이중연결리스트의 느낌으로 만들어야 한다...!!
//표의 범위를 벗어나는 이동은 입력으로 주어지지 않습니다.
//원래대로 복구할 행이 없을 때(즉, 삭제된 행이 없을 때) "Z"가 명령어로 주어지는 경우는 없습니다.
class Solution {
    public String solution(int n, int k, String[] cmd) {
         // ArrayList<Integer> arrList = new ArrayList();   //시간초과(삽입/삭제 시 데이터 이동이 필요한 경우 추가시간 발생)
        // LinkedList<Integer> arrList = new LinkedList(); //시간초과(조회 시 불리)
        TreeSet<Integer> set = new TreeSet<Integer>();
        Stack<int[]> trash = new Stack();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            set.add(i);
        }
        int beforeVal=0, beforeIdx=0;
        for(int i=0;i<cmd.length;i++){
            String[] tmp = cmd[i].split(" ");
            switch(tmp[0]){
                case "U":
                    k -= Integer.parseInt(tmp[1]);
                    break;
                case "D":
                    k += Integer.parseInt(tmp[1]);
                    break;
                case "C":
                    beforeVal = set.remove(k);
                    beforeIdx = k;
                    trash.push(new int[]{beforeIdx, beforeVal});
                    if(k==set.size()) k--; //삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택
                    break;
                case "Z":
                    int[] top = trash.pop();
                    set.add(top[0], top[1]);
                    if(top[0]<=k) k++; //현재 선택된 행은 바뀌지 않는 점에 주의
                    break;
            }            
        }
        
        boolean[] arr = new boolean[n];
        for(int e: set){
            arr[e] = true;
        }
        
        for(int i=0;i<n;i++){
            if(arr[i]) sb.append("O");
            else sb.append("X");
        }
        
        String answer = sb.toString();
        return answer;
    }
}