/*

    항상 인덱스 순서대로 정렬된 상태로 유지된다는 점 -> TreeSet 사용  
    higher()과 lower()를 사용해 커서의 위치를 옮길 수 있음 

    - 채점 결과: 100점

    - 효율성 테스트 결과
    테스트 1 〉	통과 (697.09ms, 211MB)
    테스트 2 〉	통과 (869.88ms, 212MB)
    테스트 3 〉	통과 (846.66ms, 213MB)
    테스트 4 〉	통과 (619.19ms, 206MB)
    테스트 5 〉	통과 (610.22ms, 208MB)
    ... (생략)

*/


import java.util.*;

class Solution2 {
    public static void main(String[] args) throws Exception {
        int n = 8;
        int k = 2;
        String[] cmd = new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};

        String answer = solution2(n, k, cmd);
        System.out.println(answer);
        System.out.println("OOOOXOOO");
    }

    public static String solution2(int n, int k, String[] cmd) {

        TreeSet<Integer> table = new TreeSet<>();
        Deque<Integer> restore = new ArrayDeque<>();
        boolean[] isDeleted = new boolean[n];

        for (int i = 0; i < n; i++)
            table.add(i);

        int idx = k;

        for (String c : cmd) {
            StringTokenizer st = new StringTokenizer(c);
            String type = st.nextToken();
            int X = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : 0;

            switch (type) {

                case "U":
                    for (int x = 0; x < X; x++)
                        idx = table.lower(idx) != null ? table.lower(idx) : idx;
                    break;

                case "D":
                    for (int x = 0; x < X; x++)
                        idx = table.higher(idx) != null ? table.higher(idx) : idx;
                    break;

                case "C":
                    restore.push(idx);
                    table.remove(idx);
                    isDeleted[idx] = true;
                    idx = table.higher(idx) != null ? table.higher(idx) : table.lower(idx);
                    break;

                case "Z":
                    int re = restore.pop();
                    table.add(re);
                    isDeleted[re] = false;
                    break;
            }
        }

        StringBuilder answer = new StringBuilder();
        for (boolean de : isDeleted) {
            if (de) answer.append('X');
            else answer.append('O');
        }
        return answer.toString();
    }
}