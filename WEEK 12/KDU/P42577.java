import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> set = new HashSet<>(Arrays.asList(phone_book));
        for (String num : phone_book) {
            // num의 모든 접두어가 다른 번호로 존재하는지 확인
            for (int cut = 1; cut < num.length(); cut++) {
                if (set.contains(num.substring(0, cut))) {
                    return false;
                }
            }
        }
        return true;
    }
}
