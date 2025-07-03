package changmin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P42586 {
	public static void main(String[] args) {
		
		int[] progresses = {95, 90, 99, 99, 80, 99};
		int[] speeds = {1, 1, 1, 1, 1, 1};
		
		int[] ans = Solution(progresses, speeds);
		
		System.out.println(Arrays.toString(ans));
	}
	
	public static int[] Solution(int[] p, int[] s) {
		
		int count = p.length; //작업의 개수
		
		int sc = 0; //완료한 작업의 수
		List<Integer> lans = new ArrayList<>(); //배포할때 몇개 하는지
		
		int idx = 0; //몇번째 작업하는지
		while(sc < count) {
			
			for(int i = 0; i < count; i++) { //하루치 작업 +
				p[i] += s[i];
			}
			
			int todayCount = 0; //오늘 몇개 했나용
			
			while(true) {
				if(p[idx] < 100) break; //제일 앞에 있는 작업 안끝났으면 다음날로
				
				todayCount++; //완료했으면 오늘 작업 카운트++, 다음 작업으로 idx++	
				idx++;
				
				if(idx >= count) break; //작업 다했으면 끝
			}
			
			if(todayCount != 0) { //오늘 작업한게 있으면 리스트에 추가, 완료갯수 카운트
				lans.add(todayCount); 
				sc+= todayCount;
			}
		}
		
		//리스트를 배열로
		int[] ans = new int[lans.size()];
		for(int i = 0; i < lans.size(); i++) {
			ans[i] = lans.get(i);
		}
	
		return ans;
	}
	
}
