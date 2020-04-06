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
	 *            Դ�ַ���
	 * @param str2
	 *            Ҫ���ҵ��ַ���
	 * @param next
	 *            Ҫ���ҵ��ַ�������ƥ��ֵ����
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
		// �����next������Ǵ洢����ƥ��ֵ
		int[] next = new int[str.length()];

		// ֻ��һ���ַ�ʱ����ôǰ׺��׺��Ϊ�ռ� ����ֵ����䶯һֱΪ0
		next[0] = 0;
		// charAt(index) index Ϊ���� ��ȡ�����ַ�
		for (int i = 1, j = 0; i < str.length(); i++) {
			// ���Ǻ������ڡ�
			while (j > 0 && str.charAt(i) != str.charAt(j)) {
				j = next[j - 1];
			}
			// charAt index -- �ַ��������� ����ָ�����������ַ���
			if (str.charAt(i) == str.charAt(j)) {
				j++;
			}
			next[i] = j;
		}

		return next;
	}
}
