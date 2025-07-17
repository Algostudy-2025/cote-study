/*

    Java에서 제공하는 LinkedList를 사용해서 구현 -> 시간초과

    채점 결과: 30점
    효율성 0점...
    

*/


import java.util.*;

class Solution {
    public static void main(String[] args) throws Exception {
        int n = 8;
        int k = 2;
        String[] cmd = new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};

        String answer = solution(n, k, cmd);
        System.out.println(answer);
        System.out.println("OOOOXOOO");
    }

    public static String solution(int n, int k, String[] cmd) {

        List<Integer> table = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            table.add(i);
        }

        int idx = k;
        Deque<int[]> restore = new ArrayDeque<>();

        for (String c : cmd) {
            StringTokenizer st = new StringTokenizer(c);
            String type = st.nextToken();
            int X = 0;
            if (st.hasMoreTokens())
                X = Integer.parseInt(st.nextToken());

            switch (type) {
                case "U":
                    idx -= X;
                    break;
                case "D":
                    idx += X;
                    break;
                case "C":
                    restore.push(new int[]{idx, table.get(idx)});
                    table.remove(idx);
                    if (idx == table.size())
                        idx--;
                    break;
                case "Z":
                    int[] re = restore.pop();
                    table.add(re[0], re[1]);
                    if (re[0] <= idx)
                        idx++;
                    break;
            }
        }

        // 결과출력
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (int t : table) {
            while (i < t) {
                sb.append('X');
                i++;
            }
            sb.append('O');
            i++;
        }
        while (i < n) {
            sb.append('X');
            i++;
        }

        return sb.toString();
    }
}