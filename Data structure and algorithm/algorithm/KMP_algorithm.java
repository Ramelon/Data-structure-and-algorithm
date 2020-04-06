package algorithm;

import java.util.Arrays;

public class KMP_algorithm {
	public static void main(String[] args) {
		String str1 = "BBC ABCDAB ABCDABCDABDE";
		String str2 = "ABCDABDD";
		// ABCD ABD
		int[] next = Next(str2);
		System.out.println(Arrays.toString(next));
		
		int index = KMP_Demo(str1, str2, next);
		System.out.println(index);
	}

	/**
	 * 
	 * @param str1
	 *            源字符串
	 * @param str2
	 *            要查找的字符串
	 * @param next
	 *            要查找的字符串部分匹配值数组
	 * @return
	 */
	public static int KMP_Demo(String str1, String str2, int[] next) {
		for (int i = 0, j = 0; i < str1.length(); i++) {
			while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
				j = next[j - 1]; 
			}
			if (str1.charAt(i) == str2.charAt(j)) {
				j++;
			}
			if (j == str2.length()) {
				return i - j + 1;
			}
		}
		return -1;
	}

	public static int[] Next(String str) {
		// 这里的next数组就是存储部分匹配值
		int[] next = new int[str.length()];

		// 只有一个字符时，那么前缀后缀都为空集 所有值不会变动一直为0
		next[0] = 0;
		// charAt(index) index 为多少 就取多少字符
		for (int i = 1, j = 0; i < str.length(); i++) {
			// 这是核心所在。
			while (j > 0 && str.charAt(i) != str.charAt(j)) {
				j = next[j - 1];
			}
			// charAt index -- 字符的索引。 返回指定索引处的字符。
			if (str.charAt(i) == str.charAt(j)) {
				j++;
			}
			next[i] = j;
		}

		return next;
	}
}
