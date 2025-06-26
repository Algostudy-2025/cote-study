import java.util.*;

/* P42748 K번째수
 * https://school.programmers.co.kr/learn/courses/30/lessons/42748
 */
class Solution {
	public int[] solution(int[] array, int[][] commands) {

		int t = commands.length; // 명령의 개수

		int[] ans = new int[t]; // 정답 배열

		for (int idx = 0; idx < t; idx++) { 
			
			int start = commands[idx][0] - 1; // 주어진 값은 1 base 라서 -1 해줌
			int end = commands[idx][1] - 1;
			int ansIdx = commands[idx][2] - 1;

			int[] nums = new int[end - start + 1]; //잘라낼 배열

			int numsidx = 0; 
			for (int i = start; i <= end; i++) { //복사
				nums[numsidx++] = array[i];
			}

			Arrays.sort(nums); //정렬

			ans[idx] = nums[ansIdx]; //정답 배열에 넣기
		}

		return ans;

	}
}