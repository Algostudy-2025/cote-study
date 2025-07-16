import java.util.*;

class Solution {

	static class Node {
		int data;
		int prev;
		int next;

		public Node(int data, int prev, int next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}

	// n -> 표의 행 개수 k -> 처음 선택된 행의 위치, cmd -> 명령어 배열
	public String solution(int n, int k, String[] cmd) {

		Node[] cells = new Node[n];

		for (int i = 0; i < n; i++) {
			cells[i] = new Node(i, i - 1, i + 1);
		}

		int curr = k;
		boolean[] deleted = new boolean[n];
		Stack<Integer> removed = new Stack<>();

		for (int i = 0; i < cmd.length; i++) {
			String command = cmd[i];
			String[] parts = command.split(" ");

			if (parts[0].equals("U")) {
				for (int j = 0; j < Integer.parseInt(parts[1]); j++) {
					curr = cells[curr].prev;
				}
			} else if (parts[0].equals("D")) {
				for (int j = 0; j < Integer.parseInt(parts[1]); j++) {
					curr = cells[curr].next;
				}
			} else if (parts[0].equals("C")) {
				Node node = cells[curr];

				deleted[curr] = true;
				if (node.prev != -1) {
					cells[node.prev].next = node.next;
				}
				if (node.next != n) {
					cells[node.next].prev = node.prev;
				}

				removed.push(curr);

				if (node.next != n) {
					curr = node.next;
				} else {
					curr = node.prev;
				}

			} else if (parts[0].equals("Z")) {
				int restore = removed.pop();
				Node node = cells[restore];
				deleted[restore] = false;
				if (node.prev != -1) {
					cells[node.prev].next = restore;
				}
				if (node.next != n) {
					cells[node.next].prev = restore;
				}
			}

		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			if (deleted[i]) {
				sb.append("X");
			} else {
				sb.append("O");
			}
		}

		return sb.toString();

	}
}
//         LinkedList<Integer> cells = new LinkedList<>();

//         for(int i = 0; i < n; i++) {
//             cells.add(i);
//         }

//         int select = k;
//         Stack<int[]> removed = new Stack<>();

//         for(int i = 0; i < cmd.length; i++) {
//             String command = cmd[i];
//             String[] parts = command.split(" ");
//             int size = cells.size();

//             if(parts[0].equals("U")) {
//                 select -= Integer.parseInt(parts[1]);
//                 continue;

//             } else if(parts[0].equals("D")) {
//                 select += Integer.parseInt(parts[1]);
//                 continue;

//             } else if(parts[0].equals("C")) {
//                 removed.push(new int[] {cells.get(select), select});
//                 cells.remove(select);

//                 if(select == size-1) {
//                     select--;
//                 }
//                 continue;

//             } else if(parts[0].equals("Z")) {
//                 int[] restore = removed.pop();
//                 int insertIdx = 0;
//                 while (insertIdx < cells.size() && 
//                        cells.get(insertIdx) < restore[0]) {
//                     insertIdx++;
//                 }
//                 cells.add(insertIdx, restore[0]);
//                 if (insertIdx <= select) {
//                     select++;
//                 }
//                 continue;
//             }
//         }

//         boolean[] isRemoved = new boolean[n];
//         for(int i = 0 ; i < n; i++) {
//             isRemoved[i] = true;
//         }

//         for(int idx : cells) {
//             isRemoved[idx] = false;
//         }

//         StringBuilder sb = new StringBuilder();

//         for(int i = 0 ; i < n; i++) {
//             if(isRemoved[i]) {
//                 sb.append("X");
//             } else {
//                 sb.append("O");
//             }
//         }

//         return sb.toString();

//         StringBuilder sb = new StringBuilder();

//         boolean[] delete = new boolean[n];
//         int selIdx = k;
//         Stack<Integer> deleted = new Stack<>();

//         for(int i = 0; i < cmd.length; i++) {
//             String command = cmd[i];

//             if(command.charAt(0) == 'U') { //현재 선택된 행에서 X칸 위에 있는 행을 선택합니다.
//                 String[] parts = command.split(" ");
//                 int move = Integer.parseInt(parts[1]);
//                 int count = 0;
//                 // 삭제되지 않은 행을 만날 때까지 위로 이동하는 것을 move 횟수만큼 반복
//                 while(count < move) {
//                     selIdx--;
//                     if(!delete[selIdx]) {
//                         count++;
//                     }
//                 }
//                 continue;

//             } else if (command.charAt(0) == 'D') { // 현재 선택된 행에서 X칸 아래에 있는 행을 선택합니다.
//                 String[] parts = command.split(" ");
//                 int move = Integer.parseInt(parts[1]);
//                 int count = 0;
//                 // 삭제되지 않은 행을 만날 때까지 아래로 이동하는 것을 move 횟수만큼 반복
//                 while(count < move) {
//                     selIdx++;
//                     if(!delete[selIdx]) {
//                         count++;
//                     }
//                 }
//                 continue;

//             } else if (command.charAt(0) == 'C') { // 현재 선택된 행을 삭제한 후, 바로 아래 행을 선택합니다. 단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택합니다
//                 delete[selIdx] = true;
//                 deleted.push(selIdx);

//                 int next = selIdx + 1;

//                 while(next < n && delete[next]) {
//                     next++;
//                 }

//                 if(next < n) {
//                     selIdx = next;    
//                 } else {
//                     next = selIdx - 1;
//                     while(next >= 0 && delete[next]) {
//                         next--;
//                     }
//                     selIdx = next;
//                 }

//                 continue;

//             } else if (command.charAt(0) == 'Z') { //가장 최근에 삭제된 행을 원래대로 복구합니다. 단, 현재 선택된 행은 바뀌지 않습니다.
//                 int restore = deleted.pop();
//                 delete[restore] = false;
//                 continue;
//             }
//         }

//         for(int i = 0; i < n; i++) {
//             if(delete[i]) {
//                 sb.append("X");
//             } else {
//                 sb.append("O");
//             }
//         }

//         return sb.toString();
