package recursion;

public class hanoi {

	private static int sum = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		f(4, '1', '2', '3');
		System.out.println(sum);
	}

	/**
	 * 
	 * @param n
	 *            ������
	 * @param X
	 *            ��1
	 * @param Y
	 *            ��2
	 * @param Z
	 *            ��3
	 */
	public static void f(int n, char X, char Y, char Z) {
		if (n == 1) {
			System.out.println("move:" + X + "->" + Z);
			sum++;
			return;
		}
		f(n - 1, X, Z, Y); // ��X���ϱ��Ϊ1 ~ n-1 �Ŀ��ƶ���Y�ϣ� ��Z��Ϊ�н顣
		f(1, X, Y, Z); // ��X���ϵı��Ϊn�Ŀ��ƶ���Z��
		f(n - 1, Y, X, Z); // ��Y���ϱ��Ϊ1 ~ n-1 �Ŀ��ƶ���Z�ϣ� ��X��Ϊ�н顣
	}
}
