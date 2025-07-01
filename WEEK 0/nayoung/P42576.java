import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        HashMap<String, Integer> map = new HashMap();
        
        for(String e: participant){
            if(map.keySet().contains(e)){
                int cnt = map.get(e);
                map.put(e, ++cnt);
            }else{
                map.put(e, 1);
            }
        }
        
        for(String e: completion){
            int cnt = map.get(e);
            map.put(e, --cnt);
        }
        
        for(String e: map.keySet()){
            int cnt = map.get(e);
            if(cnt>0){
                answer = e;
            }
        }
        
        return answer;
    }
}