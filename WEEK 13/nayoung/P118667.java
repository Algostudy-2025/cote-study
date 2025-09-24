/*
- 처음에는 큐를 실제로 만들까 생각했었지만 시간복잡도 터질 것 같아 포기

- [투포인터 이용]
- 두개의 배열을 한줄로 합친 하나의 배열(arr) 만듦
- 첫번째 배열 기준으로 이 배열의 합을 목표합과 비교
- 첫번째 배열의 첫번째와 마지막에 투포인터 잡고 시작.
- 첫번째 배열합이 목표합보다 작으면 right포인터 하나 증가 (합이 커져야 하므로)
- 첫번째 배열합이 목표합보다 크면 left포인터 하나 증가 (합이 작아져야 하므로)
- 첫번째 배열합이 목표합과 같으면 break;
- 두 포인터 left, right가 모두 arr의 마지막 인덱스(2N-1)에 도달시 break.
*/
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int N = queue1.length;
        int[] arr = new int[2*N];
        long sum1 = 0;
        long sum2 = 0;
        for(int i=0;i<N;i++){
            arr[i] = queue1[i];
            arr[i+N] = queue2[i];
            sum1 += (long) queue1[i];
            sum2 += (long) queue2[i];
        }
        int left = 0;
        int right = N-1;
        long goal = (sum1+sum2)%2==0 ? (sum1+sum2)/2 : -1;
        boolean flag = false;
        int answer = 0;
        // System.out.println(goal);
        while(left<2*N && right<2*N && goal!=-1){
            if(sum1==goal){
                flag = true;
                // System.out.println("== : "+left+" "+right+" | "+sum1);
                break;
            }else if(sum1<goal){
                //right 움직이고 합에 더하기
                right++;
                if(right>=2*N) break;
                sum1 += right>=N ? queue2[right-N] : queue1[right];
                answer++;
                // System.out.println("< : "+left+" "+right+" | "+sum1);
            }else{
                //미리 합에서 빼고 left 움직이기
                if(left>=2*N) break;
                sum1 -= left>=N ? queue2[left-N] : queue1[left];
                left++;
                answer++;
                // System.out.println("> : "+left+" "+right+" | "+sum1);
            }
        }
        
        if(!flag) answer = -1;
        return answer;
    }
}