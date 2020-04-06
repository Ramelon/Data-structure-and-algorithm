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
	 *            几层塔
	 * @param X
	 *            塔1
	 * @param Y
	 *            塔2
	 * @param Z
	 *            塔3
	 */
	public static void f(int n, char X, char Y, char Z) {
		if (n == 1) {
			System.out.println("move:" + X + "->" + Z);
			sum++;
			return;
		}
		f(n - 1, X, Z, Y); // 把X塔上编号为1 ~ n-1 的块移动到Y上， 以Z塔为中介。
		f(1, X, Y, Z); // 把X塔上的编号为n的块移动到Z上
		f(n - 1, Y, X, Z); // 把Y塔上编号为1 ~ n-1 的块移动到Z上， 以X塔为中介。
	}
}
