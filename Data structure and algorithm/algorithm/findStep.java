package algorithm;

public class findStep {
	public static void main(String[] args) {
		System.out.println(findStepDemo(10));
	}

	// ��������̨�� ���� һ�� ���� ��������
	public static int findStepDemo(int n) {
		if (n == 1 || n == 2) {
			return n;
		}

		if (n == 3) {
			return n + 1;
		}
		
		return findStepDemo(n - 1) + findStepDemo(n - 2) + findStepDemo(n - 3);
	}
}
