//그리디, 중복순열
//최대 시간복잡도 : 4^7*100 = 약 9,000,000 -> 완탐 충분히 가넝!
//삽질 많이 함...
//이건 첫번째 삽질: for사용자 > for임티 > for할인율 => 중복순열 생각못함
//이건 두번째 삽질: 중복순열까지는 했는데 사용자 for문을 잘못된 순서에 넣음 => 사용자마다 이모티콘 할인율 다름 ex) 사용자 1: [10,20], 사용자 2: [30,40]
//이건 세번째 삽질: 사용자마다 같은 할인율이 적용되도록까지는 했는데 같은 이모티콘 플러스 가입자수인 경우 개별 이모티콘 구매가격을 갱신하는 거 생각 못함
//백준허브 기준: [level 2] Title: 이모티콘 할인행사, Time: 67.41 ms, Memory: 79.6 MB -BaekjoonHub
class Solution {
    static int userLen, emoLen;
    static int userPercent, userPrice;
    static int[] percentArr; //이모티콘 당 할인율 배열
    static int[] sales = {10,20,30,40};
    
    static int emoPlus, total;
    static int userPlus, userTotal;
    static int[] emoticons;
    static int[][] users;
    
    public int[] solution(int[][] usersArr, int[] emoticonsArr) {        
        userLen = usersArr.length;
        emoLen = emoticonsArr.length;
        emoticons = new int[emoLen];
        for(int i=0;i<emoLen;i++){
            emoticons[i] = emoticonsArr[i];
        }
        users = new int[userLen][2];
        for(int i=0;i<userLen;i++){
            for(int j=0;j<2;j++){
                users[i][j] = usersArr[i][j];
            }
        }
        
        emoPlus = -1;
        total = -1;
        
        percentArr = new int[emoLen];
        permperm(0); //중복순열
        
        int[] answer = {emoPlus, total};
        
        return answer;
    }
    
    static void permperm(int idx){
        if(idx==emoLen){ //순열이 다 채워질 때마다 각 경우에 대해 이모티콘 플러스 가입자수와 개별 이모티콘 구매 비용 체크
            int[] tmpArr = percentArr.clone(); //깊은 복사
            int[] res = check(tmpArr);
            if(res[0]>emoPlus){ //이모티콘 플러스 가입한 사용자가 갱신되는 경우
                emoPlus = res[0];
                total = res[1]; //그 경우의 개별 이모티콘 구매 비용을 덮어씀
            }else if(res[0]==emoPlus){ //이모티콘 플러스 가입한 사용자가 동일한 경우
                total = Math.max(total, res[1]); //그 중 개별 이모티콘 구매 비용이 더 높은걸 덮어씀
            }
            return;
        }
        
        for(int i=0;i<4;i++){
            percentArr[idx] = sales[i];
            permperm(idx+1);
        }
    }
    
    static int[] check(int[] tmpArr){
        int userPlus = 0;
        int userTotal = 0;
        for(int u=0;u<userLen;u++){
            userPercent = users[u][0];
            userPrice = users[u][1]; 
            
            int sum = 0;
            for(int i=0;i<emoLen;i++){
                if(tmpArr[i]>=userPercent){
                    float sale = (float) tmpArr[i] / 100;
                    sum += emoticons[i]*(1-sale);
                }
            }
            // System.out.println("sum:"+sum);
            
            if(sum>=userPrice) userPlus++; //플러스 가입 하고 이모티콘 개별 구매는 x
            else userTotal+=sum; //플러스 가입 안하고 원하는 애들만 삼
            
        }
        
        return new int[]{userPlus, userTotal};
    }
}