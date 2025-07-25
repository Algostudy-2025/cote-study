class Solution {
    static int numEmoticon, numUser, resPlusUser, resProfit;
    static int[] selected, emoticons;
    static int[][] users;
    
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        
        numUser = users.length;
        numEmoticon = emoticons.length;
        
        selected = new int[numEmoticon];
        dfs(0);
        
        return new int[]{resPlusUser, resProfit};
    }
    
    public void dfs(int depth){
        if (depth == numEmoticon) {
            check();
            return;
        }
        for (int rate = 10; rate<=40; rate+=10){
            selected[depth]=rate;
            dfs(depth+1);
        }
    }
    
    public void check(){
        int plusUser = 0;
        int profit = 0;
        
        for (int[] user : users){
            int threshold = user[0];
            int limit = user[1];
            int total = 0;
            
            for (int i = 0; i<numEmoticon; i++){
                // 기프티콘 구매하는 경우
                if (threshold<=selected[i]){
                    // 할인가격 계산
                    total+=emoticons[i] * (100-selected[i]) / 100;
                    if (total>=limit){
                        plusUser++;
                        total = 0;
                        break;
                    }
                }
            }
            profit+=total;
        }
        
        if (resPlusUser<plusUser || (resPlusUser == plusUser && resProfit < profit)){
            resPlusUser = plusUser;
            resProfit = profit;
        }
    }
}
