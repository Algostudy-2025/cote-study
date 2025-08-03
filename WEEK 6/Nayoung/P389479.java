class Solution {
    public int solution(int[] players, int m, int k) {
        int N = players.length;
        int[] cnt = new int[24]; //시간대별로 필요한 서버 수를 저장하는 배열
        for(int i=0;i<24;i++){
            cnt[i] = players[i]/m;
        }
        
        int pointer = 0; //시간대 움직이는 포인터
        int servers = 0; //특정 시간대에 필요한 서버 수
        while(pointer<N){
            if(cnt[pointer]<=0) { //해당시간에 서버가 필요없다면
                pointer++; //포인터만 다음 시간대로 넘기고 패스
                continue;
            }else{ //아니라면 해당 시간대의 서버들로 버틸 수 있는 시간대에 대해 필요서버수 빼기
                servers = cnt[pointer];  
                for(int i=pointer+1;i<pointer+k;i++){
                    if(i>=N) continue; //범위 넘어가면 패스
                    cnt[i] -= servers;
                }
                pointer++; //포인터를 다음 시간대로 넘김
            }
        }
        
        int answer = 0; //필요한 최소 전체 서버수
        for(int i=0;i<24;i++){
            if(cnt[i]>0) answer+= cnt[i];
        }
        
        return answer;
    }
}