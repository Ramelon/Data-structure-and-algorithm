package algorithm;

public class ViolentMatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "I Love China";
		String str2 = "Loveee";
		int index = ViolentMatchDemo(str1, str2);
		System.out.println(index);
	}

	/**
	 * 
	 * @param str1
	 *            源字符串
	 * @param str2
	 *            要查找的字符串
	 * @return
	 */
	public static int ViolentMatchDemo(String str1, String str2) {
		byte[] strByte1 = str1.getBytes();
		byte[] strByte2 = str2.getBytes();

		int i = 0;
		int j = 0;
		while (i < strByte1.length && j < strByte2.length) {
			if (strByte1[i] == strByte2[j]) {
				i++;
				j++;
			}else{
				// 指针需要位移到那里。
				i = i - j + 1;
				j = 0;
			}
		}
		if (j == strByte2.length) {
			// 因为要获取头的位置
			return i - j;
		}
		return -1;
	}
}
