class Solution {
    /**
     * P42576. 완주하지 못한 선수
     * 단순하게 방문배열과 비교방법으로는 시간 초과 발생
     * 참가자 수가 최대 100,000명 -> O(n^2) 시간복잡도는 불가능
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        boolean[] visited = new boolean[completion.length];
        
        for(String p:participant){
            boolean check = false;
            for(int i=0; i<completion.length; i++){
                if(!visited[i] && p.equals(completion[i])){
                    visited[i]=true;
                    check =true;
                    break;
                }
            }
            if(!check)
                answer = p;
        }
        
        return answer;
    }
}
     * 채점 결과
     * 정확성: 58.3
     * 효율성: 0.0
     * 합계: 58.3 / 100.0
     * 
     * 해시맵을 이용하여 참가자와 완주자를 카운트하는 방법으로 해결해야함
     * 해당 문제를 풀려면 해시맵으로 풀어야 시간내에 해결 가능
     * 라이브러리 사용이 안되므로 해시맵과 필요할만한 기능들을 구현 -> 다른 사람풀이를 보니 사용 가능함
     * 채점 결과
     * 정확성: 58.3
     * 효율성: 41.7
     * 합계: 100.0 / 100.0
     * 
     * 바닐라 자바로 풀어야 하는 상황에서 더 쉽게 풀려고 했다면
     * 해시맵을 직접 구현하는 것보다 버블정렬을 구현해서 풀어도 풀이가 가능
import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);
        int i;
        for ( i=0; i<completion.length; i++){

            if (!participant[i].equals(completion[i])){
                return participant[i];
            }
        }
        return participant[i];
    }
}
     */
    class Runner {
        String key;
        int value;
        Runner next;

        Runner(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class CustomHashMap {
        // 해시맵을 만들 때 전체 크기는 충돌을 피하기 위해 적당히 큰 소수를 사용함
        //-> 잘 모르겠어서 전체인원 *2 + 3으로 설정
        private final int SIZE = 200003;
        private Runner[] entry = new Runner[SIZE];

        private int hash(String key) {
            int hash = 0;
            for (int i = 0; i < key.length(); i++) {
                hash = (hash * 31 + key.charAt(i)) % SIZE;
            }
            return hash;
        }

        public void put(String key, int value) {
            int index = hash(key);
            Runner current = entry[index];

            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }

            Runner newRunner = new Runner(key, value);
            newRunner.next = entry[index];
            entry[index] = newRunner;
        }

        public int get(String key) {
            int index = hash(key);
            Runner current = entry[index];

            while (current != null) {
                if (current.key.equals(key)) {
                    return current.value;
                }
                current = current.next;
            }

            return 0;
        }

        /**
         * 기존 해시맵에는 없는 매서드
         * 참가자 배열을 순회하면서 해당 키의 값을 1 증가시키는 매서드
         * 이 매서드는 참가자 배열에 있는 모든 키를 해시맵에 추가하는 역할을 함
         */
        public void increment(String key) {
            int index = hash(key);
            Runner current = entry[index];

            while (current != null) {
                if (current.key.equals(key)) {
                    current.value++;
                    return;
                }
                current = current.next;
            }
            put(key, 1);
        }

        /**
         * 완주자 배열을 순회하면서 해당 키의 값을 1 감소시키는 매서드
         * 이 매서드는 완주자 배열에 있는 모든 키를 해시맵에서 제거하는 역할을 함
         */
        public void decrement(String key) {
            int index = hash(key);
            Runner current = entry[index];

            while (current != null) {
                if (current.key.equals(key)) {
                    current.value--;
                    return;
                }
                current = current.next;
            }
        }

        /**
         * 해시맵에서 value가 0이 아닌 첫 번째 키를 찾는 매서드
         * 이 매서드는 완주하지 못한 선수를 찾는 역할을 함
         * 이 방법때문에 조금은 효율이 떨어진다고 생각하였음
         * 그렇다보니 정렬로 해결하는 방법이 더 효율적일 수 있음
         */
        public String finding() {
            for (int i = 0; i < SIZE; i++) {
                Runner current = entry[i];
                while (current != null) {
                    if (current.value != 0) {
                        return current.key;
                    }
                    current = current.next;
                }
            }
            return "error";
        }
    }

    public String solution(String[] participant, String[] completion) {
        CustomHashMap hashMap = new CustomHashMap();

        for (String p : participant) {
            hashMap.increment(p);
        }

        for (String c : completion) {
            hashMap.decrement(c);
        }

        return hashMap.finding();
    }
}