
import java.util.*;
import java.io.*;

public class P_42576 {
	public static void main(String[] args) throws IOException {
		String[] participant = { "leo", "kiki", "eden" };
		String[] completion = { "eden", "kiki" };

		HashMap<String, Integer> map = new HashMap<>();

		// 참가자 이름을 카운트
		for (String name : participant) {
			map.put(name, map.getOrDefault(name, 0) + 1);
		}

		// 완주자 이름은 카운트에서 제거
		for (String name : completion) {
			map.put(name, map.get(name) - 1);
		}

		// 값이 1인 사람 = 완주하지 못한 사람
		for (String key : map.keySet()) {
			if (map.get(key) != 0) {
				System.out.println(key);
			}
		}

	}

}