class Solution {
    int answer;
    int n;
    int left_r = 0;
    int left_c = 0;
    int right_r = 0;
    int right_c = 0;
    public int solution(int[][] board) {
        n = board.length - 1;
        move(board);
        return answer;
    }
    
    public void move(int[][] board) {
        // 둘 중 한쪽이라도 (n, n) 도착
        if((left_r == n && left_c == n) || (right_r == n && right_c == n)) return;
        // 지도 상 동측 이동이 가능
        if(left_c > right_c) {
            if(board[left_r][left_c] != 1) {
                left_c++;
                right_c++;
            }
        } else if (left_c < right_c) {
            if(board[right_r][right_c] != 1) {
                left_c++;
                right_c++;
            }
        }
        // 지도 상 남측 이동이 가능
        if(left_r >= right_r) {
            if(board[left_r][left_c] != 1) {
                left_r++;
                right_r++;
            }
        } else if (left_r < right_r) {
            if(board[right_r][right_c] != 1) {
                left_r++;
                right_r++;
            }
        }
        // 불가능하면 회전 시도
        // 이걸 어떻게 짜나요,,, 왼발, 오른발에 90도 조건만 있으니까 시계, 반시계인데,,, 
        answer++;
        move(board);
    }
}