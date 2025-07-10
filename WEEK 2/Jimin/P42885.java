import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int a = 0;
        int b = people.length - 1;

        Arrays.sort(people);

        while(a <= b) {
            if(people[a] + people[b] <= limit) a++;
            b--;
            answer++;
        }
        return answer;
    }
}